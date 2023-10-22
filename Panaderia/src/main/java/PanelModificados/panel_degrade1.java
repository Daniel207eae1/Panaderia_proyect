/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PanelModificados;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @web http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class panel_degrade1 extends javax.swing.JPanel{
    
    private Color color1 = Color.decode("#C6572D");
    private Color color2 = Color.decode("#CB8824");
    
    public panel_degrade1(){
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        Graphics2D G2D = (Graphics2D) g;
        
        int w = getWidth();
        int h = getHeight();
        
        GradientPaint gp = new GradientPaint(0,0, color1, 0,h, color2);
        
        G2D.setPaint(gp);
        G2D.fillRect(0, 0, w, h);
    }
}
