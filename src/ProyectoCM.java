

import javax.swing.JOptionPane;

public class ProyectoCM {
      
    public static void main(String[] args) {    
        try {
            Sistema sistema = new Sistema();
            VentanaPrincipal ventana = new VentanaPrincipal(sistema);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            sistema.cargarDatosDesdeCSV();
            //sistema.mostrarMenu();
        } catch (CarteraExistsException e) {
            JOptionPane.showMessageDialog(null, "Error: La cartera ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

