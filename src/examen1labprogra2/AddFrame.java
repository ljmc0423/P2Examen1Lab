/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
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
    private JButton cargarImagenBtn, volverBtn, guardarBtn;
    private JDateChooser fechaChooser;
    private JPanel panelMovie, panelGame;
    private ImageIcon imagenSeleccionadaMovie;
    private ImageIcon imagenSeleccionadaGame;
    private ArrayList<RentItem> items;
    private MainGUI mainGUI;

    public AddFrame(ArrayList<RentItem> items, MainGUI mainGUI) {
        this.items = items;
        this.mainGUI = mainGUI;

        setTitle("Agregar Item");
        setSize(600, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipoPanel.add(new JLabel("Tipo:"));
        tipoComboBox = new JComboBox<>(new String[]{"Movie", "Game"});
        tipoPanel.add(tipoComboBox);
        add(tipoPanel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel(new CardLayout());

        panelMovie = new JPanel(new GridLayout(7, 2, 5, 5));
        panelMovie.setBorder(BorderFactory.createTitledBorder("Datos de la Película"));

        codigoField = new JTextField();
        nombreField = new JTextField();
        precioField = new JTextField();
        cantidadSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        imagenLabel = new JLabel("Imagen no cargada", SwingConstants.CENTER);
        imagenLabel.setBorder(BorderFactory.createEtchedBorder());
        fechaChooser = new JDateChooser();
        fechaChooser.setDate(new Date());
        cargarImagenBtn = new JButton("Cargar Imagen");

        JLabel vistaPreviaMovie = new JLabel("Vista previa", SwingConstants.CENTER);
        vistaPreviaMovie.setPreferredSize(new Dimension(200, 200));
        vistaPreviaMovie.setBorder(BorderFactory.createEtchedBorder());

        panelMovie.add(new JLabel("Codigo:"));
        panelMovie.add(codigoField);
        panelMovie.add(new JLabel("Nombre:"));
        panelMovie.add(nombreField);
        panelMovie.add(new JLabel("Precio Base:"));
        panelMovie.add(precioField);
        panelMovie.add(new JLabel("Cantidad Disponible:"));
        panelMovie.add(cantidadSpinner);
        panelMovie.add(new JLabel("Imagen:"));
        panelMovie.add(cargarImagenBtn);
        panelMovie.add(new JLabel("Vista previa Imagen:"));
        panelMovie.add(vistaPreviaMovie);
        panelMovie.add(new JLabel("Fecha Estreno:"));
        panelMovie.add(fechaChooser);

        cargarImagenBtn.addActionListener(e -> {
            File archivo = seleccionarImagen();
            if (archivo != null) {
                ImageIcon imagen = escalarImagen(archivo, 200, 200);
                imagenSeleccionadaMovie = imagen;
                imagenLabel.setIcon(imagen);
                imagenLabel.setText("");
                vistaPreviaMovie.setIcon(imagen);
                vistaPreviaMovie.setText("");
            }
        });

        panelGame = new JPanel(new GridLayout(7, 2, 5, 5));
        panelGame.setBorder(BorderFactory.createTitledBorder("Datos del Videojuego"));

        JTextField codigoFieldG = new JTextField();
        JTextField nombreFieldG = new JTextField();
        JTextField precioFieldG = new JTextField();
        JSpinner cantidadSpinnerG = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JLabel imagenLabelG = new JLabel("Imagen no cargada", SwingConstants.CENTER);
        imagenLabelG.setBorder(BorderFactory.createEtchedBorder());
        JDateChooser fechaChooserG = new JDateChooser();
        fechaChooserG.setDate(new Date());
        JButton cargarImagenBtnG = new JButton("Cargar Imagen");

        JLabel vistaPreviaGame = new JLabel("Vista previa", SwingConstants.CENTER);
        vistaPreviaGame.setPreferredSize(new Dimension(200, 200));
        vistaPreviaGame.setBorder(BorderFactory.createEtchedBorder());

        panelGame.add(new JLabel("Codigo:"));
        panelGame.add(codigoFieldG);
        panelGame.add(new JLabel("Nombre:"));
        panelGame.add(nombreFieldG);
        panelGame.add(new JLabel("Precio Base:"));
        panelGame.add(precioFieldG);
        panelGame.add(new JLabel("Cantidad Disponible:"));
        panelGame.add(cantidadSpinnerG);
        panelGame.add(new JLabel("Imagen:"));
        panelGame.add(cargarImagenBtnG);
        panelGame.add(new JLabel("Vista previa Imagen:"));
        panelGame.add(vistaPreviaGame);
        panelGame.add(new JLabel("Fecha Publicacion:"));
        panelGame.add(fechaChooserG);

        cargarImagenBtnG.addActionListener(e -> {
            File archivo = seleccionarImagen();
            if (archivo != null) {
                ImageIcon imagen = escalarImagen(archivo, 200, 200);
                imagenSeleccionadaGame = imagen;
                imagenLabelG.setIcon(imagen);
                imagenLabelG.setText("");
                vistaPreviaGame.setIcon(imagen);
                vistaPreviaGame.setText("");
            }
        });

        cardPanel.add(panelMovie, "Movie");
        cardPanel.add(panelGame, "Game");
        add(cardPanel, BorderLayout.CENTER);

        tipoComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardPanel.getLayout());
            cl.show(cardPanel, (String) tipoComboBox.getSelectedItem());
        });

        JPanel botonPanel = new JPanel();
        guardarBtn = new JButton("Guardar");
        volverBtn = new JButton("Volver al Menu");
        botonPanel.add(guardarBtn);
        botonPanel.add(volverBtn);
        add(botonPanel, BorderLayout.SOUTH);

        guardarBtn.addActionListener(e -> {
            String tipo = (String) tipoComboBox.getSelectedItem();
            if (tipo.equals("Movie")) {
                guardarMovie();
            } else {
                guardarGame(codigoFieldG, nombreFieldG, precioFieldG, cantidadSpinnerG,
                        fechaChooserG, imagenLabelG);
            }
        });

        volverBtn.addActionListener(e -> {
            mainGUI.setVisible(true);
            this.dispose();
        });
    }


    private File seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (archivo != null && esArchivoImagen(archivo)) return archivo;
        }
        return null;
    }

    private ImageIcon escalarImagen(File archivo, int ancho, int alto) {
        ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
        Image img = imagen.getImage();
        Image nuevaImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImg);
    }

    private boolean esArchivoImagen(File archivo) {
        String nombre = archivo.getName().toLowerCase();
        return nombre.endsWith(".jpg") || nombre.endsWith(".jpeg") ||
                nombre.endsWith(".png") || nombre.endsWith(".gif") ||
                nombre.endsWith(".bmp");
    }

    private boolean codigoExiste(String codigo) {
        for (RentItem item : items) {
            if (item.getCodigo().equals(codigo)) return true;
        }
        return false;
    }

    private void guardarMovie() {
        String codigo = codigoField.getText().trim();
        String nombre = nombreField.getText().trim();
        String precioStr = precioField.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (codigoExiste(codigo)) {
            JOptionPane.showMessageDialog(this, "El codigo ya existe.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (imagenSeleccionadaMovie == null) {
            JOptionPane.showMessageDialog(this, "Por favor cargue una imagen.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Movie movie = new Movie(codigo, nombre, precio, imagenSeleccionadaMovie);

        Date fecha = fechaChooser.getDate();
        if (fecha != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            movie.setFechaEstreno(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH));
        }

        items.add(movie);
        JOptionPane.showMessageDialog(this, "Pelicula agregada exitosamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposMovie();
    }

    private void guardarGame(JTextField codigoFieldG, JTextField nombreFieldG,
                             JTextField precioFieldG, JSpinner cantidadSpinnerG,
                             JDateChooser fechaChooserG, JLabel imagenLabelG) {
        String codigo = codigoFieldG.getText().trim();
        String nombre = nombreFieldG.getText().trim();
        String precioStr = precioFieldG.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (codigoExiste(codigo)) {
            JOptionPane.showMessageDialog(this, "El codigo ya existe.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (imagenSeleccionadaGame == null) {
            JOptionPane.showMessageDialog(this, "Por favor cargue una imagen.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Game game = new Game(codigo, nombre, precio, imagenSeleccionadaGame);

        Date fecha = fechaChooserG.getDate();
        if (fecha != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            game.setFechaPublicacion(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH));
        }

        items.add(game);
        JOptionPane.showMessageDialog(this, "Videojuego agregado exitosamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposGame(codigoFieldG, nombreFieldG, precioFieldG, cantidadSpinnerG,
                fechaChooserG, imagenLabelG);
    }

    private void limpiarCamposMovie() {
        codigoField.setText("");
        nombreField.setText("");
        precioField.setText("");
        cantidadSpinner.setValue(0);
        fechaChooser.setDate(new Date());
        imagenLabel.setIcon(null);
        imagenLabel.setText("Imagen no cargada");
        imagenSeleccionadaMovie = null;
    }

    private void limpiarCamposGame(JTextField codigoFieldG, JTextField nombreFieldG,
                                   JTextField precioFieldG, JSpinner cantidadSpinnerG,
                                   JDateChooser fechaChooserG, JLabel imagenLabelG) {
        codigoFieldG.setText("");
        nombreFieldG.setText("");
        precioFieldG.setText("");
        cantidadSpinnerG.setValue(0);
        fechaChooserG.setDate(new Date());
        imagenLabelG.setIcon(null);
        imagenLabelG.setText("Imagen no cargada");
        imagenSeleccionadaGame = null;
    }
}
