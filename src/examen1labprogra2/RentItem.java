/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

/**
 *
 * @author ljmc2
 */
import javax.swing.ImageIcon;

public abstract class RentItem {
    private String codigo;
    private String nombre;
    private double precioBase;
    private int copiasDisponibles;
    private ImageIcon imagen;

    
    public RentItem(String codigo, String nombre, double precioBase, ImageIcon image) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.copiasDisponibles = 0;
        this.imagen = image;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }
    
    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "| Nombre: " + nombre + "| Precio Base: " + precioBase
                + "| Copias Disponibles: " + copiasDisponibles;
    }

    public abstract double pagoRenta(int dias);
}
