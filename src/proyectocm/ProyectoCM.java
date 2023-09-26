package proyectocm;

public class ProyectoCM {
      
    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        sistema.mostrarMenu();
        
    }
}
