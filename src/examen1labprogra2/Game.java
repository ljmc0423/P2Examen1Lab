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
            JOptionPane.showMessageDialog(null,
                "No hay especificaciones registradas.",
                "Especificaciones",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        StringBuilder sb = new StringBuilder("Especificaciones de " + this.getNombre() + ":\n\n");
        for (String e : especificaciones) {
            sb.append("• ").append(e).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(),
                "Ver Especificaciones",
                JOptionPane.PLAIN_MESSAGE,
                this.getImagen());
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
        String[] opciones = {
            "Actualizar fecha de publicación",
            "Agregar especificación",
            "Ver especificaciones",
            "Volver al menú principal"
        };

        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Opciones de Juego: " + this.getNombre(),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                this.getImagen(),
                opciones,
                opciones[3]
        );

        ejecutarOpcion(seleccion + 1);
    }

    public void ejecutarOpcion(int opcion) {
        
        switch (opcion) {
            case 1:
                actualizarFechaPublicacion();
                break;
                
            case 2:
                String nuevaEspec = JOptionPane.showInputDialog(
                        null,
                        "Ingrese la nueva especificación:"
                );

                if (nuevaEspec != null && !nuevaEspec.trim().isEmpty()) {
                    agregarEspecificacion(nuevaEspec.trim());
                    JOptionPane.showMessageDialog(null, "Especificación agregada con éxito.");
                } else if (nuevaEspec != null) {
                    JOptionPane.showMessageDialog(null, "No se agregó la especificación (campo vacío).");
                }
                break;
                
            case 3:
                listEspecificaciones(); 
                break;
                
           case 4:
                JOptionPane.showMessageDialog(null, "Volviendo al menú principal.");
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

        Object[] params = {"Seleccione la nueva fecha de publicación:", dateChooser};

        try {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    params,
                    "Actualizar Fecha",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                Date nuevaFecha = dateChooser.getDate();

                if (nuevaFecha != null) {
                    Calendar nuevoCal = Calendar.getInstance();
                    nuevoCal.setTime(nuevaFecha);
                    this.setFechaPublicacion(nuevoCal);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    JOptionPane.showMessageDialog(null,
                            "Fecha actualizada a: " + sdf.format(nuevaFecha));
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se seleccionó ninguna fecha.");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar la fecha:\n" + e.getMessage());
        }
    }
}
