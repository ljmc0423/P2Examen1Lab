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
/*
- ternario para estado de movie
*/
public class CatalogFrame extends JFrame {

    private JPanel catalogPanel;
    private JScrollPane scrollPane;

    public CatalogFrame() {
        setTitle("Catálogo de Ítems");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Catálogo de Ítems", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        catalogPanel = new JPanel();
        catalogPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // tarjetas alineadas
        scrollPane = new JScrollPane(catalogPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JButton volverBtn = new JButton("Volver al Menú");
        volverBtn.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(volverBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        //ejemplos placeholder
        ArrayList<String> ejemploItems = new ArrayList<>();
        ejemploItems.add("Movie: Avengers|Disponible|3.50|placeholder.png");
        ejemploItems.add("Game: Zelda|--|5.00|placeholder.png");
        ejemploItems.add("Movie: Inception|Alquilado|4.00|placeholder.png");

        for (String data : ejemploItems) {
            catalogPanel.add(crearTarjeta(data));
        }
    }

    private JPanel crearTarjeta(String info) {
        //tipo:nombre|estado|precio|imagen
        String[] partes = info.split("\\|");
        String tipoNombre = partes[0].split(":")[1];
        String estado = partes[1];
        String precio = partes[2];
        String imagenPath = partes[3];

        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(150, 200));
        tarjeta.setBorder(new LineBorder(Color.BLACK, 1, true));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel imagenLabel = new JLabel();
        imagenLabel.setIcon(new ImageIcon(imagenPath)); // solo ejemplo, usar placeholder
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nombreLabel = new JLabel(tipoNombre);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel estadoLabel = new JLabel(estado);
        estadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel precioLabel = new JLabel("$" + precio);
        precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(imagenLabel);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(nombreLabel);
        tarjeta.add(estadoLabel);
        tarjeta.add(precioLabel);

        return tarjeta;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CatalogFrame frame = new CatalogFrame();
            frame.setVisible(true);
        });
    }
}
