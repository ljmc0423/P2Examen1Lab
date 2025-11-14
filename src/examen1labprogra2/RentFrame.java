/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author ljmc2
 */
public class RentFrame extends JFrame {

    private JTextField codigoField;
    private JButton buscarBtn, volverBtn;
    private JPanel resultPanel;
    private ArrayList<RentItem> items;
    private static ArrayList<RentItem> itemsEstaticos = new ArrayList<>();

    public RentFrame() {
        // Intentar obtener la lista de items estáticos o buscar en MainGUI abierto
        if (!itemsEstaticos.isEmpty()) {
            this.items = new ArrayList<>(itemsEstaticos);
        } else {
            // Buscar MainGUI en las ventanas abiertas
            this.items = obtenerItemsDeMainGUI();
        }
        
        inicializarGUI();
    }
    
    private ArrayList<RentItem> obtenerItemsDeMainGUI() {
        // Buscar MainGUI en las ventanas abiertas
        for (Window window : Window.getWindows()) {
            if (window instanceof MainGUI) {
                MainGUI mainGUI = (MainGUI) window;
                return mainGUI.getItems();
            }
        }
        return new ArrayList<>();
    }
    
    public RentFrame(ArrayList<RentItem> items) {
        this.items = items != null ? items : new ArrayList<>();
        
        inicializarGUI();
    }
    
    public static void setItemsEstaticos(ArrayList<RentItem> items) {
        itemsEstaticos = items != null ? items : new ArrayList<>();
    }
    
    private void inicializarGUI() {
        setTitle("Rentar Ítem");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Código del Ítem:"));
        codigoField = new JTextField(15);
        buscarBtn = new JButton("Buscar");
        inputPanel.add(codigoField);
        inputPanel.add(buscarBtn);
        add(inputPanel, BorderLayout.NORTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        volverBtn = new JButton("Volver al Menú");
        bottomPanel.add(volverBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        buscarBtn.addActionListener(e -> buscarItem());

        volverBtn.addActionListener(e -> {
            MainGUI gui = new MainGUI(items);
            gui.setVisible(true);
            this.dispose();
        });
    }
    
    public void setItems(ArrayList<RentItem> items) {
        this.items = items;
    }

    private void buscarItem() {
        resultPanel.removeAll();

        String codigo = codigoField.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un código.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar el item en la lista
        RentItem itemEncontrado = null;
        for (RentItem item : items) {
            if (item.getCodigo().equals(codigo)) {
                itemEncontrado = item;
                break;
            }
        }

        if (itemEncontrado == null) {
            JLabel mensajeLabel = new JLabel("Item No Existe");
            mensajeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            mensajeLabel.setForeground(Color.RED);
            resultPanel.add(mensajeLabel);
        } else {
            // Mostrar datos del item
            mostrarDatosItem(itemEncontrado);
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void mostrarDatosItem(RentItem item) {
        // Limpiar panel
        resultPanel.removeAll();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        // Mostrar imagen
        if (item.getImagen() != null) {
            JLabel imagenLabel = new JLabel(item.getImagen());
            imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultPanel.add(imagenLabel);
            resultPanel.add(Box.createVerticalStrut(10));
        }

        // Mostrar información del item
        JLabel nombreLabel = new JLabel("Nombre: " + item.getNombre());
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(nombreLabel);

        JLabel codigoLabel = new JLabel("Código: " + item.getCodigo());
        codigoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(codigoLabel);

        JLabel precioLabel = new JLabel("Precio Base: " + item.getPrecioBase() + " Lps");
        precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(precioLabel);

        // Mostrar información adicional según el tipo
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            JLabel estadoLabel = new JLabel("Estado: " + movie.getEstado());
            estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultPanel.add(estadoLabel);
        } else if (item instanceof Game) {
            JLabel tipoLabel = new JLabel("Tipo: PS3 Game");
            tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultPanel.add(tipoLabel);
        }

        resultPanel.add(Box.createVerticalStrut(20));

        // Campo para días
        JLabel diasLabel = new JLabel("Ingrese cantidad de días:");
        diasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(diasLabel);

        JPanel diasPanel = new JPanel(new FlowLayout());
        JTextField diasField = new JTextField(10);
        JButton calcularBtn = new JButton("Calcular Renta");
        diasPanel.add(diasField);
        diasPanel.add(calcularBtn);
        diasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(diasPanel);

        resultPanel.add(Box.createVerticalStrut(10));

        // Label para mostrar el resultado
        JLabel resultadoLabel = new JLabel("Monto Total: ");
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultadoLabel.setForeground(new Color(0, 100, 0));
        resultPanel.add(resultadoLabel);

        // Acción del botón calcular
        calcularBtn.addActionListener(ev -> {
            try {
                String diasStr = diasField.getText().trim();
                if (diasStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese la cantidad de días.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int dias = Integer.parseInt(diasStr);
                if (dias <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad de días debe ser mayor a cero.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double montoTotal = item.pagoRenta(dias);
                resultadoLabel.setText("Monto Total: " + String.format("%.2f", montoTotal) + " Lps");
                
                // Mostrar también en un diálogo
                JOptionPane.showMessageDialog(this, 
                        "Monto Total de Renta: " + String.format("%.2f", montoTotal) + " Lps\n" +
                        "Días: " + dias,
                        "Cálculo de Renta", 
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido de días.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RentFrame frame = new RentFrame();
            frame.setVisible(true);
        });
    }
}
