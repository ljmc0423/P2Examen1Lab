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
public class CatalogFrame extends JFrame {

    private JPanel catalogPanel;
    private JScrollPane scrollPane;
    private ArrayList<RentItem> items;

    public CatalogFrame() {
        this(new ArrayList<>());
    }
    
    public CatalogFrame(ArrayList<RentItem> items) {
        this.items = items != null ? items : new ArrayList<>();
        
        inicializarGUI();
    }
    
    private void inicializarGUI() {
        setTitle("Catalogo de Items");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(241, 196, 15));
        headerPanel.setPreferredSize(new Dimension(900, 80));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Catalogo de Items", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        catalogPanel = new JPanel();
        catalogPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        catalogPanel.setBackground(new Color(236, 240, 241));
        catalogPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        scrollPane = new JScrollPane(catalogPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(236, 240, 241));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton volverBtn = createStyledButton("Volver al Menu", new Color(52, 73, 94));
        volverBtn.setPreferredSize(new Dimension(200, 45));
        volverBtn.addActionListener(e -> {
            MainGUI gui = new MainGUI(items);
            gui.setVisible(true);
            this.dispose();
        });
        bottomPanel.add(volverBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        cargarItems();
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

    private void cargarItems() {
        catalogPanel.removeAll();
        
        if (items.isEmpty()) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(new Color(236, 240, 241));
            emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
            
            JLabel mensajeLabel = new JLabel("No hay items registrados");
            mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            mensajeLabel.setForeground(new Color(127, 140, 141));
            mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            emptyPanel.add(Box.createVerticalStrut(100));
            emptyPanel.add(mensajeLabel);
            
            catalogPanel.add(emptyPanel);
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
        tarjeta.setPreferredSize(new Dimension(220, 330));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        
        tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                    BorderFactory.createEmptyBorder(14, 14, 14, 14)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
        });

        JLabel imagenLabel = new JLabel();
        if (item.getImagen() != null) {
            ImageIcon imagenOriginal = item.getImagen();
            Image img = imagenOriginal.getImage();
            Image nuevaImg = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(nuevaImg));
        } else {
            imagenLabel.setText("Sin imagen");
            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imagenLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            imagenLabel.setForeground(new Color(189, 195, 199));
            imagenLabel.setPreferredSize(new Dimension(180, 180));
        }
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagenLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel nombreLabel = new JLabel("<html><center>" + item.getNombre() + "</center></html>");
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setForeground(new Color(44, 62, 80));
        nombreLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel estadoLabel = null;
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            String estado = movie.getEstado();
            estadoLabel = new JLabel(estado);
            estadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
            estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            if (estado.equals("ESTRENO")) {
                estadoLabel.setForeground(Color.WHITE);
                estadoLabel.setOpaque(true);
                estadoLabel.setBackground(new Color(46, 204, 113));
            } else {
                estadoLabel.setForeground(Color.WHITE);
                estadoLabel.setOpaque(true);
                estadoLabel.setBackground(new Color(149, 165, 166));
            }
            estadoLabel.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        } else if (item instanceof Game) {
            estadoLabel = new JLabel("PS3 GAME");
            estadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
            estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            estadoLabel.setForeground(Color.WHITE);
            estadoLabel.setOpaque(true);
            estadoLabel.setBackground(new Color(155, 89, 182));
            estadoLabel.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        }

        JLabel precioLabel = new JLabel(String.format("%.2f Lps", item.getPrecioBase()));
        precioLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        precioLabel.setForeground(new Color(231, 76, 60));
        precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        precioLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        tarjeta.add(imagenLabel);
        tarjeta.add(nombreLabel);
        if (estadoLabel != null) {
            tarjeta.add(estadoLabel);
        }
        tarjeta.add(precioLabel);

        return tarjeta;
    }

}
