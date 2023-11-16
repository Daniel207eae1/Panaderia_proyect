/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author elbot
 */
public class Venta {
    public Date fecha;
    
    public String vendedor;
    
    public String cliente;
    
    // 0: Cantidad    1: Precio_Total
    public Map<String, int[]> productos;
    
    public int total;
    
    public Venta(Date fecha, String vendedor, String cliente, int total){
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.total = total;
    }
    
    public void agregar_producto(String producto, int cantidad, int precio) throws Exception{
        try {
            int[] productos_arr = new int[2];
            productos_arr[0] = cantidad;
            productos_arr[1] = precio;
            productos.put(producto, productos_arr);
        } 
        catch (Exception e) {
            throw new Exception("Agregar_producto: \n"+e.getMessage());
        }
    }
}
