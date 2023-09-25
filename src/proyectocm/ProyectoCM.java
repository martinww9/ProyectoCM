package proyectocm;

public class ProyectoCM {

    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        VentanaMenu ventana = new VentanaMenu(sistema);
        ventana.setVisible(true);
        sistema.mostrarMenu();
        
    }
}
