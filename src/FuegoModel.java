import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
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
    private FuegoView fuegoView;
    private FuegoController fuegoController;
    private boolean deadFire;

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


    public void setColorBC(Color colorBC) {
        this.colorBC = colorBC;
    }

    public void crearColores(Color[] colors, int primer, int ultimo) {

        //lo que tengo que hacer es calcular con las posiciones dadas los colores de enmedio
        for (int i = primer; i < ultimo; i++) {
            int sumarojo = (colors[ultimo].getRed());
            int sumaverde = (colors[ultimo].getGreen());
            int sumaazul = (colors[ultimo].getBlue());
            // int sumarojo = (colors[ultimo].getRed()-colors[primer].getRed())/(ultimo-primer);
            // int sumaverde = (colors[ultimo].getGreen()-colors[primer].getGreen())/(ultimo-primer);
            // int sumaazul = (colors[ultimo].getBlue()-colors[primer].getBlue())/(ultimo-primer);
            int alpha = i;
            Color color = new Color(sumarojo, sumaverde, sumaazul, alpha);
            colors[i] = color;
        }
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
        Color[] listaColores = new Color[255];
        //TODO USAR 3 COLORES PARA CAMBIAR EL VALOR DEL FUEGO
        //color no utilizado
        listaColores[0] = new Color(0, 0, 0, 0);
        //Punto dispersión
        listaColores[100] = new Color(255, 0, 0, 100);
        //Punto hot
        listaColores[200] = new Color(255, 255, 0, 255);
        //Punto xtrahot
        listaColores[254] = new Color(255, 255, 255, 255);

        crearColores(listaColores, 0, 100);
        crearColores(listaColores, 100, 200);
        crearColores(listaColores, 200, 254);


        Random r = new Random();
        //TODO varios colores 3 o 4 para cada temperatura esencial y luego
        // Paleta de colores


        // Creación de la matriz de temperatura
        data = new int[getWidth()][getHeight()];
        data2 = new int[data.length][data[0].length];

        // Inicialización de los puntos de llama en el fondo de la pantalla
        for (int i = 0; i < data.length; i++) {
            try {
                for (int j = data[0].length - 4; j < data[0].length; j++) {
                    int porciento = r.nextInt(101);
                    //VALOR MODIFICABLE EN UN SLIDER
                    if (porciento < 50) {
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

        // Propagación del fuego
        for (int i = 1; i < data.length - 1; i++) {
            for (int j = data[0].length - 2; j >= 0; j--) {
                int porciento = r.nextInt(101);
                //VALOR MODIFICABLE EN UN SLIDER
                if (porciento < 95) {
                    data2[i][j] = data[i][j];

                    canvasGraphics.setColor(listaColores[data[i][j]]);
                    data[i + 1][j] = (data[i][j + 1] + data[i - 1][j] + data[i][j + 1] + data[i + 1][j + 1]) / 4;
                    // Copia los datos a la matriz temporal antes de hacer los cálculos
                    canvasGraphics.drawRect(i, j, 1, 1);


                }
            }
        }

        // Copia los datos de la matriz temporal de vuelta a la matriz de temperatura

        data2 = data;
        paint(this.getGraphics());
        repaint();
    }


    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void run() {
        while (true) {
            Pintar(getGraphics());
        }
    }
}
