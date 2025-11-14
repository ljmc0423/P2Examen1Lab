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

        // Panel para Movie
        panelMovie = new JPanel(new GridLayout(6, 2, 5, 5));
        panelMovie.setBorder(BorderFactory.createTitledBorder("Datos de la Película"));
        codigoField = new JTextField();
        nombreField = new JTextField();
        precioField = new JTextField();
        cantidadSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        imagenLabel = new JLabel("Imagen no cargada", SwingConstants.CENTER);
        imagenLabel.setBorder(BorderFactory.createEtchedBorder());
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

        // Panel para Game (reutiliza los mismos campos)
        panelGame = new JPanel(new GridLayout(6, 2, 5, 5));
        panelGame.setBorder(BorderFactory.createTitledBorder("Datos del Videojuego"));
        
        JTextField codigoFieldG = new JTextField();
        JTextField nombreFieldG = new JTextField();
        JTextField precioFieldG = new JTextField();
        JSpinner cantidadSpinnerG = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JLabel imagenLabelG = new JLabel("Imagen no cargada", SwingConstants.CENTER);
        imagenLabelG.setBorder(BorderFactory.createEtchedBorder());
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

        // Combobox para cambiar entre movie y game
        tipoComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardPanel.getLayout());
            cl.show(cardPanel, (String) tipoComboBox.getSelectedItem());
        });

        // Cargar imagen para Movie
        cargarImagenBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
                // Redimensionar imagen si es muy grande
                if (imagen.getIconWidth() > 200 || imagen.getIconHeight() > 200) {
                    Image img = imagen.getImage();
                    Image nuevaImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imagen = new ImageIcon(nuevaImg);
                }
                imagenSeleccionadaMovie = imagen;
                imagenLabel.setIcon(imagen);
                imagenLabel.setText("");
            }
        });

        // Cargar imagen para Game
        cargarImagenBtnG.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
                // Redimensionar imagen si es muy grande
                if (imagen.getIconWidth() > 200 || imagen.getIconHeight() > 200) {
                    Image img = imagen.getImage();
                    Image nuevaImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    imagen = new ImageIcon(nuevaImg);
                }
                imagenSeleccionadaGame = imagen;
                imagenLabelG.setIcon(imagen);
                imagenLabelG.setText("");
            }
        });

        // Panel de botones
        JPanel botonPanel = new JPanel();
        guardarBtn = new JButton("Guardar");
        botonPanel.add(guardarBtn);
        volverBtn = new JButton("Volver al Menu");
        botonPanel.add(volverBtn);
        add(botonPanel, BorderLayout.SOUTH);

        // Guardar item
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

    private void guardarMovie() {
        // Validar campos
        String codigo = codigoField.getText().trim();
        String nombre = nombreField.getText().trim();
        String precioStr = precioField.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar código único
        if (codigoExiste(codigo)) {
            JOptionPane.showMessageDialog(this, "El código ya existe. Por favor use otro código.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar precio
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un precio válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar imagen
        if (imagenSeleccionadaMovie == null) {
            JOptionPane.showMessageDialog(this, "Por favor cargue una imagen.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear Movie
        Movie movie = new Movie(codigo, nombre, precio, imagenSeleccionadaMovie);
        
        // Establecer fecha de estreno
        Date fecha = fechaChooser.getDate();
        if (fecha != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            movie.setFechaEstreno(cal.get(Calendar.YEAR), 
                                 cal.get(Calendar.MONTH), 
                                 cal.get(Calendar.DAY_OF_MONTH));
        }

        // Agregar a la lista
        items.add(movie);
        
        JOptionPane.showMessageDialog(this, "Película agregada exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        // Limpiar campos
        limpiarCamposMovie();
    }

    private void guardarGame(JTextField codigoFieldG, JTextField nombreFieldG, 
                             JTextField precioFieldG, JSpinner cantidadSpinnerG,
                             JDateChooser fechaChooserG, JLabel imagenLabelG) {
        // Validar campos
        String codigo = codigoFieldG.getText().trim();
        String nombre = nombreFieldG.getText().trim();
        String precioStr = precioFieldG.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar código único
        if (codigoExiste(codigo)) {
            JOptionPane.showMessageDialog(this, "El código ya existe. Por favor use otro código.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar precio
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un precio válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar imagen
        if (imagenSeleccionadaGame == null) {
            JOptionPane.showMessageDialog(this, "Por favor cargue una imagen.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear Game
        Game game = new Game(codigo, nombre, precio, imagenSeleccionadaGame);
        
        // Establecer fecha de publicación
        Date fecha = fechaChooserG.getDate();
        if (fecha != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            game.setFechaPublicacion(cal.get(Calendar.YEAR), 
                                    cal.get(Calendar.MONTH), 
                                    cal.get(Calendar.DAY_OF_MONTH));
        }

        // Agregar a la lista
        items.add(game);
        
        JOptionPane.showMessageDialog(this, "Videojuego agregado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        // Limpiar campos
        limpiarCamposGame(codigoFieldG, nombreFieldG, precioFieldG, cantidadSpinnerG,
                         fechaChooserG, imagenLabelG);
    }

    private boolean codigoExiste(String codigo) {
        for (RentItem item : items) {
            if (item.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
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
