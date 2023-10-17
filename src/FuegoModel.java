import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuegoModel extends Canvas implements Runnable {
    private double[][] data;
    private Graphics2D canvasGraphics;
    private Color colorBC;
    private int inicio;
    private double pixelarriba;
    int size;
    private Color colorFr;
    private FuegoView fuegoView;
    private FuegoController fuegoController;
    private boolean deadFire;
    private BufferedImage imagetemp;
    private int[] ImgArray;
    private Color[] listaColores;
    public void setColorFr(Color colorFr) {
        this.colorFr = colorFr;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public FuegoView getFuegoView() {
        return fuegoView;
    }

    public void setFuegoView(FuegoView fuegoView) {
        this.fuegoView = fuegoView;
    }

    public FuegoController getFuegoController() {
        return fuegoController;
    }

    public void setFuegoController(FuegoController fuegoController) {
        this.fuegoController = fuegoController;
    }

    public void setDeadFire(boolean deadFire) {
        this.deadFire = deadFire;
    }


    public void setPixelarriba(double pixelarriba) {
        this.pixelarriba = pixelarriba;
    }

    public void setColorBC(Color colorBC) {
        this.colorBC = colorBC;
    }

    public FuegoModel() {
        size = 600;
        data = new double[size][size];
    }

    public void crearColores(Color[] colors, int primer, int ultimo) {
        int sumarojo = (colors[primer].getRed());
        int sumaverde = (colors[primer].getGreen());
        int sumaazul = (colors[primer].getBlue());
        for (int i = primer; i < ultimo; i++) {
            int alpha = i;
            for (int j = colors[primer].getRed(); j < colors[ultimo].getRed(); j++) {
                sumarojo++;
            }
            for (int j = colors[primer].getGreen(); j < colors[ultimo].getGreen(); j++) {
                sumaverde++;
            }
            for (int j = colors[primer].getBlue(); j < colors[ultimo].getBlue(); j++) {
                sumaazul++;
            }


            Color color = new Color(sumarojo, sumaverde, sumaazul, alpha);
            colors[i] = color;
        }
    }

    public void llamarPaleta() {
        listaColores = new Color[256];
        listaColores[0] = new Color(0, 0, 0, 0);
        if (colorFr != null) {
            listaColores[100] = new Color(colorFr.getRed(), colorFr.getGreen(), colorFr.getBlue(), 255);
        } else {
            listaColores[100] = new Color(255, 0, 0, 255);
        }
        listaColores[150] = new Color(253, 152, 0, 255);
        //Valores para añadir sensación del fuego sin importar su color, no modificables
        listaColores[200] = new Color(255, 255, 0, 255);
        listaColores[255] = new Color(255, 255, 255, 255);
        crearColores(listaColores, 0, 100);
        crearColores(listaColores, 100, 150);
        crearColores(listaColores, 150, 200);
        crearColores(listaColores, 200, 255);
    }
    public void LimpiarMatriz(){
        for (int fila = 0; fila < data.length; fila++) {
            for (int columna = 0; columna < data[fila].length; columna++) {
                data[columna][fila] = 0;
            }
        }
    }
    public void Pintar(Graphics g) {
        canvasGraphics = (Graphics2D) g;

        if (colorBC != null) {
            this.setBackground(colorBC);
        }
        //Default
        /*
        else {
            try {
                //Hacer un nuevo canvasGraphics
                BufferedImage   background = ImageIO.read(new File("src/images/bg.jpg"));
                canvasGraphics.drawImage(background, 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        llamarPaleta();
        Random r = new Random();
        // Temperature matrix
        //controlar tamaño con variable
        // Fire points initialization

        for (int columna = 0; columna < data[0].length; columna++) {
            int porciento = r.nextInt(101);

            //Slider modifiable value
            if (porciento <= inicio) {
                if (!deadFire) {
                    data[data.length - 1][columna] = 255;

                }
                else {
                    LimpiarMatriz();
                }
                
            }
        }

        //i = file j = columna )
        // Fire propagation
        for (int fila = 0; fila < data.length - 1; fila++) {
            for (int columna = 0; columna < data[fila].length; columna++) {
                //VALOR MODIFICABLE EN UN SLIDER
                Random random= new Random();
                if (random.nextBoolean()){
                double temperatura = data[fila][columna] * pixelarriba;
                //abajo
                temperatura += data[fila + 1][columna] * 0.5;

                if (columna < data[fila + 1].length - 1) {
                    //izquierda
                    temperatura += data[fila + 1][columna + 1] * 0.15;
                }
                if (columna > 0) {
                    //derecha
                    temperatura += data[fila + 1][columna - 1] * 0.15;
                }
                data[fila][columna] = temperatura;
            }
                else if (data[fila][columna] > 50){
                    data[fila][columna]-=7;
                }
            }

           // System.out.println(" ");
        }

        //Pintar píxeles
        for (int fila = 0; fila < data.length; fila++) {
            for (int columna = 0; columna < data[fila].length; columna++) {

                imagetemp.setRGB(columna, fila, listaColores[(int) data[fila][columna]].getRGB());

            }
        }
        //Double buffer attempt
        canvasGraphics.drawImage(imagetemp, 200, 0, null);



    }

    @Override
    public void run() {
        //Double buffer attempt

        imagetemp = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        while (true) {

            Pintar(getGraphics());
            repaint();



        }
    }
}
