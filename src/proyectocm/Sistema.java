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
                    //agregarCartera();
                    break;
                case 2:
                    //buscarCartera();
                    break;
                case 3:
                    //mostrarCarteras();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}