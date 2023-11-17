/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import com.google.api.client.util.ArrayMap;
import java.util.Map;

/**
 *
 * @author elbot
 */
public class Venta {
    public String id;
    
    public String fecha;
    
    public String vendedor;
    
    public String cliente;
    
    public int cantidad_productos;
    
    // 0: Cantidad    1: Precio_Total
    public Map<String, int[]> productos = new ArrayMap<>();
    
    public int total;
    
    public Venta(String id, String fecha, String vendedor, String cliente, int total, int cantidad_productos){
        this.id = id;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.total = total;
        this.cantidad_productos = cantidad_productos;
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
