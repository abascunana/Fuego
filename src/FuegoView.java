import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FuegoView extends JFrame implements ActionListener {
    private JButton bkill;
    private JButton bback;
    private JButton bfire;
    private JColorChooser colorChooser;
    private FuegoModel fuegoModel;


    public FuegoController getFuegoController() {
        return fuegoController;
    }

    public void setFuegoController(FuegoController fuegoController) {
        this.fuegoController = fuegoController;
    }

    private FuegoController fuegoController;

    //Crear
    public FuegoView(FuegoModel fuegoModel)
    {this.fuegoModel = fuegoModel;
        setFuegoController(fuegoModel.getFuegoController());
        CrearMiventana();
    }
    public void CrearMiventana(){
        setTitle("Java Fuego");
        setSize(700,700);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,700));
        Componentes();
        Thread thread = new Thread(fuegoModel);
        Thread threadc = new Thread(fuegoController);
        getContentPane().add(fuegoModel);
        this.setVisible(true);
        thread.start();
        threadc.start();




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
        fuegoController.setColorBC(colorChooser.getColor());

        }
        if (e.getSource() == this.bfire) {
            fuegoController.setColorFr(colorChooser.getColor());

        }
        if (e.getSource() == this.bkill){
            fuegoController.setDeadFire();
        }

        }




}