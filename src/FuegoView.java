import javax.swing.*;

public class FuegoView extends JFrame {
//Crear

    public FuegoView(){
        CrearMiventana();
    }
    public void CrearMiventana(){
        setTitle("Miventana");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        FuegoModel fuego = new FuegoModel();
        this.getContentPane().add(fuego);
        this.setVisible(true);
        fuego.run();



    }
}