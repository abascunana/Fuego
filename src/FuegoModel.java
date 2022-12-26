import java.awt.*;

import java.util.ArrayList;
import java.util.Random;

public class FuegoModel extends Canvas implements Runnable {
    private int[][] data ;
    private Graphics2D canvasGraphics;

    public void Pintar() {
        this.setBackground(Color.white);


        canvasGraphics = (Graphics2D) this.getGraphics();


        Random r = new Random();
        System.out.println(this.getWidth());

        data = new int[getWidth()][this.getHeight()];
        //Puntos de llama inicial
        ArrayList<Color> colors = new ArrayList<>();
        //paleta de colores
        for (int i = 0; i <256 ; i++) {
            Color color = new Color(255,i,i,255);
            colors.add(color);
        }
 /*       Color[] colors2 = {
                new Color(255, 255, 0),
                new Color(255, 128, 0),
                new Color(255, 0, 0),
                new Color(255, 255, 255)
        };*/

        //ESTO LO TENDRÉ QUE MODIFICAR
        for (int i = 1; i < getWidth()-1; i++) {

            for (int j = 1; j < 255; j++) {
                //Valor espurnas
                int porciento = r.nextInt(100);
                if (porciento < 20){
                     data[i][j] = ((data[i][j] + data[i+1][j] + data[i-1][j] + data[i][j+1])/4);
                    //Intensidad en la paleta de colores (colors)
                    canvasGraphics.setColor(colors.get(data[i][j]));
                    canvasGraphics.drawRect(i,j, 1,1);


                }

            }


            }





        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void run() {
    while (true){
        Pintar();
        repaint();
    }


    }
}




