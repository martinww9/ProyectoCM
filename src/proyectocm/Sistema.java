package proyectocm;
import java.util.*;

class Sistema {
    private ArrayList<CarteraMinisterial> carteras;
    private HashMap<String,CarteraMinisterial> carterasMapa;
    private Scanner scanner;

    public Sistema() {
        carteras = new ArrayList<>();
        scanner = new Scanner(System.in);
        carterasMapa = new HashMap<>();
        //inicializarCarteras();
        cargarDatosDesdeCSV();
    }   
    private void inicializarCarteras(){
        CarteraMinisterial cartera1 = new CarteraMinisterial("Salud", "Jose Lopez");
        CarteraMinisterial cartera2 = new CarteraMinisterial("Educacion", "Maria Perez");
        CarteraMinisterial cartera3 = new CarteraMinisterial("Bienes nacionales", "Sofia Ramirez");
        CarteraMinisterial cartera4 = new CarteraMinisterial("Medioambiente", "Carlos Perez");
        CarteraMinisterial cartera5 = new CarteraMinisterial("Relaciones exteriores", "Pedro Ortiz");
        CarteraMinisterial cartera6 = new CarteraMinisterial("Defensa nacional", "Andrea Quinteros");
        CarteraMinisterial cartera7 = new CarteraMinisterial("Hacienda", "Oscar Zamora");
        CarteraMinisterial cartera8 = new CarteraMinisterial("Agricultura", "Karen Ibañez");
        CarteraMinisterial cartera9 = new CarteraMinisterial("Mineria", "Camila Wagner");
        CarteraMinisterial cartera10 = new CarteraMinisterial("Deporte", "Hector Aravena");
        cartera1.agregarFuncionario("Juan Perez", "Ministro", "12345678-9");
        cartera2.agregarFuncionario("Maria Perez", "Ministro", "213456789-9");
        cartera3.agregarFuncionario("Sofia Ramirez", "Ministro", "312456789-9");
        cartera4.agregarFuncionario("Carlos Perez", "Ministro", "412356789-9");
        cartera5.agregarFuncionario("Pedro Ortiz", "Ministro", "512346789-9");
        cartera6.agregarFuncionario("Andrea Quinteros", "Ministro", "612345789-9");
        cartera7.agregarFuncionario("Oscar Zamora", "Ministro", "712345689-9");
        cartera8.agregarFuncionario("Karen Ibañez", "Ministro", "812345679-9");
        cartera9.agregarFuncionario("Camila Wagner", "Ministro", "912345678-9");
        cartera10.agregarFuncionario("Hector Aravena", "Ministro", "12345678-k");
        carterasMapa.put("Salud", cartera1);
        carterasMapa.put("Educacion", cartera2);
        carterasMapa.put("Bienes nacionales", cartera3);
        carterasMapa.put("Medioambiente", cartera4);
        carterasMapa.put("Relaciones exteriores", cartera5);
        carterasMapa.put("Defensa nacional", cartera6);
        carterasMapa.put("Hacienda", cartera7);
        carterasMapa.put("Agricultura", cartera8);
        carterasMapa.put("Mineria", cartera9);
        carterasMapa.put("Deporte", cartera10);
        carteras.add(cartera1);
        carteras.add(cartera2);
        carteras.add(cartera3);
        carteras.add(cartera4);
        carteras.add(cartera5);
        carteras.add(cartera6);
        carteras.add(cartera7);
        carteras.add(cartera8);
        carteras.add(cartera9);
        carteras.add(cartera10);
    }
    
    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar una cartera");
            System.out.println("2. Mostrar todas las carteras");
            System.out.println("3. Buscar una cartera");
            System.out.println("4. Eliminar una cartera");
            System.out.println("5. Agregar un funcionario a una cartera");
            System.out.println("6. Eliminar un funcionario");
            System.out.println("7. Buscar un funcionario en una cartera especifica");
            System.out.println("8. Mostrar todos los funcionarios de una cartera");
            System.out.println("9. Salir");
            System.out.print("Elija una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            String nombreCartera;
            String nombreFuncionario;
            String idFuncionario;
            System.out.println("");
            switch (opcion) {
                case 1:
                        System.out.print("Ingrese el nombre de la cartera: ");
                        nombreCartera = scanner.nextLine();

                        System.out.print("¿Desea agregar un encargado a esta cartera? (Sí/No): ");
                        String respuesta = scanner.nextLine();

                        if (respuesta.equalsIgnoreCase("Si")) {
                            System.out.print("Ingrese el nombre del encargado: ");
                            String encargadoCartera = scanner.nextLine();
                            System.out.print("Ingrese el ID del encargado: ");
                            String encargadoID = scanner.nextLine();

                            Funcionario encargado = new Funcionario(encargadoCartera, "Ministro", encargadoID);
                            CarteraMinisterial nuevaCartera = new CarteraMinisterial(nombreCartera, encargadoCartera);
                            agregarCartera(nuevaCartera);
                            nuevaCartera.agregarFuncionario(encargado); 
                        } else if (respuesta.equalsIgnoreCase("No")) {
                            agregarCartera(nombreCartera);
                        } else {
                            System.out.println("Respuesta no válida. Debe responder 'Sí' o 'No'.");
                        }
                    break;
                case 2:
                    mostrarCarteras();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de la cartera a buscar: ");
                    nombreCartera = scanner.nextLine();
                    buscarCartera(nombreCartera);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre de la cartera a eliminar: ");
                    nombreCartera = scanner.nextLine();
                    eliminarCartera(nombreCartera);
                    break;
                case 5:
                    System.out.println("Ingrese el nombre del funcionario: ");
                    nombreFuncionario = scanner.nextLine();
                    
                    System.out.println("Ingrese el cargo del funcionario: ");
                    String cargoFuncionario = scanner.nextLine();
                    
                    System.out.println("Ingrese el ID del funcionario: ");
                    idFuncionario = scanner.nextLine();
                    
                    System.out.println("Ingrese el nombre de la cartera a la que pertenece el funcionario: ");
                    nombreCartera = scanner.nextLine();
                    
                    agregarFuncionarioConsola(nombreFuncionario, cargoFuncionario, idFuncionario, nombreCartera);
                    break;
                case 6:
                    System.out.println("Ingrese el nombre de la cartera a la que pertenece el funcionario: ");
                    nombreCartera = scanner.nextLine();
                    
                    System.out.println("Ingrese el ID del funcionario a eliminar: ");
                    String IDFuncionarioEliminar = scanner.nextLine();
                    
                    buscarCartera(nombreCartera).eliminarFuncionario(IDFuncionarioEliminar);
                    break;
                case 7:
                    System.out.println("Ingrese el nombre de la cartera a la que pertenece el funcionario: ");
                    nombreCartera = scanner.nextLine();
                    
                    System.out.println("Ingrese el ID del funcionario a buscar: ");
                    String IDFuncionarioBuscar = scanner.nextLine();
                    
                    buscarCartera(nombreCartera).buscarFuncionario(IDFuncionarioBuscar);
                    break;
                case 8:
                    System.out.println("Ingrese el nombre de la cartera, de la cual quiere visuailizar sus funcionarios: ");
                    nombreCartera = scanner.nextLine();
                    buscarCartera(nombreCartera).mostrarFuncionarios();
                    break;
                case 9:
                    guardarDatosACSV();
                    salir = true;
                    break;
                case 10: 
                    System.out.print("Ingrese el ID del funcionario que desea reubicar: ");
                    idFuncionario = scanner.nextLine();

                    System.out.print("Ingrese el nombre de la cartera actual del funcionario: ");
                    String nombreCarteraActual = scanner.nextLine();

                    System.out.print("Ingrese el nombre de la cartera de destino: ");
                    String nombreCarteraDestino = scanner.nextLine();

                    reubicarFuncionario(idFuncionario, nombreCarteraActual, nombreCarteraDestino);
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    //Sobre carga de metodo
      public void agregarCartera(CarteraMinisterial cartera){
         String nombreCartera = cartera.getNombre();
        if(carterasMapa.containsKey(nombreCartera)){
        System.out.println("\nCartera ya existente");
        }else{
        carterasMapa.put(nombreCartera,cartera);
        carteras.add(cartera);
        System.out.println("Cartera agregada: " + carterasMapa.get(nombreCartera).getNombre());
        }
    }
    
        public void agregarCartera(String nombreCartera) {
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
        
        public CarteraMinisterial buscarCartera(String nombreCartera) {
        if(carterasMapa.containsKey(nombreCartera)) {
                System.out.println("Cartera encontrada: " + carterasMapa.get(nombreCartera).getNombre());
                return carterasMapa.get(nombreCartera);
        }
        else
            System.out.println("Cartera no existe");
        return null;
    }
        
        public CarteraMinisterial eliminarCartera(String nombreCartera){
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
        
        public void reubicarFuncionario(String idFuncionario, String nombreCarteraActual, String nombreCarteraDestino) {
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
        
        private void cargarDatosDesdeCSV() {
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
        
private void guardarDatosACSV() {
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
