/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author ljmc2
 */
public class CatalogFrame extends JFrame {

    private JPanel catalogPanel;
    private JScrollPane scrollPane;
    private ArrayList<RentItem> items;
    private static ArrayList<RentItem> itemsEstaticos = new ArrayList<>();

    public CatalogFrame() {
        // Intentar obtener la lista de items estáticos o buscar en MainGUI abierto
        if (!itemsEstaticos.isEmpty()) {
            this.items = new ArrayList<>(itemsEstaticos);
        } else {
            // Buscar MainGUI en las ventanas abiertas
            this.items = obtenerItemsDeMainGUI();
        }
        
        inicializarGUI();
    }
    
    public CatalogFrame(ArrayList<RentItem> items) {
        this.items = items != null ? items : new ArrayList<>();
        
        inicializarGUI();
    }
    
    public static void setItemsEstaticos(ArrayList<RentItem> items) {
        itemsEstaticos = items != null ? items : new ArrayList<>();
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
    
    private void inicializarGUI() {
        setTitle("Catálogo de Ítems");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Catálogo de Ítems", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        catalogPanel = new JPanel();
        catalogPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        catalogPanel.setBackground(Color.WHITE);
        
        scrollPane = new JScrollPane(catalogPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JButton volverBtn = new JButton("Volver al Menú");
        volverBtn.addActionListener(e -> {
            MainGUI gui = new MainGUI(items);
            gui.setVisible(true);
            this.dispose();
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(volverBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Cargar todos los items
        cargarItems();
    }
    
    private void cargarItems() {
        catalogPanel.removeAll();
        
        if (items.isEmpty()) {
            JLabel mensajeLabel = new JLabel("No hay ítems registrados");
            mensajeLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            mensajeLabel.setForeground(Color.GRAY);
            mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            catalogPanel.add(mensajeLabel);
        } else {
            for (RentItem item : items) {
                catalogPanel.add(crearTarjeta(item));
            }
        }
        
        catalogPanel.revalidate();
        catalogPanel.repaint();
    }

    private JPanel crearTarjeta(RentItem item) {
        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(180, 280));
        tarjeta.setBorder(new LineBorder(Color.GRAY, 2, true));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);

        // Imagen
        JLabel imagenLabel = new JLabel();
        if (item.getImagen() != null) {
            ImageIcon imagenOriginal = item.getImagen();
            // Redimensionar imagen para la tarjeta
            Image img = imagenOriginal.getImage();
            Image nuevaImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(nuevaImg));
        } else {
            imagenLabel.setText("Sin imagen");
            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagenLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nombre
        JLabel nombreLabel = new JLabel(item.getNombre());
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Estado (solo para Movie)
        JLabel estadoLabel = null;
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            String estado = movie.getEstado();
            estadoLabel = new JLabel("Estado: " + estado);
            estadoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            // Color según el estado
            if (estado.equals("ESTRENO")) {
                estadoLabel.setForeground(new Color(0, 150, 0)); // Verde
            } else {
                estadoLabel.setForeground(new Color(100, 100, 100)); // Gris
            }
        } else if (item instanceof Game) {
            // Para Game no se muestra estado
            estadoLabel = new JLabel("Tipo: PS3 Game");
            estadoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            estadoLabel.setForeground(new Color(0, 100, 200)); // Azul
        }
        
        if (estadoLabel != null) {
            estadoLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        }

        // Precio de renta
        JLabel precioLabel = new JLabel("Precio: " + String.format("%.2f", item.getPrecioBase()) + " Lps");
        precioLabel.setFont(new Font("Arial", Font.BOLD, 13));
        precioLabel.setForeground(new Color(200, 0, 0)); // Rojo
        precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        precioLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        // Agregar componentes a la tarjeta
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(imagenLabel);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(nombreLabel);
        if (estadoLabel != null) {
            tarjeta.add(estadoLabel);
        }
        tarjeta.add(precioLabel);
        tarjeta.add(Box.createVerticalStrut(5));

        return tarjeta;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CatalogFrame frame = new CatalogFrame();
            frame.setVisible(true);
        });
    }
}
