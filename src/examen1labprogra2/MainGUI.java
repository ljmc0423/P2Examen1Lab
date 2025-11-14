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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton addItemBtn = new JButton("Agregar Item");
        JButton rentItemBtn = new JButton("Rentar");
        JButton subMenuBtn = new JButton("Ejecutar Submenu"); // Botón a implementar
        JButton printAllBtn = new JButton("Imprimir Todo");

        buttonPanel.add(addItemBtn);
        buttonPanel.add(rentItemBtn);
        buttonPanel.add(subMenuBtn);
        buttonPanel.add(printAllBtn);

        add(buttonPanel, BorderLayout.CENTER);

        
        addItemBtn.addActionListener(e -> {
            // Asumo que AddFrame gestiona la adición de items
            AddFrame frame = new AddFrame(items, this);
            frame.setVisible(true);
            this.setVisible(false);
        });

        rentItemBtn.addActionListener(e -> {
            // Asumo que RentFrame gestiona la renta
            RentFrame frame = new RentFrame();
            frame.setVisible(true);
            this.dispose();
        });

        // --- IMPLEMENTACIÓN DEL SUBMENÚ ---
        subMenuBtn.addActionListener(e -> {
            ejecutarSubmenu();
        });

        printAllBtn.addActionListener(e -> {
            // Asumo que CatalogFrame muestra la lista de items
            CatalogFrame frame = new CatalogFrame();
            frame.setVisible(true);
            this.dispose();
        });
    }
    
    /**
     * Lógica para seleccionar un RentItem por código y ejecutar su submenu.
     */
    private void ejecutarSubmenu() {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ítems registrados para ejecutar un submenú.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Solicitar el código del ítem
        String codigo = JOptionPane.showInputDialog(
                this, 
                "Ingrese el código del ítem (Juego) para ejecutar el Submenú:",
                "Seleccionar Ítem",
                JOptionPane.QUESTION_MESSAGE
        );
        
        if (codigo == null || codigo.trim().isEmpty()) {
            // Cancelado o vacío
            return;
        }

        // 2. Buscar el ítem en la lista
        RentItem itemSeleccionado = null;
        for (RentItem item : items) {
            // Asumo que getCodigo() existe en RentItem
            if (item.getCodigo().equalsIgnoreCase(codigo.trim())) {
                itemSeleccionado = item;
                break;
            }
        }

        // 3. Ejecutar el submenú si el ítem es válido y soporta acciones de menú
        if (itemSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No se encontró ningún ítem con el código: " + codigo, "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
        } else if (itemSeleccionado instanceof MenuActions) {
            // El ítem implementa MenuActions (como la clase Game)
            MenuActions menuActionItem = (MenuActions) itemSeleccionado;
            menuActionItem.submenu();
        } else {
            // El ítem fue encontrado, pero no tiene un submenú implementado
            JOptionPane.showMessageDialog(this, "El ítem '" + itemSeleccionado.getNombre() + "' no tiene un submenú disponible.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList<RentItem> getItems() {
        return items;
    }

    public static void main(String[] args) {
        ArrayList<RentItem> initialItems = new ArrayList<>();
        // Nota: Debes asegurarte de que RentItem y MenuActions están definidos y que 
        // tienes acceso a una imagen para este ejemplo.
        // Simulamos un Game para la prueba
        try {
            ImageIcon icon = new ImageIcon(MainGUI.class.getResource("/resources/default_icon.png")); // Ajusta la ruta a un icono real
            Game sampleGame = new Game("G001", "The Last of Us", 50.0, icon);
            sampleGame.setFechaPublicacion(2013, 6, 14); // Año, Mes (1-12), Día
            sampleGame.agregarEspecificacion("Solo para mayores de 18");
            sampleGame.agregarEspecificacion("Requiere 50GB de disco duro");
            initialItems.add(sampleGame);
        } catch (Exception e) {
             System.err.println("Advertencia: No se pudo cargar el ícono de ejemplo. " + e.getMessage());
        }
        
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI(initialItems);
            gui.setVisible(true);
        });
    }
}