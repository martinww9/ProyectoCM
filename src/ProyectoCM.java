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
            JOptionPane.showMessageDialog(null, "Error: Cartera ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (CarteraNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cartera no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FuncionarioExistsException ex) {
            JOptionPane.showMessageDialog(null, "Error: Funcionario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

