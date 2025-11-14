/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.util.Date;
/**
 *
 * @author ljmc2
 */
public class AddFrame extends JFrame {

    private JComboBox<String> tipoComboBox;
    private JTextField codigoField, nombreField, precioField;
    private JSpinner cantidadSpinner;
    private JLabel imagenLabel;
    private JButton cargarImagenBtn;
    private JDateChooser fechaChooser;
    private JPanel panelMovie, panelGame;

    public AddFrame() {
        setTitle("Agregar Item");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipoPanel.add(new JLabel("Tipo:"));
        tipoComboBox = new JComboBox<>(new String[]{"Movie", "Game"});
        tipoPanel.add(tipoComboBox);
        add(tipoPanel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel(new CardLayout());

        panelMovie = new JPanel(new GridLayout(6, 2, 5, 5));
        panelMovie.setBorder(BorderFactory.createTitledBorder("Datos de la Película"));
        codigoField = new JTextField();
        nombreField = new JTextField();
        precioField = new JTextField();
        cantidadSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        imagenLabel = new JLabel("Imagen no cargada");
        cargarImagenBtn = new JButton("Cargar Imagen");
        fechaChooser = new JDateChooser();
        fechaChooser.setDate(new Date()); // fecha por defecto: actual

        panelMovie.add(new JLabel("Código:"));
        panelMovie.add(codigoField);
        panelMovie.add(new JLabel("Nombre:"));
        panelMovie.add(nombreField);
        panelMovie.add(new JLabel("Precio Base:"));
        panelMovie.add(precioField);
        panelMovie.add(new JLabel("Cantidad Disponible:"));
        panelMovie.add(cantidadSpinner);
        panelMovie.add(new JLabel("Imagen:"));
        panelMovie.add(cargarImagenBtn);
        panelMovie.add(new JLabel("Fecha Estreno:"));
        panelMovie.add(fechaChooser);

        panelGame = new JPanel(new GridLayout(6, 2, 5, 5));
        panelGame.setBorder(BorderFactory.createTitledBorder("Datos del Videojuego"));
        JTextField codigoFieldG = new JTextField();
        JTextField nombreFieldG = new JTextField();
        JTextField precioFieldG = new JTextField();
        JSpinner cantidadSpinnerG = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JLabel imagenLabelG = new JLabel("Imagen no cargada");
        JButton cargarImagenBtnG = new JButton("Cargar Imagen");
        JDateChooser fechaChooserG = new JDateChooser();
        fechaChooserG.setDate(new Date());

        panelGame.add(new JLabel("Código:"));
        panelGame.add(codigoFieldG);
        panelGame.add(new JLabel("Nombre:"));
        panelGame.add(nombreFieldG);
        panelGame.add(new JLabel("Precio Base:"));
        panelGame.add(precioFieldG);
        panelGame.add(new JLabel("Cantidad Disponible:"));
        panelGame.add(cantidadSpinnerG);
        panelGame.add(new JLabel("Imagen:"));
        panelGame.add(cargarImagenBtnG);
        panelGame.add(new JLabel("Fecha Publicación:"));
        panelGame.add(fechaChooserG);

        cardPanel.add(panelMovie, "Movie");
        cardPanel.add(panelGame, "Game");
        add(cardPanel, BorderLayout.CENTER);

        //combobox para cambiar entre movie y game
        tipoComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardPanel.getLayout());
            cl.show(cardPanel, (String) tipoComboBox.getSelectedItem());
        });

        //implementar guardar
        JPanel botonPanel = new JPanel();
        JButton guardarBtn = new JButton("Guardar");
        botonPanel.add(guardarBtn);
        add(botonPanel, BorderLayout.SOUTH);
    }
}
