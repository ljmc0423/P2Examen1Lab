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
        items = new ArrayList<>();

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
        JButton subMenuBtn = new JButton("Ejecutar Submenu");
        JButton printAllBtn = new JButton("Imprimir Todo");

        buttonPanel.add(addItemBtn);
        buttonPanel.add(rentItemBtn);
        buttonPanel.add(subMenuBtn);
        buttonPanel.add(printAllBtn);

        add(buttonPanel, BorderLayout.CENTER);

        
        addItemBtn.addActionListener(e -> {
            AddFrame frame = new AddFrame();
            frame.setVisible(true);
            this.dispose();
        });

        rentItemBtn.addActionListener(e -> {
            JFrame rentFrame = new JFrame("Rentar Ítem");
            rentFrame.setSize(300, 200);
            rentFrame.setLocationRelativeTo(null);
            rentFrame.add(new JLabel("Aquí irá la lógica de renta", SwingConstants.CENTER));
            rentFrame.setVisible(true);
        });

        subMenuBtn.addActionListener(e -> {
            JFrame subMenuFrame = new JFrame("Submenú");
            subMenuFrame.setSize(300, 200);
            subMenuFrame.setLocationRelativeTo(null);
            subMenuFrame.add(new JLabel("Aquí se ejecutará el submenú", SwingConstants.CENTER));
            subMenuFrame.setVisible(true);
        });

        printAllBtn.addActionListener(e -> {
            JFrame printFrame = new JFrame("Todos los Ítems");
            printFrame.setSize(400, 300);
            printFrame.setLocationRelativeTo(null);
            printFrame.add(new JLabel("Aquí se listarán todos los ítems", SwingConstants.CENTER));
            printFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
