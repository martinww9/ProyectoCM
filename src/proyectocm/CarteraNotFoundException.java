package proyectocm;

public class CarteraNotFoundException extends Exception {
    public CarteraNotFoundException( String par) {
        super("Cartera no encontrada");
    }
}
