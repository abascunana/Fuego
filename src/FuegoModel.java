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
    private int[][] data;
    private Graphics2D canvasGraphics;
    private int[][] data2;
    private Color colorBC;
    private int inicio;
    private int expansion;
    private Color colorFr;
    private FuegoView fuegoView;
    private FuegoController fuegoController;
    private boolean deadFire;
    private BufferedImage imagetemp ;
    private BufferedImage imageshow1 ;
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
    public void setExpansion(int expansion) {
        this.expansion = expansion;
    }
    public void setColorBC(Color colorBC) {
        this.colorBC = colorBC;
    }


    public void crearColores(Color[] colors, int primer, int ultimo) {
        int sumarojo = (colors[primer].getRed());
        int sumaverde = (colors[primer].getGreen());
        int sumaazul = (colors[primer].getBlue());
        //lo que tengo que hacer es calcular con las posiciones dadas los colores de enmedio
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
            // int sumarojo = (colors[ultimo].getRed()-colors[primer].getRed())/(ultimo-primer);
            // int sumaverde = (colors[ultimo].getGreen()-colors[primer].getGreen())/(ultimo-primer);
            // int sumaazul = (colors[ultimo].getBlue()-colors[primer].getBlue())/(ultimo-primer);

            Color color = new Color(sumarojo, sumaverde, sumaazul, alpha);
            colors[i] = color;
        }
    }
public void llamarPaleta(){
     listaColores = new Color[255];
     listaColores[0] = new Color(0, 0, 0, 0);


    if (colorFr != null){
        listaColores[100] = new Color(colorFr.getRed(), colorFr.getGreen(), colorFr.getBlue(), 255);
    }
    else {
        listaColores[100] = new Color(255, 0, 0, 255);
    }

    listaColores[150] = new Color(253, 152, 0, 255);
    //Valores para añadir sensación del fuego sin importar su color, no modificables
    listaColores[200] = new Color(255, 255, 0, 255);
    listaColores[254] = new Color(255, 255, 255, 255);

    crearColores(listaColores, 0, 100);
    crearColores(listaColores, 100, 150);
    crearColores(listaColores, 150, 200);
    crearColores(listaColores, 200, 254);

}
    public void Pintar(Graphics g) {
        setSize(new Dimension(500, 500));
        canvasGraphics = (Graphics2D) g;
        if (colorBC != null) {
            this.setBackground(colorBC);
        }
        //Default
        else {
            this.setBackground(Color.black);
        }

        //TODO MEJORAR MODELO
        llamarPaleta();
        Random r = new Random();

        // Creación de la matriz de temperatura
        data = new int[getWidth()][getHeight()];
        data2 = new int[data.length][data[0].length];

        // Inicialización de los puntos de llama en el fondo de la pantalla
        for (int i = 0; i < data.length; i++) {
            try {
                for (int j = data[0].length - 2; j < data[0].length; j++) {
                    int porciento = r.nextInt(101);
                    //VALOR MODIFICABLE EN UN SLIDER
                    if (porciento < inicio) {
                        if (!deadFire) {
                            data[i][j] = 254;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //El fuego no se puede pintar porque el usuario tiene la pantalla reducida, resultado esperado
                Logger.getLogger(FuegoModel.class.getName()).log(Level.FINE, null, e);
            }

        }
        //i = width j = height )
        // Propagación del fuego
        for (int i = 1; i < data.length - 2/*márgen*/; i++) {
            for (int j = data[0].length - 2/*márgen*/; j > 0; j--) {
                int porciento = r.nextInt(101);
                //VALOR MODIFICABLE EN UN SLIDER
                if (porciento < expansion) {
                    imageshow1 = imagetemp;
                    data2[i][j] = data[i][j];
                    //data[i][j+1]=(data[i][j+1]+data[i+1][j]+data[i-1][j]+data[i][j])/4;
                    data[i+1][j]=(data[i][j+1]+data[i-1][j]+data[i][j+1]+data[i+1][j+1])/4;
                    imagetemp.setRGB(i, j, listaColores[data[i][j]].getRGB());
                }
            }
        }
        canvasGraphics.drawImage(imageshow1,0,0,null);
        data2 = data;
        // Copia los datos de la matriz temporal de vuelta a la matriz de temperatura
    }
    @Override
    public void run() {
        imagetemp = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        imageshow1 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        while (true) {
            Pintar(getGraphics());
            repaint(100);
        }
    }
}
