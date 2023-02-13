import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FuegoModel extends Canvas implements Runnable {
    private int[][] data;
    private Graphics2D canvasGraphics;
    private int[][] data2;
    private Color colorBC;
    private Color colorFr;

    private boolean deadFire;
    public void setColorFr(Color colorFr) {
        this.colorFr = colorFr;
    }

    public void setDeadFire(boolean deadFire) {
        this.deadFire = deadFire;
    }


    public void setColorBC(Color colorBC) {
        this.colorBC = colorBC;
    }

    public void Pintar(Graphics g) {
        setSize(new Dimension(400,400));
        canvasGraphics = (Graphics2D) g;
        if (colorBC!=null){
            this.setBackground(colorBC);
        }
        else {
            this.setBackground(Color.black);
        }

        Random r = new Random();

        // Paleta de colores
        ArrayList<Color> colors = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            int alpha = i;
            Color color;
            if (colorFr != null){
                color = new Color(colorFr.getRed(), colorFr.getGreen(), colorFr.getBlue(), alpha);
            }
            else {
                color = new Color(255, 0, 0, alpha);
            }

            colors.add(color);
        }

        // Creación de la matriz de temperatura
        data = new int[getWidth()][getHeight()];
        data2 = new int[data[0].length][data[1].length];

        // Inicialización de los puntos de llama
        for (int i = 0; i < data[0].length; i++) {
            try {
                for (int j =data[1].length-10; j < data[1].length; j++) {
                    int porciento = r.nextInt(100);
                    if (porciento < 20) {
                        if (!deadFire){
                            data[i][j] = 255;
                        }

                    }

                }
            }
            catch (ArrayIndexOutOfBoundsException e){

            }

        }

        // Propagación del fuego
        for (int i = 1; i < data[0].length-1; i++) {
            for (int j = data[1].length-4 ; j >= 0; j--) {
                int porciento = r.nextInt(101);
                if (porciento < 97) {
                    data2[i][j] = data[i][j];
                    canvasGraphics.setColor(colors.get(data2[i][j]));
                    data[i+1][j] = (data[i][j+1] + data[i-1][j] + data[i][j + 1] + data[i+1][j + 1]) / 4;
                    // Copia los datos a la matriz temporal antes de hacer los cálculos
                    canvasGraphics.drawRect(i, j, 1, 10);


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
