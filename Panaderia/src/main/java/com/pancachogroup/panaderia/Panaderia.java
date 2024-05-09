/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pancachogroup.panaderia;

//import Vistas.Inicio;
import Contextos.Conexion_Firestore;
import Vistas.Administracion.Inicio_Sesion;


public class Panaderia {

    public static void main(String[] args){
        try {
            Panaderia.showWindow();
            
            Conexion_Firestore.conectarFirebase();
        } 
        catch (Exception e) {
            System.out.println("Ocurrio un error en el main: "+e.getMessage());
        }
    }
    
    public static Inicio_Sesion showWindow(){
        Inicio_Sesion inicio = new Inicio_Sesion();
        inicio.setVisible(true);
        return inicio;
    }
}
