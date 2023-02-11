import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FuegoView extends JFrame implements Runnable, ActionListener {
    private JButton bkill;
    private JButton bback;
    private JButton bfire;
    private JColorChooser colorChooser;
    private FuegoModel fuegoModel;
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

        Componentes();
        fuegoModel = new FuegoModel();

        Thread thread = new Thread(fuegoModel);
        getContentPane().add(fuegoModel);
        this.setVisible(true);
        thread.start();




    }
    public void Componentes(){
        GridBagConstraints c = new GridBagConstraints();
        bkill = new JButton("KillFire");
        bkill.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        this.add(bkill,c);
        bback = new JButton("SetBack");
        bback.addActionListener(this);
        c.gridx=1;
        c.gridy=0;
        this.add(bback,c);
        bfire = new JButton("SetFireColor");
        bfire.addActionListener(this);
        c.gridx=2;
        c.gridy=0;
        this.add(bfire,c);
        colorChooser = new JColorChooser();
        c.gridx=1;
        c.gridy=1;
        this.add(colorChooser,c);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.bback) {
        fuegoModel.setColorBC(colorChooser.getColor());

        }
        if (e.getSource() == this.bfire) {
            fuegoModel.setColorFr(colorChooser.getColor());

        }

        }



    @Override
    public void run() {
        //Enviar todo
    }
}