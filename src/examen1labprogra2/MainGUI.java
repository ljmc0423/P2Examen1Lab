/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen1labprogra2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author ljmc2
 */
public class MainGUI extends JFrame {

    private ArrayList<RentItem> items;

    public MainGUI() {
        this(new ArrayList<>());
    }
    
    public MainGUI(ArrayList<RentItem> items) {
        this.items = items;

        setTitle("Sistema de Renta");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(600, 100));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Sistema de Renta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel subtitleLabel = new JLabel("Gestion de Peliculas y Juegos", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(236, 240, 241));
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.weightx = 1.0;

        JButton addItemBtn = createStyledButton(" Agregar Item", new Color(46, 204, 113));
        JButton rentItemBtn = createStyledButton(" Rentar", new Color(52, 152, 219));
        JButton subMenuBtn = createStyledButton(" Ejecutar Submenu", new Color(155, 89, 182));
        JButton printAllBtn = createStyledButton(" Imprimir Todo", new Color(241, 196, 15));

        mainPanel.add(addItemBtn, gbc);
        gbc.gridy++;
        mainPanel.add(rentItemBtn, gbc);
        gbc.gridy++;
        mainPanel.add(subMenuBtn, gbc);
        gbc.gridy++;
        mainPanel.add(printAllBtn, gbc);

        add(mainPanel, BorderLayout.CENTER);

        
        addItemBtn.addActionListener(e -> {
            AddFrame frame = new AddFrame(items, this);
            frame.setVisible(true);
            this.setVisible(false);
        });

        rentItemBtn.addActionListener(e -> {
            RentFrame frame = new RentFrame(items);
            frame.setVisible(true);
            this.setVisible(false);
        });

        subMenuBtn.addActionListener(e -> {
            ejecutarSubmenu();
        });

        printAllBtn.addActionListener(e -> {
            CatalogFrame frame = new CatalogFrame(items);
            frame.setVisible(true);
            this.setVisible(false);
        });
    }
    
    private void ejecutarSubmenu() {
        if (items.isEmpty()) {
            String mensaje = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>No hay items registrados para ejecutar un submenu.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String mensajeInput = "<html><body style='width: 350px; padding: 10px; font-family: Segoe UI;'>"
                            + "<h3 style='color: #9B59B6; margin: 0 0 10px 0;'>Ejecutar Submenu</h3>"
                            + "<p style='margin: 5px 0;'>Ingrese el codigo del item (Juego) para ejecutar el submenu:</p>"
                            + "<p style='color: #7F8C8D; font-size: 11px; margin: 5px 0 0 0;'>Nota: Solo los juegos tienen submenu disponible</p>"
                            + "</body></html>";
        
        String codigo = JOptionPane.showInputDialog(
                this, 
                mensajeInput,
                "Seleccionar Item",
                JOptionPane.QUESTION_MESSAGE
        );
        
        if (codigo == null || codigo.trim().isEmpty()) {
            return;
        }

        RentItem itemSeleccionado = null;
        for (RentItem item : items) {
            if (item.getCodigo().equalsIgnoreCase(codigo.trim())) {
                itemSeleccionado = item;
                break;
            }
        }

        if (itemSeleccionado == null) {
            String mensajeError = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                                + "<p style='color: #E74C3C;'>No se encontro ningun item con el codigo:</p>"
                                + "<p style='font-weight: bold; color: #2C3E50;'>" + codigo + "</p>"
                                + "</body></html>";
            JOptionPane.showMessageDialog(this, mensajeError, "Error de Busqueda", JOptionPane.ERROR_MESSAGE);
        } else if (itemSeleccionado instanceof MenuActions) {
            MenuActions menuActionItem = (MenuActions) itemSeleccionado;
            menuActionItem.submenu();
        } else {
            String mensajeAdvertencia = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                                      + "<p style='color: #E67E22;'>El item <b>" + itemSeleccionado.getNombre() + "</b> no tiene un submenu disponible.</p>"
                                      + "<p style='color: #7F8C8D; font-size: 11px; margin-top: 10px;'>Solo los juegos (Games) tienen submenu.</p>"
                                      + "</body></html>";
            JOptionPane.showMessageDialog(this, mensajeAdvertencia, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(400, 50));
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

    public ArrayList<RentItem> getItems() {
        return items;
    }

    public static void main(String[] args) {
        ArrayList<RentItem> initialItems = new ArrayList<>();
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI(initialItems);
            gui.setVisible(true);
        });
    }
}