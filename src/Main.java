public class Main {
    public static void main(String[] args) {


        FuegoModel fuegoModel = new FuegoModel();
        FuegoController fuegoController = new FuegoController(fuegoModel);
        fuegoModel.setFuegoController(fuegoController);
        FuegoView fuegoView = new FuegoView(fuegoModel);
    }
}
