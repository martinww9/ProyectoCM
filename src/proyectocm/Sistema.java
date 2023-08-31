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
        inicializarCarteras();
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
        cartera1.agregarFuncionario("Juan Perez", "Ministro", "12345678-9", 1);
        cartera2.agregarFuncionario("Maria Perez", "Ministro", "213456789-9", 1);
        cartera3.agregarFuncionario("Sofia Ramirez", "Ministro", "312456789-9", 1);
        cartera4.agregarFuncionario("Carlos Perez", "Ministro", "412356789-9", 1);
        cartera5.agregarFuncionario("Pedro Ortiz", "Ministro", "512346789-9", 1);
        cartera6.agregarFuncionario("Andrea Quinteros", "Ministro", "612345789-9", 1);
        cartera7.agregarFuncionario("Oscar Zamora", "Ministro", "712345689-9", 1);
        cartera8.agregarFuncionario("Karen Ibañez", "Ministro", "812345679-9", 1);
        cartera9.agregarFuncionario("Camila Wagner", "Ministro", "912345678-9", 1);
        cartera10.agregarFuncionario("Hector Aravena", "Ministro", "12345678-k", 1);
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
            System.out.println("1. Agregar cartera");
            System.out.println("2. Mostrar carteras");
            System.out.println("3. Buscar cartera");
            System.out.println("4. Eliminar cartera");
            System.out.println("5. Salir");
            System.out.println("6. Agregar funcionario");
            System.out.println("7. Eliminar funcionario");
            System.out.println("8. Buscar funcionario");
            System.out.println("9. Mostrar funcionarios");
            System.out.print("Elija una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            String nombreCartera = "";
            System.out.println("");
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la cartera: ");
                    nombreCartera = scanner.nextLine();
                    System.out.print("Ingrese el nombre del encargado: ");
                    String encargadoCartera = scanner.nextLine();
                    agregarCartera(nombreCartera, encargadoCartera);
                    System.out.println("Cartera agregada: " + carterasMapa.get(nombreCartera).getNombre());
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
                    salir = true;
                    break;
                case 6:
                    System.out.println("Ingrese el nombre del funcionario: ");
                    String nombreFuncionario = scanner.nextLine();
                    
                    System.out.println("Ingrese el cargo del funcionario: ");
                    String cargoFuncionario = scanner.nextLine();
                    
                    System.out.println("Ingrese el ID del funcionario: ");
                    String IDFuncionario = scanner.nextLine();
                    
                    System.out.println("Ingrese el nombre de la cartera a la que pertenece el funcionario: ");
                    nombreCartera = scanner.nextLine();
                    
                    Funcionario funcionario = new Funcionario(nombreFuncionario, cargoFuncionario, IDFuncionario);
                    agregarFuncionarioConsola(funcionario, nombreCartera);
                    break;
                case 7:
                //Funcion de agregar funcionario
                break;
                case 8:
                //Funcion de eliminar funcionario
                case 9:
                    System.out.println("Ingrese el nombre de la cartera a la que pertenece el funcionario: ");
                    nombreCartera = scanner.nextLine();
                    buscarCartera(nombreCartera).mostrarFuncionarios();
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
        }
    }
    
    
        public void agregarCartera(String nombreCartera, String encargadoCartera) {
        if(carterasMapa.containsKey(nombreCartera)){
        System.out.println("\nCartera ya existente");
        }else{
        CarteraMinisterial cartera = new CarteraMinisterial(nombreCartera, encargadoCartera);
        carterasMapa.put(nombreCartera, cartera);
        carteras.add(cartera);
        }
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

        public void agregarFuncionarioConsola(Funcionario funcionario, String nombreCartera){
            if(carterasMapa.containsKey(nombreCartera)){
                carterasMapa.get(nombreCartera).agregarFuncionario(funcionario);
            }else{
                System.out.println("Cartera no existe");
            }   
        }

    }