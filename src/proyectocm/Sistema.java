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
            System.out.print("Elija una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la cartera: ");
                    String nombreCartera = scanner.nextLine();
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
                    String nombreCarteraa = scanner.nextLine();
                    buscarCartera(nombreCarteraa);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre de la cartera a eliminar: ");
                    String nombreCarteraaa = scanner.nextLine();
                    eliminarCartera(nombreCarteraaa);
                    break;
                case 5:
                    salir = true;
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
}