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
        JButton subMenuBtn = new JButton("Ejecutar Submenu");
        JButton printAllBtn = new JButton("Imprimir Todo");

        buttonPanel.add(addItemBtn);
        buttonPanel.add(rentItemBtn);
        buttonPanel.add(subMenuBtn);
        buttonPanel.add(printAllBtn);

        add(buttonPanel, BorderLayout.CENTER);

        
        addItemBtn.addActionListener(e -> {
            AddFrame frame = new AddFrame(items, this);
            frame.setVisible(true);
            this.setVisible(false);
        });

        rentItemBtn.addActionListener(e -> {
            RentFrame frame = new RentFrame();
            frame.setVisible(true);
            this.dispose();
        });

        subMenuBtn.addActionListener(e -> {
            
        });

        printAllBtn.addActionListener(e -> {
            CatalogFrame frame = new CatalogFrame();
            frame.setVisible(true);
            this.dispose();
        });
    }
    
    public ArrayList<RentItem> getItems() {
        return items;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
