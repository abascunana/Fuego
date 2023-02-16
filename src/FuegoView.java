import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FuegoView extends JFrame implements ActionListener,ChangeListener{
    private JButton bkill;
    private JButton bback;
    private JButton f1;
    private JSlider expansion;
    //TODO FUEGO PORCENTAJE INICIO
    //TODO FUEGO DISPERSIÓN
    //TODO FUEGO COLORES (3 COLORES IMPLICADOS POR AHORA)
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

        setSize(50000,50000);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,700));
        Componentes();
        Thread thread = new Thread(fuegoModel);
        Thread threadc = new Thread(fuegoController);
        getContentPane().add(fuegoModel, BorderLayout.CENTER);
        this.setVisible(true);
        createBufferStrategy(2);

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
   /*
        botones.add(bback,c);*/
        botones.add(bback);
        f1 = new JButton("Fuego");
        f1.addActionListener(this);
        botones.add(f1);
        colorChooser = new JColorChooser();
      c.gridx=0;
        c.gridy=0;
        controlador.add(colorChooser,c);
        c.gridy =1;
        controlador.add(botones,c);
        c.gridy=2;
        expansion = new JSlider(0, 100, 95);
        expansion.setPaintTrack(true);
        expansion.setPaintTicks(true);
        expansion.setPaintLabels(true);
        expansion.setMajorTickSpacing(100);
        expansion.setMinorTickSpacing(1);
        expansion.addChangeListener(this);
        controlador.add(expansion,c);
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
        if (e.getSource() == this.f1) {
            fuegoController.setColorFr(colorChooser.getColor());

        }
        }


    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.expansion){
            fuegoController.setExpansion(expansion.getValue());
        }
    }
}