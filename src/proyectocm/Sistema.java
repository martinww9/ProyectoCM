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
            System.out.println("Encargado:");
            System.out.println(" - " + carteras.get(i).getEncargado());
            carteras.get(i).mostrarFuncionarios();
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