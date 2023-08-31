package proyectocm;
import java.util.*;

class CarteraMinisterial {
    private String nombre;
    private ArrayList<Funcionario> funcionarios;
    private HashMap<String, Funcionario> funcionariosMapa;
    private String encargado;

    public CarteraMinisterial(String nombre, String encargado) {
        this.nombre = nombre;
        this.encargado = encargado;
        funcionarios = new ArrayList<>();
        funcionariosMapa = new HashMap<>();
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

    public HashMap<String, Funcionario> getFuncionariosMapa() {
        return funcionariosMapa;
    }

    public void setFuncionariosMapa(HashMap<String, Funcionario> funcionariosMapa) {
        this.funcionariosMapa = funcionariosMapa;
    }
    
    public void mostrarFuncionarios() {
        System.out.println("Funcionarios:");
        for (int i= 0; i < funcionarios.size(); i++) {
            System.out.print((i+1) + ". " + funcionarios.get(i).getNombre());
            System.out.print(" Cargo: " + funcionarios.get(i).getCargo());
            System.out.println(" ID: " + funcionarios.get(i).getID());
        }
    }
    public void agregarFuncionario(String nombreFuncionario, String cargo, String id) {
        if(funcionariosMapa.containsKey(id)) {
             System.out.print("Funcionario ya esta registrado.");
        } else {
            Funcionario funcionario = new Funcionario(nombreFuncionario, cargo, id);
            funcionariosMapa.put(id, funcionario);
            funcionarios.add(funcionario);          
            System.out.println("Funcionario agregado: " + funcionariosMapa.get(id).getNombre());
        }          
    }