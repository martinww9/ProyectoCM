
import java.util.*;

class Sistema {
    private ArrayList<CarteraMinisterial> carteras;
    private HashMap<String,CarteraMinisterial> carterasMapa;
    private Scanner scanner;

    public Sistema()  {
        carteras = new ArrayList<>();
        scanner = new Scanner(System.in);
        carterasMapa = new HashMap<>();
        //inicializarCarteras(); 
    }   
    
    //Sobre carga de metodo
public void agregarCartera(CarteraMinisterial cartera) throws CarteraExistsException {
    String nombreCartera = cartera.getNombre();
    
    if (carterasMapa.containsKey(nombreCartera)) {
        throw new CarteraExistsException("Cartera ya existente: " + nombreCartera);
    } else {
        carterasMapa.put(nombreCartera, cartera);
        carteras.add(cartera);
        System.out.println("Cartera agregada: " + nombreCartera);
    }
}

    
        public void agregarCartera(String nombreCartera) throws CarteraExistsException {
            CarteraMinisterial nuevaCartera = new CarteraMinisterial(nombreCartera, "Sin encargado");
            agregarCartera(nuevaCartera);
        }
        
        public void mostrarCarteras() {
        System.out.println("\nCarteras Ministeriales:");
        for (int i= 0; i < carteras.size(); i++) {
            System.out.println( (i+1) + ". " + carteras.get(i).getNombre());
            System.out.print("Encargado:");
            System.out.println(" - " + carteras.get(i).getEncargado());
            carteras.get(i).mostrarFuncionarios();
            System.out.println("");
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

        public void agregarFuncionarioConsola(String nombreFuncionario, String cargo, String id , String nombreCartera){
            if(carterasMapa.containsKey(nombreCartera)){
                Funcionario funcionario = new Funcionario(nombreFuncionario, cargo, id);
                carterasMapa.get(nombreCartera).agregarFuncionario(funcionario);
            }else{
                System.out.println("Cartera no existe");
            }   
        }
        
        public void reubicarFuncionario(String idFuncionario, String nombreCarteraActual, String nombreCarteraDestino) throws CarteraNotFoundException, FuncionarioNotFoundException {
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
        
        public void cambiarCargoFuncionario(String idFuncionario, String carteraFuncionario, String cargoNuevo)throws CarteraNotFoundException, FuncionarioNotFoundException {
            CarteraMinisterial carteraAct = buscarCartera(carteraFuncionario);
            //Verificar existencia de cartera
            if (carteraAct != null){
                Funcionario funcionario = carteraAct.buscarFuncionario(idFuncionario);
                //Verificar existencia de funcionario
                if (funcionario != null){
                    //Cambiar cargo del funcionario
                    funcionario.setCargo(cargoNuevo);
                }
            }
        }
        
        public void cargarDatosDesdeCSV()throws CarteraExistsException {
       Set<String> carterasAgregadas = new HashSet<>();
       List<String[]> datos = CSVDataManager.cargarDatos();

       for (String[] fila : datos) {
           String nombreCartera = fila[0];
           String encargado = fila[1];
           String nombreFuncionario = fila[2];
           String cargoFuncionario = fila[3];
           String idFuncionario = fila[4];

           // Verificar si la cartera y el encargado ya han sido agregados
           String carteraEncargadoKey = nombreCartera + "-" + encargado;

           if (carterasAgregadas.contains(carteraEncargadoKey)) {
               // La cartera ya existe, agrega el funcionario a esa cartera
               agregarFuncionarioConsola(nombreFuncionario, cargoFuncionario, idFuncionario, nombreCartera);
           } else {
               // La cartera no existe, crea la cartera y agrega el funcionario
               CarteraMinisterial carteraAux = new CarteraMinisterial(nombreCartera, encargado);
               agregarCartera(carteraAux);
               agregarFuncionarioConsola(nombreFuncionario, cargoFuncionario, idFuncionario, nombreCartera);

               // Agrega la cartera y el encargado al conjunto de seguimiento
               carterasAgregadas.add(carteraEncargadoKey);
            }
        }
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
