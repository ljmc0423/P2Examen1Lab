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
        // Asumo que getImagen() existe en RentItem para obtener el ImageIcon
        return super.toString() + "| Fecha Publicación: " + dia + "/" + mes + "/" + year + " – PS3 Game";
    }
    
    @Override
    public double pagoRenta(int dias) {
        return PRECIO_RENTA_FIJO * dias;
    }
    
    @Override
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

    @Override
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
        try {
            String inputYear = JOptionPane.showInputDialog(null, "Ingrese el AÑO de publicación:", "Actualizar Fecha", JOptionPane.QUESTION_MESSAGE);
            if (inputYear == null) return;
            int year = Integer.parseInt(inputYear.trim());

            String inputMes = JOptionPane.showInputDialog(null, "Ingrese el MES de publicación (1-12):", "Actualizar Fecha", JOptionPane.QUESTION_MESSAGE);
            if (inputMes == null) return;
            int mes = Integer.parseInt(inputMes.trim());

            String inputDia = JOptionPane.showInputDialog(null, "Ingrese el DÍA de publicación:", "Actualizar Fecha", JOptionPane.QUESTION_MESSAGE);
            if (inputDia == null) return;
            int dia = Integer.parseInt(inputDia.trim());

            setFechaPublicacion(year, mes, dia);
            JOptionPane.showMessageDialog(null, "Fecha de publicación actualizada a: " + dia + "/" + mes + "/" + year, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El año, mes o día deben ser números enteros válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
             JOptionPane.showMessageDialog(null, "Error: Fecha ingresada no es válida. Verifique el mes (1-12) y el día.", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar la fecha: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
