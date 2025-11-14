/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane; 
import java.awt.HeadlessException;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ljmc2
 */
public class Game extends RentItem implements MenuActions {
    private Calendar fechaPublicacion;
    private ArrayList<String> especificaciones;
    private static final double PRECIO_RENTA_FIJO = 20.0;
    
    public Game(String codigo, String nombre, double precioBase, ImageIcon imagen) {
        super(codigo, nombre, precioBase, imagen);
        this.fechaPublicacion = Calendar.getInstance();
        this.especificaciones = new ArrayList<>();
    }

    public Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public void setFechaPublicacion(Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public void setFechaPublicacion(int year, int mes, int dia) {
        this.fechaPublicacion.set(year, mes - 1, dia); 
    }
    
    public ArrayList<String> getEspecificaciones() {
        return especificaciones;
    }
    
    public void agregarEspecificacion(String especificacion) {
        especificaciones.add(especificacion);
    }
    
    public void listEspecificaciones() {
        if (especificaciones.isEmpty()) {
            String mensajeVacio = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                                + "<p style='color: #7F8C8D;'>No hay especificaciones registradas para este juego.</p>"
                                + "</body></html>";
            JOptionPane.showMessageDialog(null, mensajeVacio, "Especificaciones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='width: 350px; padding: 10px; font-family: Segoe UI;'>");
        sb.append("<h3 style='color: #9B59B6;'>Especificaciones de ").append(this.getNombre()).append("</h3>");
        sb.append("<ul style='padding-left: 20px;'>");
        for (int i = 0; i < especificaciones.size(); i++) {
            sb.append("<li style='margin: 5px 0;'>").append(especificaciones.get(i)).append("</li>");
        }
        sb.append("</ul></body></html>");
        
        JOptionPane.showMessageDialog(null, sb.toString(), "Ver Especificaciones", JOptionPane.PLAIN_MESSAGE, this.getImagen());
    }
    
    @Override
    public String toString() {
        int year = fechaPublicacion.get(Calendar.YEAR);
        int mes = fechaPublicacion.get(Calendar.MONTH) + 1; // Meses son 0-based
        int dia = fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        return super.toString() + "| Fecha Publicacion: " + dia + "/" + mes + "/" + year + " – PS3 Game";

    }
    
    
    public double pagoRenta(int dias) {
        return PRECIO_RENTA_FIJO * dias;
    }
    
  
    public void submenu() {
        try {
            javax.swing.UIManager.put("OptionPane.background", new java.awt.Color(236, 240, 241));
            javax.swing.UIManager.put("Panel.background", new java.awt.Color(236, 240, 241));
            javax.swing.UIManager.put("Button.background", new java.awt.Color(155, 89, 182));
            javax.swing.UIManager.put("Button.foreground", java.awt.Color.WHITE);
            javax.swing.UIManager.put("Button.font", new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            javax.swing.UIManager.put("Button.focus", new java.awt.Color(142, 68, 173));
            
            String[] opciones = {
                "Actualizar fecha de publicacion",
                "Agregar especificacion",
                "Ver especificaciones",
                "Volver al menu principal"
            };

            String mensaje = "<html><body style='width: 350px; padding: 15px; background-color: #ECF0F1;'>"
                           + "<h2 style='color: #9B59B6; font-family: Segoe UI; margin: 0 0 15px 0;'>Menu: " + this.getNombre() + "</h2>"
                           + "<p style='font-family: Segoe UI; font-size: 13px; color: #2C3E50; margin: 0;'>Seleccione una opcion:</p>"
                           + "</body></html>";
            
            int seleccion = JOptionPane.showOptionDialog(
                null,                           
                mensaje,                        
                "Opciones de Juego",            
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE,      
                this.getImagen(),               
                opciones,                       
                opciones[3]                     
            );

            javax.swing.UIManager.put("OptionPane.background", null);
            javax.swing.UIManager.put("Panel.background", null);
            javax.swing.UIManager.put("Button.background", null);
            javax.swing.UIManager.put("Button.foreground", null);
            javax.swing.UIManager.put("Button.font", null);
            javax.swing.UIManager.put("Button.focus", null);

            ejecutarOpcion(seleccion + 1); 

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el menu de la GUI.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e.getMessage(), "Error Inesperado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ejecutarOpcion(int opcion) {
        
        switch (opcion) {
            case 1:
                actualizarFechaPublicacion();
                break;
                
            case 2:
                String mensajeInput = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                                    + "<p>Ingrese la nueva especificacion:</p>"
                                    + "<p style='color: #7F8C8D; font-size: 10px;'>(ej: 'Necesita 10GB de espacio')</p>"
                                    + "</body></html>";
                String nuevaEspec = JOptionPane.showInputDialog(
                    null, 
                    mensajeInput, 
                    "Agregar Especificacion", 
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (nuevaEspec != null && !nuevaEspec.trim().isEmpty()) {
                    agregarEspecificacion(nuevaEspec.trim());
                    String mensajeExito = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                                        + "<p style='color: #27AE60;'>Especificacion agregada con exito.</p>"
                                        + "</body></html>";
                    JOptionPane.showMessageDialog(null, mensajeExito, "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else if (nuevaEspec != null) {
                    String mensajeVacio = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                                        + "<p style='color: #E67E22;'>No se agrego la especificacion. El campo estaba vacio.</p>"
                                        + "</body></html>";
                     JOptionPane.showMessageDialog(null, mensajeVacio, "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                break;
                
            case 3:
                listEspecificaciones(); 
                break;
                
            case 4:
                String mensajeVolver = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                                     + "<p>Volviendo al menu principal.</p>"
                                     + "</body></html>";
                JOptionPane.showMessageDialog(null, mensajeVolver, "Volver", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            default:
                if (opcion == 0) {
                    String mensajeCancelado = "<html><body style='width: 200px; padding: 10px; font-family: Segoe UI;'>"
                                            + "<p>Operacion cancelada.</p>"
                                            + "</body></html>";
                     JOptionPane.showMessageDialog(null, mensajeCancelado, "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String mensajeError = "<html><body style='width: 200px; padding: 10px; font-family: Segoe UI;'>"
                                        + "<p style='color: #E74C3C;'>Opcion no valida seleccionada.</p>"
                                        + "</body></html>";
                     JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void actualizarFechaPublicacion() {
    JDateChooser dateChooser = new JDateChooser();
    dateChooser.setCalendar(this.fechaPublicacion);

    String mensaje = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                   + "<p style='font-size: 12px;'>Seleccione la nueva fecha de publicacion:</p>"
                   + "</body></html>";
    
    Object[] params = {mensaje, dateChooser};

    try {
        int result = JOptionPane.showConfirmDialog(
            null, 
            params, 
            "Actualizar Fecha de Publicacion", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            Date nuevaFecha = dateChooser.getDate();
            
            if (nuevaFecha != null) {
                Calendar nuevoCalendar = Calendar.getInstance();
                nuevoCalendar.setTime(nuevaFecha);
                
                this.setFechaPublicacion(nuevoCalendar);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaFormateada = sdf.format(nuevaFecha);
                
                String mensajeExito = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                                    + "<p style='color: #27AE60;'>Fecha de publicacion actualizada a:</p>"
                                    + "<p style='font-weight: bold; color: #2C3E50;'>" + fechaFormateada + "</p>"
                                    + "</body></html>";
                
                JOptionPane.showMessageDialog(
                    null, 
                    mensajeExito, 
                    "Exito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                String mensajeAdvertencia = "<html><body style='width: 250px; padding: 10px; font-family: Segoe UI;'>"
                                          + "<p style='color: #E67E22;'>No se selecciono ninguna fecha.</p>"
                                          + "</body></html>";
                 JOptionPane.showMessageDialog(null, mensajeAdvertencia, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

    } catch (Exception e) {
        String mensajeError = "<html><body style='width: 300px; padding: 10px; font-family: Segoe UI;'>"
                            + "<p style='color: #E74C3C;'>Ocurrio un error al actualizar la fecha:</p>"
                            + "<p style='font-size: 10px;'>" + e.getMessage() + "</p>"
                            + "</body></html>";
        JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
