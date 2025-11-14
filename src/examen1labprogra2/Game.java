/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;

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
        this.fechaPublicacion.set(year, mes, dia);
    }
    
    public ArrayList<String> getEspecificaciones() {
        return especificaciones;
    }
    
    public void agregarEspecificacion(String especificacion) {
        especificaciones.add(especificacion);
    }
    
    public void listEspecificaciones() {
        listEspecificaciones(0);
    }
    
    private void listEspecificaciones(int indice) {
        if (indice >= especificaciones.size()) {
            return;
        }
        
        listEspecificaciones(indice + 1);
    }
    
    @Override
    public String toString() {
        int year = fechaPublicacion.get(Calendar.YEAR);
        int mes = fechaPublicacion.get(Calendar.MONTH) + 1;
        int dia = fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        return super.toString() + "| Fecha Publicacion: " + dia + "/" + mes + "/" + year + " – PS3 Game";
    }
    
    
    public double pagoRenta(int dias) {
        return PRECIO_RENTA_FIJO * dias;
    }
    
  
    public void submenu() {
        
    }
    
  
    public void ejecutarOpcion(int opcion) {
        
        switch (opcion) {
            case 1:
               
                break;
                
            case 2:
            
                break;
                
            case 3:
                
                listEspecificaciones();
                break;
                
            case 4:
               
                break;
                
            default:
                
                break;
        }
    }
}
