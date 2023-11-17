/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;



public class Empleado {
    public String id;
    
    public String cedula;
    
    public String nombre;
    
    public String apellido;
    
    public int salario;
    
    public String celular;
    
    public String correo;
    
    public String fecha_nacimiento;
    
    public String fecha_afiliacion;
    
    public String cargo;
    
    public Empleado(String id, String cedula, String nombre, String apellido, int salario, String celular, String correo, String fecha_nacimiento, String fecha_afiliacion, String cargo){
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.celular = celular;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_afiliacion = fecha_afiliacion;
        this.cargo = cargo;
    }
}
