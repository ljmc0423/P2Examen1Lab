/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
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
        System.out.println((indice + 1) + ". " + especificaciones.get(indice));
        listEspecificaciones(indice + 1);
    }
    
    @Override
    public String toString() {
        int year = fechaPublicacion.get(Calendar.YEAR);
        int mes = fechaPublicacion.get(Calendar.MONTH) + 1;
        int dia = fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        return super.toString() + "| Fecha Publicación: " + dia + "/" + mes + "/" + year + " – PS3 Game";
    }
    
    @Override
    public double pagoRenta(int dias) {
        return PRECIO_RENTA_FIJO * dias;
    }
    
    @Override
    public void submenu() {
        System.out.println("\n=== MENÚ DE OPCIONES DEL JUEGO ===");
        System.out.println("1. Actualizar fecha de publicación");
        System.out.println("2. Agregar especificación");
        System.out.println("3. Ver especificaciones");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    @Override
    public void ejecutarOpcion(int opcion) {
        Scanner scanner = new Scanner(System.in);
        
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el año: ");
                int year = scanner.nextInt();
                System.out.print("Ingrese el mes (1-12): ");
                int mes = scanner.nextInt() - 1; // Calendar usa meses 0-11
                System.out.print("Ingrese el día: ");
                int dia = scanner.nextInt();
                setFechaPublicacion(year, mes, dia);
                System.out.println("Fecha de publicación actualizada correctamente.");
                break;
                
            case 2:
                scanner.nextLine(); // Limpiar buffer
                System.out.print("Ingrese la especificación técnica: ");
                String especificacion = scanner.nextLine();
                agregarEspecificacion(especificacion);
                System.out.println("Especificación agregada correctamente.");
                break;
                
            case 3:
                System.out.println("\n=== ESPECIFICACIONES TÉCNICAS ===");
                if (especificaciones.isEmpty()) {
                    System.out.println("No hay especificaciones registradas.");
                } else {
                    listEspecificaciones();
                }
                break;
                
            case 4:
                System.out.println("Saliendo del menú...");
                break;
                
            default:
                System.out.println("Opción inválida. Por favor seleccione una opción del 1 al 4.");
                break;
        }
    }
}
