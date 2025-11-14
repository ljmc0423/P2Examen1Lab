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
        setSize(650, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(650, 120));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Rentar Item", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(new Color(52, 152, 219));
        JLabel codigoLabel = new JLabel("Código del Item:");
        codigoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        codigoLabel.setForeground(Color.WHITE);
        inputPanel.add(codigoLabel);
        codigoField = new JTextField(15);
        codigoField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        codigoField.setPreferredSize(new Dimension(200, 35));
        inputPanel.add(codigoField);
        buscarBtn = createStyledButton("Buscar", new Color(41, 128, 185));
        buscarBtn.setPreferredSize(new Dimension(120, 35));
        inputPanel.add(buscarBtn);
        headerPanel.add(inputPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(new Color(236, 240, 241));
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(236, 240, 241));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        volverBtn = createStyledButton("Volver al Menu", new Color(52, 73, 94));
        volverBtn.setPreferredSize(new Dimension(200, 45));
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

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }

    private void buscarItem() {
        resultPanel.removeAll();

        String codigo = codigoField.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un codigo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RentItem itemEncontrado = null;
        for (RentItem item : items) {
            if (item.getCodigo().equals(codigo)) {
                itemEncontrado = item;
                break;
            }
        }

        if (itemEncontrado == null) {
            JPanel errorPanel = new JPanel();
            errorPanel.setBackground(new Color(236, 240, 241));
            errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
            errorPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
            
            JLabel mensajeLabel = new JLabel("Item No Existe");
            mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            mensajeLabel.setForeground(new Color(231, 76, 60));
            mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            errorPanel.add(mensajeLabel);
            
            JLabel sugerenciaLabel = new JLabel("Verifique el codigo e intente nuevamente");
            sugerenciaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            sugerenciaLabel.setForeground(new Color(127, 140, 141));
            sugerenciaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            errorPanel.add(Box.createVerticalStrut(10));
            errorPanel.add(sugerenciaLabel);
            
            resultPanel.add(errorPanel);
        } else {
            mostrarDatosItem(itemEncontrado);
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void mostrarDatosItem(RentItem item) {
        resultPanel.removeAll();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(new Color(236, 240, 241));

        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        cardPanel.setMaximumSize(new Dimension(500, 2000));

        if (item.getImagen() != null) {
            JLabel imagenLabel = new JLabel(item.getImagen());
            imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagenLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
            cardPanel.add(imagenLabel);
        }

        JLabel nombreLabel = new JLabel(item.getNombre());
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setForeground(new Color(44, 62, 80));
        cardPanel.add(nombreLabel);
        cardPanel.add(Box.createVerticalStrut(10));

        JLabel codigoLabel = new JLabel("Codigo: " + item.getCodigo());
        codigoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        codigoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        codigoLabel.setForeground(new Color(127, 140, 141));
        cardPanel.add(codigoLabel);
        cardPanel.add(Box.createVerticalStrut(5));

        JLabel precioLabel = new JLabel("Precio Base: " + item.getPrecioBase() + " Lps");
        precioLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        precioLabel.setForeground(new Color(41, 128, 185));
        cardPanel.add(precioLabel);
        cardPanel.add(Box.createVerticalStrut(10));

        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            JLabel estadoLabel = new JLabel("Estado: " + movie.getEstado());
            estadoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            estadoLabel.setForeground(movie.getEstado().equals("ESTRENO") ? 
                new Color(46, 204, 113) : new Color(127, 140, 141));
            cardPanel.add(estadoLabel);
        } else if (item instanceof Game) {
            JLabel tipoLabel = new JLabel("Tipo: PS3 Game");
            tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            tipoLabel.setForeground(new Color(155, 89, 182));
            cardPanel.add(tipoLabel);
        }

        cardPanel.add(Box.createVerticalStrut(25));

        JLabel diasLabel = new JLabel("Ingrese cantidad de dias:");
        diasLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        diasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(diasLabel);
        cardPanel.add(Box.createVerticalStrut(10));

        JPanel diasPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        diasPanel.setBackground(Color.WHITE);
        JTextField diasField = new JTextField(10);
        diasField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        diasField.setPreferredSize(new Dimension(150, 35));
        JButton calcularBtn = createStyledButton("Calcular Renta", new Color(46, 204, 113));
        calcularBtn.setPreferredSize(new Dimension(150, 35));
        diasPanel.add(diasField);
        diasPanel.add(calcularBtn);
        diasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(diasPanel);

        cardPanel.add(Box.createVerticalStrut(20));

        JLabel resultadoLabel = new JLabel("Monto Total: ");
        resultadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultadoLabel.setForeground(new Color(39, 174, 96));
        cardPanel.add(resultadoLabel);

        resultPanel.add(Box.createVerticalStrut(20));
        resultPanel.add(cardPanel);
        resultPanel.add(Box.createVerticalStrut(20));

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
