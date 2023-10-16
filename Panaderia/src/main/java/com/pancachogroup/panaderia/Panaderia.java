/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pancachogroup.panaderia;

//import Vistas.Inicio;
import Vistas.Principal;


public class Panaderia {

    public static void main(String[] args){
//        Inicio inicio_jframe = new Inicio();
//        inicio_jframe.setVisible(true);
        try {
            Principal principal = new Principal();
            principal.setVisible(true);
        } 
        catch (Exception e) {
            System.out.println("Ocurrio un error en el main: "+e.getMessage());
        }
    }
}
