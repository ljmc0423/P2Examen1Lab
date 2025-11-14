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
        setSize(650, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(46, 204, 113));
        headerPanel.setPreferredSize(new Dimension(650, 80));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Agregar Nuevo Item", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tipoPanel.setBackground(new Color(46, 204, 113));
        tipoPanel.add(new JLabel("Tipo:"));
        tipoComboBox = new JComboBox<>(new String[]{"Movie", "Game"});
        tipoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tipoComboBox.setPreferredSize(new Dimension(150, 30));
        tipoPanel.add(tipoComboBox);
        headerPanel.add(tipoPanel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel(new CardLayout());

        
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
        fechaChooser.setDate(new Date()); 

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
        panelMovie.add(new JLabel("Fecha Estreno:"));
        panelMovie.add(fechaChooser);

     
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
        panelGame.add(new JLabel("Fecha Publicacion:"));
        panelGame.add(fechaChooserG);

        cardPanel.add(panelMovie, "Movie");
        cardPanel.add(panelGame, "Game");
        add(cardPanel, BorderLayout.CENTER);

        
        tipoComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardPanel.getLayout());
            cl.show(cardPanel, (String) tipoComboBox.getSelectedItem());
        });

      
        cargarImagenBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
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

        cargarImagenBtnG.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
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

        
        JPanel botonPanel = new JPanel();
        guardarBtn = new JButton("Guardar");
        botonPanel.add(guardarBtn);
        volverBtn = new JButton("Volver al Menu");
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

    private void guardarMovie() {
        String codigo = codigoField.getText().trim();
        String nombre = nombreField.getText().trim();
        String precioStr = precioField.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>Por favor complete todos los campos.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (codigoExiste(codigo)) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>El codigo ya existe. Por favor use otro codigo.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                               + "<p style='color: #E74C3C;'>El precio debe ser un numero positivo.</p>"
                               + "</body></html>";
                JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>Por favor ingrese un precio valido.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (imagenSeleccionadaMovie == null) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>Por favor cargue una imagen.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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
        
        String mensajeExito = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                            + "<p style='color: #27AE60;'>Pelicula agregada exitosamente.</p>"
                            + "</body></html>";
        JOptionPane.showMessageDialog(this, mensajeExito, "Exito", JOptionPane.INFORMATION_MESSAGE);
        
        
        limpiarCamposMovie();
    }

    private void guardarGame(JTextField codigoFieldG, JTextField nombreFieldG, 
                             JTextField precioFieldG, JSpinner cantidadSpinnerG,
                             JDateChooser fechaChooserG, JLabel imagenLabelG) {
    
        String codigo = codigoFieldG.getText().trim();
        String nombre = nombreFieldG.getText().trim();
        String precioStr = precioFieldG.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>Por favor complete todos los campos.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (codigoExiste(codigo)) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>El codigo ya existe. Por favor use otro codigo.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                               + "<p style='color: #E74C3C;'>El precio debe ser un numero positivo.</p>"
                               + "</body></html>";
                JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>Por favor ingrese un precio valido.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

      
        if (imagenSeleccionadaGame == null) {
            String mensaje = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                           + "<p style='color: #E74C3C;'>Por favor cargue una imagen.</p>"
                           + "</body></html>";
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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
        
        String mensajeExito = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                            + "<p style='color: #27AE60;'>Videojuego agregado exitosamente.</p>"
                            + "</body></html>";
        JOptionPane.showMessageDialog(this, mensajeExito, "Exito", JOptionPane.INFORMATION_MESSAGE);
        
       
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