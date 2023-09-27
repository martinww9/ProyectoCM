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

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    
    public void mostrarFuncionarios() {
        System.out.println("Funcionarios:");
        for (int i= 0; i < funcionarios.size(); i++) {
            System.out.print((i+1) + ") " + funcionarios.get(i).getNombre());
            System.out.print(" Cargo: " + funcionarios.get(i).getCargo());
            System.out.println( " ID: " + funcionarios.get(i).getID());
        }
    }
    public void agregarFuncionario(String nombreFuncionario, String cargo, String id) throws FuncionarioExistsException {
        
            for (Funcionario funcionario : funcionarios) {
            if (funcionario.getID().equals(id)) {
                throw new FuncionarioExistsException("Funcionario con el mismo ID ya existe en la cartera.");
            }
        }

       
        Funcionario funcionario = new Funcionario(nombreFuncionario, cargo, id);
        funcionariosMapa.put(id, funcionario);
        funcionarios.add(funcionario);
}

    public void agregarFuncionario(Funcionario funcionario){
        String idFuncionario = funcionario.getID();
       if(funcionariosMapa.containsKey(idFuncionario)){
       System.out.println("\nFuncionario ya existente");
       }else{
        funcionariosMapa.put(idFuncionario, funcionario);
        funcionarios.add(funcionario);
        System.out.println("Funcionario agregado: " + funcionariosMapa.get(idFuncionario).getNombre());
       }
   }
    public Funcionario buscarFuncionario(String id) throws FuncionarioNotFoundException {
        if (funcionariosMapa.containsKey(id)) {
            System.out.println("Funcionario encontrado: " + funcionariosMapa.get(id).getNombre());
            return funcionariosMapa.get(id);
        } else {
            throw new FuncionarioNotFoundException("Funcionario no encontrado de ID: " + id);
        }
    }
        
        public Funcionario eliminarFuncionario (String id) throws FuncionarioNotFoundException{
            Funcionario funcionarioEliminado = buscarFuncionario(id);
            if(funcionarioEliminado == null) {
                return null;
            }
            
            funcionariosMapa.remove(id);
            
             for (int i = 0; i < funcionarios.size(); i++){
                 if(funcionarios.get(i).getID().equals(id)) {
                    funcionarios.remove(i);
                    break; 
                 }
             }
             System.out.println("Funcionario eliminado con exito");
             return funcionarioEliminado;
        }
        public List<Funcionario> obtenerFuncionarios() {
    return new ArrayList<>(funcionariosMapa.values());
}
}
