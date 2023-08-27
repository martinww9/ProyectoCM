package proyectocm;
import java.util.*;


class Sistema {
    private ArrayList<CarteraMinisterial> carteras;
    private Scanner scanner;

    public Sistema() {
        carteras = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar cartera");
            System.out.println("2. Buscar cartera");
            System.out.println("3. Mostrar carteras");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    agregarCartera();
                    break;
                case 2:
                    mostrarCarteras();
                    break;
                case 3:
                    //buscarCartera();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
        public void agregarCartera() {
        System.out.print("Ingrese el nombre de la cartera: ");
        String nombreCartera = scanner.nextLine();
        
        System.out.print("Ingrese el nombre del encargado: ");
        String encargadoCartera = scanner.nextLine();
        
        CarteraMinisterial cartera = new CarteraMinisterial(nombreCartera, encargadoCartera);
        carteras.add(cartera);
        System.out.println("Cartera agregada: " + cartera.getNombre());
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
        
        public void buscarCartera() {
        System.out.print("Ingrese el nombre de la cartera a buscar: ");
        String nombreCartera = scanner.nextLine();

        boolean encontrada = false;
        for (int i = 0; i < carteras.size(); i++) {
            if (carteras.get(i).getNombre().equals(nombreCartera)) {
                System.out.println("Cartera encontrada: " + carteras.get(i).getNombre());
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            System.out.println("Cartera no encontrada.");
        }
    }
}