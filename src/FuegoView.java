import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FuegoView extends JFrame implements Runnable{
//Crear
    public FuegoView(){
        CrearMiventana();
    }
    public void CrearMiventana(){
        setTitle("Java Fuego");

        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        FuegoModel fuegoModel = new FuegoModel();
        this.setVisible(true);
        Thread thread = new Thread(fuegoModel);
        getContentPane().add(fuegoModel);
        thread.start();




    }



    @Override
    public void run() {

    }
}