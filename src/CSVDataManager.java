import java.io.*;
import java.util.*;

public class CSVDataManager {
    private static String archivo = "test.csv"; // Ruta del archivo CSV

    public static List<String[]> cargarDatos() {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                datos.add(partes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public static void guardarDatos(List<String[]> datos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String[] fila : datos) {
                String linea = String.join(",", fila);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}