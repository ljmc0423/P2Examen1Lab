/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ljmc2
 */
public class RentFrame extends JFrame {

    private JTextField codigoField;
    private JButton buscarBtn, volverBtn;
    private JPanel resultPanel;

    public RentFrame() {
        setTitle("Rentar Ítem");
        setSize(400, 400);
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
        add(resultPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        volverBtn = new JButton("Volver al Menú");
        bottomPanel.add(volverBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        buscarBtn.addActionListener(e -> buscarItem());

        volverBtn.addActionListener(e ->{
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
            this.dispose();
        });
    }

    private void buscarItem() {
        resultPanel.removeAll();

        String codigo = codigoField.getText().trim();

        if (codigo.equals("123")) {
            JLabel nombreLabel = new JLabel("Nombre: Ejemplo Ítem");
            JLabel precioLabel = new JLabel("Precio de Renta: $3.50");
            JLabel diasLabel = new JLabel("Ingrese cantidad de días:");
            JTextField diasField = new JTextField(5);
            JButton calcularBtn = new JButton("Calcular Renta");
            JLabel resultadoLabel = new JLabel("Monto Total: ");

            JLabel imagenLabel = new JLabel();
            imagenLabel.setIcon(new ImageIcon("placeholder.png")); // solo ejemplo

            resultPanel.add(nombreLabel);
            resultPanel.add(precioLabel);
            resultPanel.add(imagenLabel);
            resultPanel.add(diasLabel);
            resultPanel.add(diasField);
            resultPanel.add(calcularBtn);
            resultPanel.add(resultadoLabel);

            calcularBtn.addActionListener(ev -> {
                try {
                    int dias = Integer.parseInt(diasField.getText());
                    double monto = 3.5 * dias;
                    resultadoLabel.setText("Monto Total: $" + monto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un número válido de días.");
                }
            });

        } else {
            resultPanel.add(new JLabel("Item No Existe"));
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RentFrame frame = new RentFrame();
            frame.setVisible(true);
        });
    }
}
