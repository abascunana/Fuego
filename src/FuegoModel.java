import java.awt.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Random;

public class FuegoModel extends Canvas implements Runnable{
    private int[][] data ;
    private Graphics2D canvasGraphics;

    public void Pintar() {

        this.setBackground(Color.white);
        canvasGraphics = (Graphics2D) this.getGraphics();
        Random r = new Random();
        System.out.println(this.getWidth());
        //Puntos de llama inicial
        ArrayList<Color> colors = new ArrayList<>();
        //paleta de colores, hace falta modificar para realismo
        for (int i = 0; i <256 ; i++) {

            Color color = new Color(255,255-i,255-i,255);
            colors.add(color);
        }
        //Con this
        data = new int[1920][1080];

        for (int i = 1; i < getWidth()-1; i++) {
            for (int j = 1; j < 254; j++) {
                //Valor espurnas
                int porciento = r.nextInt(100);
                if (porciento < 20){

                    data[i][j] = ((data[i][j] + data[i+1][j] + data[i-1][j] + data[i][j+1])/4);
                    //Intensidad en la paleta de colores (colors)
                    canvasGraphics.setColor(colors.get(j));
                    canvasGraphics.drawRect(i,j, 1,1);


                }

            }


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




