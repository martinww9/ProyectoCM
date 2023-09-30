
import java.util.*;

class Sistema {
    private ArrayList<CarteraMinisterial> carteras;
    private HashMap<String,CarteraMinisterial> carterasMapa;

    public Sistema()  {
        carteras = new ArrayList<>();
        carterasMapa = new HashMap<>(); 
    }   
    
    //Sobre carga de metodo
public void agregarCartera(CarteraMinisterial cartera) throws CarteraExistsException {
    String nombreCartera = cartera.getNombre();
    
    if (carterasMapa.containsKey(nombreCartera)) {
        throw new CarteraExistsException("Cartera ya existente: " + nombreCartera);
    } else {
        carterasMapa.put(nombreCartera, cartera);
        carteras.add(cartera);
    }
}

    
        public void agregarCartera(String nombreCartera) throws CarteraExistsException {
            CarteraMinisterial nuevaCartera = new CarteraMinisterial(nombreCartera, "Sin encargado");
            agregarCartera(nuevaCartera);
        }
        
        public void mostrarCarteras() {
    System.out.println("\nCarteras Ministeriales:");
    for (int i = 0; i < carteras.size(); i++) {
        System.out.println((i + 1) + ". " + carteras.get(i).toString()); // Llamada a toString
        System.out.println(""); // Agrega una línea en blanco entre carteras
    }
}

        
public CarteraMinisterial buscarCartera(String nombreCartera) throws CarteraNotFoundException {
    if (carterasMapa.containsKey(nombreCartera)) {
        System.out.println("Cartera encontrada: " + carterasMapa.get(nombreCartera).getNombre());
        return carterasMapa.get(nombreCartera);
    } else {
        throw new CarteraNotFoundException("Cartera no encontrada: " + nombreCartera);
    }
}
        
        public CarteraMinisterial eliminarCartera(String nombreCartera) throws CarteraNotFoundException{
            CarteraMinisterial carteraEliminada = buscarCartera(nombreCartera);
            if(carteraEliminada == null) {
                return null;
            }
            
            carterasMapa.remove(nombreCartera);
            
             for (int i = 0; i < carteras.size(); i++){
                 if(carteras.get(i).getNombre().equals(nombreCartera)) {
                    carteras.remove(i);
                    break; 
                 }
             }
             System.out.println("Cartera eliminada con exito");
             return carteraEliminada;
        }

        public void agregarFuncionarioCarteraEspecifica(String nombreFuncionario, String cargo, String id, String nombreCartera) throws CarteraNotFoundException, FuncionarioExistsException {
            if (carterasMapa.containsKey(nombreCartera)) {
                Funcionario funcionario = new Funcionario(nombreFuncionario, cargo, id);
                carterasMapa.get(nombreCartera).agregarFuncionario(funcionario);
            } else {
                throw new CarteraNotFoundException("Cartera no encontrada: " + nombreCartera);
            }
        }
        
        public void reubicarFuncionario(String idFuncionario, String nombreCarteraActual, String nombreCarteraDestino) throws CarteraNotFoundException, FuncionarioNotFoundException, FuncionarioExistsException {
            CarteraMinisterial carteraActual = buscarCartera(nombreCarteraActual);
            CarteraMinisterial carteraDestino = buscarCartera(nombreCarteraDestino);

            if (carteraActual != null && carteraDestino != null) {
                    Funcionario funcionario = carteraActual.buscarFuncionario(idFuncionario);
                    if (funcionario != null) {
                        // Eliminar el funcionario de la cartera de origen
                        carteraActual.eliminarFuncionario(idFuncionario);

                        // Agregar el funcionario a la cartera de destino
                        carteraDestino.agregarFuncionario(funcionario);

                        System.out.println("Funcionario reubicado con éxito.");
                    } else {
                        System.out.println("Funcionario no encontrado en la cartera de origen.");
                    }
                } else {
                System.out.println("Una de las carteras especificadas no existe.");
            }
        }
        
