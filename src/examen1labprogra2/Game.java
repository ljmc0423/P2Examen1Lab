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
            JOptionPane.showMessageDialog(null, "No hay especificaciones registradas para este juego.", "Especificaciones", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Especificaciones de ").append(this.getNombre()).append(":\n\n");
        for (int i = 0; i < especificaciones.size(); i++) {
            sb.append(i + 1).append(". ").append(especificaciones.get(i)).append("\n");
        }
        
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
            String[] opciones = {
                "Actualizar fecha de publicación",
                "Agregar especificación",
                "Ver especificaciones",
                "Volver al menú principal"
            };

            String mensaje = "<html><body>"
                           + "<h2>Menú: **" + this.getNombre() + "**</h2>"
                           + "Seleccione una opción:"
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

            ejecutarOpcion(seleccion + 1); 

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el menú de la GUI.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage(), "Error Inesperado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ejecutarOpcion(int opcion) {
        
        switch (opcion) {
            case 1:
                actualizarFechaPublicacion();
                break;
                
            case 2:
                String nuevaEspec = JOptionPane.showInputDialog(
                    null, 
                    "Ingrese la nueva especificación (ej: 'Necesita 10GB de espacio'):", 
                    "Agregar Especificación", 
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (nuevaEspec != null && !nuevaEspec.trim().isEmpty()) {
                    agregarEspecificacion(nuevaEspec.trim());
                    JOptionPane.showMessageDialog(null, "Especificación agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else if (nuevaEspec != null) {
                     JOptionPane.showMessageDialog(null, "No se agregó la especificación. El campo estaba vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                break;
                
            case 3:
                listEspecificaciones(); 
                break;
                
            case 4:
                JOptionPane.showMessageDialog(null, "Volviendo al menú principal.", "Volver", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            default:
                if (opcion == 0) { // Corresponde al índice -1 de showOptionDialog + 1
                     JOptionPane.showMessageDialog(null, "Operación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                     JOptionPane.showMessageDialog(null, "Opción no válida seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void actualizarFechaPublicacion() {
    // 1. Crear el componente JDateChooser
    JDateChooser dateChooser = new JDateChooser();
    dateChooser.setCalendar(this.fechaPublicacion); // Establecer la fecha actual del juego

    // 2. Crear el mensaje y el arreglo de componentes
    String mensaje = "Seleccione la nueva fecha de publicación:";
    
    // El arreglo Object[] permite mezclar texto y componentes Swing
    Object[] params = {mensaje, dateChooser};

    try {
        // 3. Mostrar el diálogo de confirmación
        // Usamos showConfirmDialog para obtener una respuesta OK/Cancel
        int result = JOptionPane.showConfirmDialog(
            null, 
            params, 
            "Actualizar Fecha de Publicación", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE
        );

        // 4. Procesar la respuesta
        if (result == JOptionPane.OK_OPTION) {
            Date nuevaFecha = dateChooser.getDate();
            
            if (nuevaFecha != null) {
                // Convertir la Date seleccionada a Calendar y actualizar el atributo
                Calendar nuevoCalendar = Calendar.getInstance();
                nuevoCalendar.setTime(nuevaFecha);
                
                // Actualizar la fecha de publicación del Game
                this.setFechaPublicacion(nuevoCalendar);

                // Opcional: Mostrar la fecha formateada para confirmación
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaFormateada = sdf.format(nuevaFecha);
                
                JOptionPane.showMessageDialog(
                    null, 
                    "Fecha de publicación actualizada a: " + fechaFormateada, 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                 JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        // Si el resultado es CANCEL_OPTION o se cierra, no se hace nada.

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar la fecha: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
