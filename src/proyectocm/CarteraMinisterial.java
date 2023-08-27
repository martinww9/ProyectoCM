package proyectocm;
import java.util.*;

class CarteraMinisterial {
    private String nombre;
    private ArrayList<Funcionario> funcionarios;
    private String encargado;

    public CarteraMinisterial(String nombre, String encargado) {
        this.nombre = nombre;
        this.encargado = encargado;
        funcionarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    
    public void mostrarFuncionarios() {
        System.out.println("Funcionarios:");
        for (int i= 0; i < funcionarios.size(); i++) {
            System.out.println(" - " + funcionarios.get(i).getNombre());
        }
    }
}