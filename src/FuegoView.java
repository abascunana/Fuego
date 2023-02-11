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
        setSize(700,700);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,700));
        FuegoModel fuegoModel = new FuegoModel();

        Thread thread = new Thread(fuegoModel);
        getContentPane().add(fuegoModel);
        this.setVisible(true);
        thread.start();




    }




    @Override
    public void run() {

    }
}