        public void cambiarCargoFuncionario(String idFuncionario, String cargoNuevo) throws FuncionarioNotFoundException {
            boolean encontrado = false;
            
                Funcionario funcionario = buscarFuncionarioEnTodasLasCarteras(idFuncionario);
                if (funcionario != null) {
                    funcionario.setCargo(cargoNuevo);
                    encontrado = true;
                }
            if (!encontrado) {
                throw new FuncionarioNotFoundException("Funcionario no encontrado de ID: " + idFuncionario);
            }
}
        public void cargarDatosDesdeCSV()throws CarteraExistsException, CarteraNotFoundException, FuncionarioExistsException {
       Set<String> carterasAgregadas = new HashSet<>();
       List<String[]> datos = CSVDataManager.cargarDatos();

       for (String[] fila : datos) {
           String nombreCartera = fila[0];
           String encargado = fila[1];
           String nombreFuncionario = fila[2];
           String cargoFuncionario = fila[3];
           String idFuncionario = fila[4];

           // Verificar si la cartera y el encargado ya han sido agregados
           String carteraEncargadoKey = nombreCartera;

           if (carterasAgregadas.contains(carteraEncargadoKey)) {
               // La cartera ya existe, agrega el funcionario a esa cartera
               agregarFuncionarioCarteraEspecifica(nombreFuncionario, cargoFuncionario, idFuncionario, nombreCartera);
           } else {
               // La cartera no existe, crea la cartera y agrega el funcionario
               CarteraMinisterial carteraAux = new CarteraMinisterial(nombreCartera, encargado);
               agregarCartera(carteraAux);
               agregarFuncionarioCarteraEspecifica(nombreFuncionario, cargoFuncionario, idFuncionario, nombreCartera);

               // Agrega la cartera y el encargado al conjunto de seguimiento
               carterasAgregadas.add(carteraEncargadoKey);
            }
        }
    }
        
     public List<Funcionario> buscarFuncionariosPorCargoEnTodasLasCarteras(String cargo) {
        List<Funcionario> funcionariosEncontrados = new ArrayList<>();
        for (CarteraMinisterial cartera : carteras) {
            List<Funcionario> funcionariosFiltrados = cartera.filtrarFuncionariosPorCargo(cargo);
            funcionariosEncontrados.addAll(funcionariosFiltrados);
        }
        return funcionariosEncontrados;
    }

        public List<CarteraMinisterial> obtenerCarteras() {
    return new ArrayList<>(carterasMapa.values());
                    
    }
        
public Funcionario buscarFuncionarioEnTodasLasCarteras(String idFuncionario) throws FuncionarioNotFoundException {
    for (CarteraMinisterial cartera : carteras) {
        try {
            Funcionario funcionario = cartera.buscarFuncionario(idFuncionario);
            if (funcionario != null) {
                return funcionario;
            }
        } catch (FuncionarioNotFoundException ex) {
         }
    }
    throw new FuncionarioNotFoundException("Funcionario no encontrado en ninguna cartera.");
}

public CarteraMinisterial obtenerCarteraDelFuncionario(String idFuncionario) throws FuncionarioNotFoundException {
    for (CarteraMinisterial cartera : carteras) {
        try {
        Funcionario funcionario = cartera.buscarFuncionario(idFuncionario);
        if (funcionario != null) {
            return cartera;
        }
            } catch (FuncionarioNotFoundException ex) {
             }
    }
    throw new FuncionarioNotFoundException("Funcionario no encontrado en ninguna cartera.");
}
        
public void guardarDatosACSV() {
    List<String[]> datos = new ArrayList<>();

    for (CarteraMinisterial cartera : carteras) {
        String nombreCartera = cartera.getNombre();
        String encargado = cartera.getEncargado();

        List<Funcionario> funcionarios = cartera.obtenerFuncionarios();

        for (Funcionario funcionario : funcionarios) {
            String nombreFuncionario = funcionario.getNombre();
            String cargoFuncionario = funcionario.getCargo();
            String idFuncionario = funcionario.getID();

            String[] fila = {nombreCartera, encargado, nombreFuncionario, cargoFuncionario, idFuncionario};
            datos.add(fila);
        }
    }

    CSVDataManager.guardarDatos(datos);
    System.out.println("Datos guardados en el archivo CSV.");
}

}
