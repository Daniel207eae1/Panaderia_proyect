/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

public class Producto {
    
    public String id;
    
    public String nombre;
    
    public int stock;
    
    public int precio_u;
    
    public String distribuidor;
    
    public String tipo;
    
    public Producto(String id, String nombre, int stock, int precio_u, String distribuidor, String tipo){
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio_u = precio_u;
        this.distribuidor = distribuidor;
        this.tipo = tipo;
    }
}
