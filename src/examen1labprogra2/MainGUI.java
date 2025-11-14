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
            JOptionPane.showMessageDialog(this, "No hay ítems registrados para ejecutar un submenú.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String codigo = JOptionPane.showInputDialog(
                this, 
                "Ingrese el código del ítem (Juego) para ejecutar el Submenú:",
                "Seleccionar Ítem",
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
            JOptionPane.showMessageDialog(this, "No se encontró ningún ítem con el código: " + codigo, "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
        } else if (itemSeleccionado instanceof MenuActions) {
            
            MenuActions menuActionItem = (MenuActions) itemSeleccionado;
            menuActionItem.submenu();
        } else {
            
            JOptionPane.showMessageDialog(this, "El ítem '" + itemSeleccionado.getNombre() + "' no tiene un submenú disponible.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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