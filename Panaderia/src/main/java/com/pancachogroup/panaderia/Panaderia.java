/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pancachogroup.panaderia;

//import Vistas.Inicio;
import Vistas.Inicio_Sesion;
import Vistas.Principal;


public class Panaderia {

    public static void main(String[] args){
        try {
            Inicio_Sesion inicio = new Inicio_Sesion();
            inicio.setVisible(true);
            
//            Principal principal = new Principal();
//            principal.setVisible(true);
        } 
        catch (Exception e) {
            System.out.println("Ocurrio un error en el main: "+e.getMessage());
        }
    }
}
