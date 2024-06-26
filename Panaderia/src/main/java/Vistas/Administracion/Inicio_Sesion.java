/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.Administracion;

import Vistas.Facturacion.Facturacion;
import Contextos.Conexion_Firestore;
import Modificados.Colores;
import Modificados.panel_degrade1;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author elbot
 */
public class Inicio_Sesion extends javax.swing.JFrame {
    
    public Inicio_Sesion() {
        try {
            UIManager.setLookAndFeel( new FlatMacLightLaf());
            //UIManager.setLookAndFeel( new FlatMacDarkLaf());
            this.setUndecorated(true);
            initComponents();
            SetImageLabel();
            
            //Panel1
            panel_degrade1 p1 = new panel_degrade1();
            p1.setSize(jPanel1.getSize());
            jPanel1.add(p1);
        }    
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error en Inicio de sesion: "+e.getMessage());
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tb_facturacion = new javax.swing.JToggleButton();
        tb_administracion = new javax.swing.JToggleButton();
        Logo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cb_sucursal = new javax.swing.JComboBox<>();
        tf_usuario = new javax.swing.JTextField();
        pf_contraseña = new javax.swing.JPasswordField();
        btn_entrar = new javax.swing.JButton();
        btn_close = new javax.swing.JLabel();
        btn_minimizar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de sesion");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setLocationByPlatform(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tb_facturacion.setBackground(new java.awt.Color(244, 244, 244));
        buttonGroup1.add(tb_facturacion);
        tb_facturacion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tb_facturacion.setForeground(new java.awt.Color(51, 51, 51));
        tb_facturacion.setText("FACTURACION");
        tb_facturacion.setAlignmentY(0.0F);
        tb_facturacion.setBorderPainted(false);
        tb_facturacion.setName("Facturacion"); // NOI18N
        tb_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_facturacionActionPerformed(evt);
            }
        });

        tb_administracion.setBackground(new java.awt.Color(244, 244, 244));
        buttonGroup1.add(tb_administracion);
        tb_administracion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tb_administracion.setForeground(new java.awt.Color(51, 51, 51));
        tb_administracion.setText("ADMINISTRACION");
        tb_administracion.setAlignmentY(0.0F);
        tb_administracion.setBorderPainted(false);
        tb_administracion.setName("Administracion"); // NOI18N

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo Total.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tb_administracion, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(tb_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(tb_administracion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
        );

        jPanel2.setBackground(new java.awt.Color(244, 244, 244));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cb_sucursal.setBackground(new java.awt.Color(217, 217, 217));
        cb_sucursal.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        cb_sucursal.setForeground(new java.awt.Color(51, 51, 51));
        cb_sucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sucursal Bello", "Sucursal Robledo" }));

        tf_usuario.setBackground(new java.awt.Color(217, 217, 217));
        tf_usuario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_usuario.setForeground(new java.awt.Color(51, 51, 51));
        tf_usuario.setBorder(null);
        tf_usuario.setName("Usuario"); // NOI18N

        pf_contraseña.setBackground(new java.awt.Color(217, 217, 217));
        pf_contraseña.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        pf_contraseña.setForeground(new java.awt.Color(51, 51, 51));
        pf_contraseña.setBorder(null);
        pf_contraseña.setName("Contraseña"); // NOI18N
        pf_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_contraseñaActionPerformed(evt);
            }
        });

        btn_entrar.setBackground(new java.awt.Color(217, 217, 217));
        btn_entrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_entrar.setForeground(new java.awt.Color(0, 0, 0));
        btn_entrar.setText("Entrar");
        btn_entrar.setBorderPainted(false);
        btn_entrar.setName("Entrar"); // NOI18N
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });

        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/X.png"))); // NOI18N
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_closeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_closeMousePressed(evt);
            }
        });

        btn_minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/-.png"))); // NOI18N
        btn_minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_minimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_minimizarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_minimizarMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Contraseña");

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Dialog", 2, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Olvidé mi contraseña");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_minimizar)
                .addGap(18, 18, 18)
                .addComponent(btn_close))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_sucursal, javax.swing.GroupLayout.Alignment.TRAILING, 0, 331, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pf_contraseña)
                                .addComponent(tf_usuario)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                            .addComponent(btn_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_close)
                    .addComponent(btn_minimizar))
                .addGap(56, 56, 56)
                .addComponent(cb_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pf_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(55, 55, 55)
                .addComponent(btn_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pf_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pf_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pf_contraseñaActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formMouseDragged

    private void btn_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_closeMousePressed

    private void btn_minimizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMousePressed
        // TODO add your handling code here:
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btn_minimizarMousePressed

    private void btn_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseEntered
        // TODO add your handling code here:
        ImageIcon icLogo = new ImageIcon(getClass().getResource("/Imagenes/X_entered.png"));
        //Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_DEFAULT));
        Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        btn_close.setIcon(iconoFac);
        btn_close.repaint();
    }//GEN-LAST:event_btn_closeMouseEntered

    private void btn_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseExited
        // TODO add your handling code here:
        ImageIcon icLogo = new ImageIcon(getClass().getResource("/Imagenes/X.png"));
        //Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_DEFAULT));
        Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        btn_close.setIcon(iconoFac);
        btn_close.repaint();
    }//GEN-LAST:event_btn_closeMouseExited

    private void btn_minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseEntered
        // TODO add your handling code here:
        ImageIcon icLogo = new ImageIcon(getClass().getResource("/Imagenes/-_entered.png"));
        //Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_DEFAULT));
        Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        btn_minimizar.setIcon(iconoFac);
        btn_minimizar.repaint();
    }//GEN-LAST:event_btn_minimizarMouseEntered

    private void btn_minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseExited
        // TODO add your handling code here:
        ImageIcon icLogo = new ImageIcon(getClass().getResource("/Imagenes/-.png"));
        //Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_DEFAULT));
        Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        btn_minimizar.setIcon(iconoFac);
        btn_minimizar.repaint();
    }//GEN-LAST:event_btn_minimizarMouseExited

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
        // TODO add your handling code here:
        //REAL
        try {
            String passwordString = new String(pf_contraseña.getPassword());
            int passwordHash = passwordString.hashCode();
            System.out.println(passwordHash);
            
            if(tf_usuario.getText().isBlank()||tf_usuario.getText().isEmpty())
                throw new Exception("Ingrese un usuario.");
            if(passwordString.isBlank()||passwordString.isEmpty())
                throw new Exception("Ingrese una contraseña.");
            if(!tb_facturacion.isSelected()&&!tb_administracion.isSelected())
                throw new Exception("Seleccione una de las dos opciones");
            
            String tipo_ingreso = "facturacion";
            if(tb_administracion.isSelected()) tipo_ingreso = "administracion";
            
            String sucursal="";
            switch(cb_sucursal.getSelectedItem().toString()){
                case "Sucursal Bello":
                    sucursal = "Bello";
                    break;
                case "Sucursal Robledo":
                    sucursal = "Robledo";
                    break;
            }
            
            boolean pass = Conexion_Firestore.LogIn(tipo_ingreso, tf_usuario.getText(), passwordHash, sucursal);
            
            if(pass&&tipo_ingreso.equals("facturacion")){
                Facturacion f = new Facturacion();
                f.setVisible(true);
                this.dispose();
            }
            else if(pass&&tipo_ingreso.equals("administracion")){
                Principal p = new Principal();
                p.setVisible(true);
                this.dispose();
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al ingresar: \n"+e.getMessage());
        }
            
    }//GEN-LAST:event_btn_entrarActionPerformed

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jLabel3.setForeground(Colores.color_foreground_options);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jLabel3.setForeground(Colores.color_foreground_gray);
    }//GEN-LAST:event_jLabel3MouseExited

    private void tb_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_facturacionActionPerformed
        // TODO add your handling code here:
//        Facturacion f = new Facturacion();
//        f.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_tb_facturacionActionPerformed

    private void SetImageLabel(){
        ImageIcon icLogo = new ImageIcon(getClass().getResource("/Imagenes/Logo Total.png"));
        //Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(Logo.getWidth(), Logo.getHeight(), Image.SCALE_DEFAULT));
        Icon iconoFac = new ImageIcon(icLogo.getImage().getScaledInstance(355, 126, Image.SCALE_DEFAULT));
        Logo.setIcon(iconoFac);
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel btn_close;
    private javax.swing.JButton btn_entrar;
    private javax.swing.JLabel btn_minimizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pf_contraseña;
    private javax.swing.JToggleButton tb_administracion;
    private javax.swing.JToggleButton tb_facturacion;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables
}
