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
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,700));
        Componentes();
        Thread thread = new Thread(fuegoModel);
        Thread threadc = new Thread(fuegoController);
        getContentPane().add(fuegoModel, BorderLayout.CENTER);
        this.setVisible(true);
        thread.start();
        threadc.start();




    }
    public void Componentes(){
        JPanel botones = new JPanel();
        JPanel controlador = new JPanel();

       controlador.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
        bkill = new JButton("KillFire");
        bkill.addActionListener(this);
       /* c.gridx=0;
        c.gridy=1;
        botones.add(bkill,c);*/
        botones.add(bkill);
        bback = new JButton("SetBack");

        bback.addActionListener(this);
   /*     c.gridx=1;
        c.gridy=1;
        botones.add(bback,c);*/
        botones.add(bback);

        colorChooser = new JColorChooser();
      c.gridx=0;
        c.gridy=0;
        controlador.add(colorChooser,c);

        c.gridy =1;
        controlador.add(botones,c);
        this.getContentPane().add(controlador, BorderLayout.WEST);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.bback) {
        fuegoController.setColorBC(colorChooser.getColor());

        }

        if (e.getSource() == this.bkill){
            fuegoController.setDeadFire();
        }

        }

//cHANGE LISTENER PARA LOS SLIDERS


}