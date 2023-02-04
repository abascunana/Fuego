import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FuegoModel extends Canvas implements Runnable {
    private int[][] data;
    private int[][] data2;

    private Graphics2D canvasGraphics;
    private Image backgroundImage;

    public FuegoModel() {
        try {
            backgroundImage = ImageIO.read(new File("src/props/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Pintar() {
        this.setBackground(Color.black);

        canvasGraphics = (Graphics2D) this.getGraphics();
        Random r = new Random();

        // Paleta de colores
        ArrayList<Color> colors = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            int alpha = i;
            Color color = new Color(255, 0, 0, i);
            colors.add(color);
        }

        // Creación de la matriz de temperatura
        data = new int[1920][1080];


        // Inicialización de los puntos de llama
        for (int i = 0; i < getWidth(); i++) {
            try {
                for (int j = getHeight()-10; j < getHeight(); j++) {
                    int porciento = r.nextInt(100);
                    if (porciento < 10) {
                        data[i][j] = 255;
                    }

                }
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("PANTALLA FUERA DE RANGO");
            }

        }

        for (int i = 1; i < getWidth()-1; i++) {
            for (int j = getHeight()-4 ; j >= 0; j--) {
                int porciento = r.nextInt(100);
                if (porciento < 100) {

                    data[i+1][j] = (data[i][j+1] + data[i-1][j] + data[i][j + 1] + data[i+1][j + 1]) / 4;
                    data2 = data;
                    canvasGraphics.setColor(colors.get(data2[i][j]));
                    canvasGraphics.drawRect(i, j, 1, 100);
                }
            }
        }

        // Propagación del fuego

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true) {
            Pintar();
            repaint();
        }
    }
}
