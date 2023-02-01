import javax.swing.*;
import java.awt.*;

public class FuegoView extends JFrame implements Runnable{
//Crear

    public FuegoView(){
        CrearMiventana();
    }
    public void CrearMiventana(){
        setTitle("Miventana");
//        this.setLayout(new GridBagLayout());
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        FuegoModel fuego = new FuegoModel();
        this.getContentPane().add(fuego);
        this.add(fuego);
        this.setVisible(true);
        fuego.run();



    }

    @Override
    public void run() {

    }
}