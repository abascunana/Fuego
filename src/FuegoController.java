import java.awt.*;

public class FuegoController implements Runnable{
    private Color colorBC;

    public void setPixelarriba(double pixelarriba) {
        this.pixelarriba = pixelarriba;
    }

    private double pixelarriba;

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    private int inicio;
    private Color colorFr; //3 colores
    private FuegoModel model;
    private boolean deadFire = false;

    public void setDeadFire() {
        this.deadFire = !this.deadFire;
    }




    public FuegoModel getModel() {
        return model;
    }

    public void setModel(FuegoModel model) {
        this.model = model;
    }

    public FuegoController(FuegoModel model) {
        this.model = model;
    }

    public void setColorBC(Color colorBC) {
        this.colorBC = colorBC;
    }

    public void setColorFr(Color colorFr) {
        this.colorFr = colorFr;
    }

    @Override
    public void run() {
        while (true){
            getModel().setColorFr(colorFr);
            getModel().setColorBC(colorBC);
            getModel().setDeadFire(deadFire);
            getModel().setPixelarriba(pixelarriba);
            getModel().setInicio(inicio);
        }

    }
}
