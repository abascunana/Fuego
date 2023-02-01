import javax.swing.*;
import java.awt.*;

public class FuegoView extends JFrame implements Runnable{
//Crear

    public FuegoView(){
        CrearMiventana();
    }
    public void CrearMiventana(){
        setTitle("Miventana");
        //setLayout(new GridBagLayout());
       /* GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        //cada elemento se genera en el elemento y
        c.weighty = 0;
        c.weightx = 0;*/
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        FuegoModel fuego = new FuegoModel();
        getContentPane().add(fuego);

        this.setVisible(true);
        fuego.run();




    }

    @Override
    public void run() {

    }
}