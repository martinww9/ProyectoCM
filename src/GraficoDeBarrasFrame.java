import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoDeBarrasFrame extends JFrame {
    private Sistema sistema;

    public GraficoDeBarrasFrame(String title, Sistema sistema) {
        super(title);
        this.sistema = sistema;
        DefaultCategoryDataset dataset = crearDataset();
        JFreeChart chart = crearGrafico(dataset);

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        ((BarRenderer) renderer).setBarPainter(new StandardBarPainter());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        // Agrega un botón para volver al menú principal
        JButton volverAlMenuButton = new JButton("Volver al Menú Carteras");
        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cierra la ventana actual de gráfico
                dispose();
                
                // Abre la ventana del menú principal
                VentanaMenu menu = new VentanaMenu(sistema);
                menu.setVisible(true);
            }
        });

        // Crea un JPanel para colocar el gráfico y el botón
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(volverAlMenuButton, BorderLayout.SOUTH);

        setContentPane(panel);
    }

    private DefaultCategoryDataset crearDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Obtener los datos de cargos y frecuencias desde el sistema
        Map<String, Integer> datosCargos = obtenerDatosCargosDesdeSistema();

        // Agregar los datos al dataset
        for (Map.Entry<String, Integer> entry : datosCargos.entrySet()) {
            String cargo = entry.getKey();
            int frecuencia = entry.getValue();
            dataset.addValue(frecuencia, cargo, cargo); // Usar el nombre del cargo como serie y categoría
        }

        return dataset;
    }

    private JFreeChart crearGrafico(DefaultCategoryDataset dataset) {
        JFreeChart chart;
        chart = ChartFactory.createBarChart(
                "Popularidad de Cargos de Funcionarios",
                "Cargos",
                "Frecuencia",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );
        return chart;
    }

    private Map<String, Integer> obtenerDatosCargosDesdeSistema() {
        // Crear un mapa para almacenar los datos de cargos y frecuencias
        Map<String, Integer> datosCargos = new HashMap<>();

        // Obtener todas las carteras ministeriales del sistema
        List<CarteraMinisterial> carteras = sistema.obtenerCarteras();

        // Recorrer cada cartera ministerial
        for (CarteraMinisterial cartera : carteras) {
            // Obtener los funcionarios de la cartera actual
            List<Funcionario> funcionarios = cartera.obtenerFuncionarios();

            // Recorrer cada funcionario y contar la frecuencia de su cargo
            for (Funcionario funcionario : funcionarios) {
                String cargo = funcionario.getCargo();

                // Actualizar la frecuencia del cargo en el mapa de datos de cargos
                int frecuenciaActual = datosCargos.getOrDefault(cargo, 0);
                datosCargos.put(cargo, frecuenciaActual + 1);
            }
        }

        return datosCargos;
    }
}
