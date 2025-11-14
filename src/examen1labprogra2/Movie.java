/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 *
 * @author alexve
 */
public class Movie extends RentItem {
    private Calendar fechaEstreno;
    
    public Movie(String codigo, String nombre, double precioBase, ImageIcon imagen) {
        super(codigo, nombre, precioBase, imagen);
        this.fechaEstreno = Calendar.getInstance();
    }
    
    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }
    
    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
    
    public void setFechaEstreno(int year, int mes, int dia) {
        this.fechaEstreno.set(year, mes, dia);
    }
    
    public String getEstado() {
        Calendar hoy = Calendar.getInstance();
        int añoActual = hoy.get(Calendar.YEAR);
        int mesActual = hoy.get(Calendar.MONTH);
        
        int añoEstreno = fechaEstreno.get(Calendar.YEAR);
        int mesEstreno = fechaEstreno.get(Calendar.MONTH);
        
        int diferenciaAños = añoActual - añoEstreno;
        int diferenciaMeses = diferenciaAños * 12 + (mesActual - mesEstreno);
        
        if (diferenciaMeses <= 3) {
            return "ESTRENO";
        } else {
            return "NORMAL";
        }
    }
    
    
    public String toString() {
        return super.toString() + "Estado: " + getEstado() + " Movie";
    }
    
   
    public double pagoRenta(int dias) {
        double precioBaseTotal = getPrecioBase() * dias;
        String estadoPelicula = getEstado();
        double recargo = 0;
        
        if (estadoPelicula.equals("ESTRENO")) {
            if (dias > 2) {
                int diasExtra = dias - 2;
                recargo = diasExtra * 50;
            }
        } else { 
            if (dias > 5) {
                int diasExtra = dias - 5;
                recargo = diasExtra * 30;
            }
        }
        
        return precioBaseTotal + recargo;
    }
}
