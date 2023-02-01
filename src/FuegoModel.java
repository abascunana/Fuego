import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class FuegoModel extends Canvas implements Runnable {
    private int[][] data;
    private Graphics2D canvasGraphics;

    public void Pintar() {
        this.setBackground(Color.gray);
        canvasGraphics = (Graphics2D) this.getGraphics();
        Random r = new Random();

        // Paleta de colores
        ArrayList<Color> colors = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            int alpha = i;
            Color color = new Color(255, 0, 0, alpha);
            colors.add(color);
        }

        // Creación de la matriz de temperatura
        data = new int[1920][1080];

        // Inicialización de los puntos de llama
        for (int i = 0; i < getWidth(); i++) {
            for (int j = getHeight()-5; j < getHeight(); j++) {
                int porciento = r.nextInt(100);
                if (porciento < 20) {
                data[i][j] = 255;
                }

            }
        }

        // Propagación del fuego
        for (int i = 1; i < getWidth()-1; i++) {
            for (int j = getHeight()-4; j >= 0; j--) {
                int porciento = r.nextInt(100);
                if (porciento < 80) {
                    data[i][j] = (data[i - 1][j + 1] + data[i][j + 1] + data[i + 1][j + 1] + data[i][j + 1]) / 4;
                    canvasGraphics.setColor(colors.get(data[i][j]));
                    canvasGraphics.drawRect(i, j, 1, 1);
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            Pintar();
            repaint();
        }
    }
}
