import java.io.*;
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

    public void cargarDatosDesdeCSV() throws CarteraExistsException, CarteraNotFoundException, FuncionarioExistsException {
        Set<String> carterasAgregadas = new HashSet<>();
        List<String[]> datos = CSVDataManager.cargarDatos();

        for (String[] fila : datos) {
            if (fila.length >= 2) { // Verifica que haya al menos 2 elementos en la fila (nombre de cartera y encargado)
                String nombreCartera = fila[0];
                String encargado = fila[1];

                // Verificar si la cartera y el encargado ya han sido agregados
                String carteraEncargadoKey = nombreCartera;

                if (!carterasAgregadas.contains(carteraEncargadoKey)) {
                    // La cartera no existe, crea la cartera
                    CarteraMinisterial carteraAux = new CarteraMinisterial(nombreCartera, encargado);
                    agregarCartera(carteraAux);

                    // Agrega la cartera y el encargado al conjunto de seguimiento
                    carterasAgregadas.add(carteraEncargadoKey);
                }

                if (fila.length >= 5) { // Si hay suficientes elementos para funcionarios
                    String nombreFuncionario = fila[2];
                    String cargoFuncionario = fila[3];
                    String idFuncionario = fila[4];

                    // Agrega el funcionario solo si los campos no están vacíos
                    if (!nombreFuncionario.isEmpty() && !cargoFuncionario.isEmpty() && !idFuncionario.isEmpty()) {
                        agregarFuncionarioCarteraEspecifica(nombreFuncionario, cargoFuncionario, idFuncionario, nombreCartera);
                    }
                }
            } else {
                // Manejar el caso en el que la fila no tenga suficientes elementos
                System.err.println("Error: la fila del archivo CSV no tiene suficientes elementos.");
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

    public void generarReporte() {
        int cantCarteras = carteras.size();
        String[] lineas = new String[cantCarteras + 1000]; // Aumentamos el tamaño para permitir más líneas decorativas.

        lineas[0] = "REPORTE FINAL"; // Encabezado
        lineas[1] = "====================";

        // Estadísticas de Funcionarios por Cargo
            Map<String, Integer> funcionariosPorCargo = new HashMap<>();
            for (CarteraMinisterial cartera : carteras) {
                List<Funcionario> funcionarios = cartera.obtenerFuncionarios();
                for (Funcionario funcionario : funcionarios) {
                    String cargo = funcionario.getCargo();
                    if (funcionariosPorCargo.containsKey(cargo)) {
                        funcionariosPorCargo.put(cargo, funcionariosPorCargo.get(cargo) + 1);
                    } else {
                        funcionariosPorCargo.put(cargo, 1);
                    }
                }
            }

        lineas[3] = "Cantidad de Funcionarios por Cargo:";
        lineas[4] = "-----------------------------------";

        int index = 5;
        for (String cargo : funcionariosPorCargo.keySet()) {
            int cantidad = funcionariosPorCargo.get(cargo);
            lineas[index++] = cargo + ": " + cantidad;
        }

        // Estadísticas de Funcionarios por Cartera
        lineas[index++] = "";
        lineas[index++] = "Cantidad de Funcionarios por Cartera:";
        lineas[index++] = "--------------------------------------";

        int carteraConMasFuncionarios = 0;
        int carteraConMenosFuncionarios = 9999999;
        String nombreCarteraMasFuncionarios = "";
        String nombreCarteraMenosFuncionarios = "";

        for (CarteraMinisterial cartera : carteras) {
            List<Funcionario> funcionarios = cartera.obtenerFuncionarios();
            int cantidadFuncionarios = funcionarios.size();
            lineas[index++] = cartera.getNombre() + ": " + cantidadFuncionarios;

            // Actualizar estadísticas de carteras
            if (cantidadFuncionarios > carteraConMasFuncionarios) {
                carteraConMasFuncionarios = cantidadFuncionarios;
                nombreCarteraMasFuncionarios = cartera.getNombre();
            }
            if (cantidadFuncionarios < carteraConMenosFuncionarios) {
                carteraConMenosFuncionarios = cantidadFuncionarios;
                nombreCarteraMenosFuncionarios = cartera.getNombre();
            }
        }

        // Cartera con más Funcionarios
        lineas[index++] = "";
        lineas[index++] = "Cartera con más Funcionarios:";
        lineas[index++] = "-----------------------------";
        lineas[index++] = nombreCarteraMasFuncionarios + " (" + carteraConMasFuncionarios + " funcionarios)";

        // Cartera con menos Funcionarios
        lineas[index++] = "";
        lineas[index++] = "Cartera con menos Funcionarios:";
        lineas[index++] = "--------------------------------";
        lineas[index++] = nombreCarteraMenosFuncionarios + " (" + carteraConMenosFuncionarios + " funcionarios)";

        // Lista de Carteras Ministeriales
        lineas[index++] = "";
        lineas[index++] = "Lista de Carteras Ministeriales:";
        lineas[index++] = "--------------------------------";

        for (CarteraMinisterial cartera : carteras) {
            lineas[index++] = "Cartera: " + cartera.getNombre();
            lineas[index++] = "Encargado: " + cartera.getEncargado();
            lineas[index++] = "";
        }

        // Lista de Funcionarios por Cartera
        lineas[index++] = "";
        lineas[index++] = "Lista de Funcionarios por Cartera:";
        lineas[index++] = "----------------------------------";

        for (CarteraMinisterial cartera : carteras) {
            lineas[index++] = "Cartera: " + cartera.getNombre();
            List<Funcionario> funcionarios = cartera.obtenerFuncionarios();
            for (Funcionario funcionario : funcionarios) {
                lineas[index++] = "Nombre: " + funcionario.getNombre();
                lineas[index++] = "Cargo: " + funcionario.getCargo();
                lineas[index++] = "ID: " + funcionario.getID();
                lineas[index++] = "";
            }
        }

        try {
            FileWriter archivo = new FileWriter("reporte.txt");
            BufferedWriter escritor = new BufferedWriter(archivo);

            for
    (String linea : lineas) {
                if (linea != null) {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }
            escritor.close();
            System.out.println("El archivo se ha exportado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al exportar el archivo: " + e.getMessage());
        }
    }

    public void guardarDatosACSV() {
        List<String[]> datos = new ArrayList<>();

        for (CarteraMinisterial cartera : carteras) {
            String nombreCartera = cartera.getNombre();
            String encargado = cartera.getEncargado();

            // Crear una entrada para la cartera incluso si no hay funcionarios
            String[] carteraInfo = {nombreCartera, encargado, "", "", ""};
            datos.add(carteraInfo);

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
    }
}