/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.Administracion;

import Modelos.Distribuidor;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author elbot
 */
public class Subseccion extends javax.swing.JFrame {

    
    CardLayout cards;
    Principal p;
    int index,opcion;
    String seccion;
    
    public Subseccion(Principal p, int opcion /*0: EDITAR - 1:INSERTAR*/, int index) {
        initComponents();
        this.p = p;
        cards = (CardLayout)container.getLayout();
        this.index = index;
        this.opcion = opcion;
        try {
            Definir_seccion();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Subseccion:\n"+ex.getMessage());
        }
    }
    
    private void Definir_seccion() throws Exception{
        try {
            if(p.I){
                cards.show(container, "inventario");
                seccion = "inventario";
                Cargar_inventario();
            } 
            else if(p.E) {
                cards.show(container, "empleados");
                seccion = "empleados";
            }
            else {
                cards.show(container, "distribuidores");
                seccion = "distribuidores";
            }
        } 
        catch (Exception e) {
            throw new Exception("Definir_seccion: "+e.getMessage());
        }
    }
    
    private void Cargar_inventario() throws Exception{
        try {
            String nombre="",distribuidor="";
            int stock=0,pu=0;
            if(opcion==0){
                nombre = (String)p.tm_productos.getValueAt(index, 0);
                stock = (int)p.tm_productos.getValueAt(index, 1);
                pu = (int)p.tm_productos.getValueAt(index, 2);
                distribuidor = (String)p.tm_productos.getValueAt(index, 3);
            }
            
            //Cargar distribuidores
            if(p.distribuidores.isEmpty()) p.Cargar_distribuidores();
            for (Distribuidor d: p.distribuidores){
                cb_distribuidor.addItem(d.nombre);
            }
            
            tf_nombre.setText(nombre);
            System.out.println(stock);
            cb_cantidad.setSelectedIndex(stock);
            tf_precio.setText(String.valueOf(pu));
            cb_distribuidor.setSelectedItem(distribuidor);
        } 
        catch (Exception e) {
            throw new Exception("Cargar_inventario:"+e.getMessage());
        }
    }
    
    private void Editar_registro() throws Exception{
        try {
            if(seccion.equals("inventario")){
                String nombre,distribuidor;
                int stock,pu;
                
                nombre = tf_nombre.getText();
                stock = Integer.parseInt(cb_cantidad.getSelectedItem().toString());
                pu = Integer.parseInt(tf_precio.getText());
                distribuidor = (String)cb_distribuidor.getSelectedItem();
                p.tm_productos.setValueAt(nombre, index, 0);
                p.tm_productos.setValueAt(stock, index, 1);
                p.tm_productos.setValueAt(pu, index, 2);
                p.tm_productos.setValueAt(distribuidor, index, 3);
                
                p.Actualizar_inventario(index);
                JOptionPane.showMessageDialog(this, "Operación exitosa.");
                Cerrar_ventana();
            }
            else if(seccion.equals("empleados")){
                
            }
            else{
                
            }
        } 
        catch (Exception e) {
            throw new Exception("Editar_registro:"+e.getMessage());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        tf_precio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_cantidad = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cb_distribuidor = new javax.swing.JComboBox<>();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tf_cedulaE = new javax.swing.JTextField();
        tf_nombreE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_apellidoE = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_salarioE = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_celularE = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_correoE = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_cargoE = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jd_nacimientoE = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        tf_afiliacionE = new javax.swing.JTextField();
        btn_guardar1 = new javax.swing.JButton();
        btn_cancelar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tf_nombreD = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tf_direccionD = new javax.swing.JTextField();
        tf_numeroD = new javax.swing.JTextField();
        btn_guardar2 = new javax.swing.JButton();
        btn_cancelar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        container.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));
        jPanel3.setPreferredSize(new java.awt.Dimension(502, 540));

        jScrollPane1.setBackground(new java.awt.Color(244, 244, 244));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.setPreferredSize(new java.awt.Dimension(429, 485));

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Nombre");

        tf_nombre.setBackground(new java.awt.Color(217, 217, 217));
        tf_nombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_nombre.setForeground(new java.awt.Color(51, 51, 51));
        tf_nombre.setBorder(null);
        tf_nombre.setName("Usuario"); // NOI18N
        tf_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nombreActionPerformed(evt);
            }
        });

        tf_precio.setBackground(new java.awt.Color(217, 217, 217));
        tf_precio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_precio.setForeground(new java.awt.Color(51, 51, 51));
        tf_precio.setBorder(null);
        tf_precio.setName("Usuario"); // NOI18N
        tf_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_precioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Precio unitario");

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Stock");

        cb_cantidad.setBackground(new java.awt.Color(217, 217, 217));
        cb_cantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_cantidad.setForeground(new java.awt.Color(51, 51, 51));
        cb_cantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Distribuidor");

        cb_distribuidor.setBackground(new java.awt.Color(217, 217, 217));
        cb_distribuidor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cb_distribuidor.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cb_distribuidor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_nombre)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_precio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(190, 190, 190))))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_distribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        btn_guardar.setBackground(new java.awt.Color(203, 136, 36));
        btn_guardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(244, 244, 244));
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_cancelar.setBackground(new java.awt.Color(203, 49, 42));
        btn_cancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(244, 244, 244));
        btn_cancelar.setText("Eliminar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1)))
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        container.add(jPanel3, "inventario");

        jPanel5.setBackground(new java.awt.Color(244, 244, 244));

        jScrollPane2.setBackground(new java.awt.Color(244, 244, 244));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(244, 244, 244));
        jPanel6.setPreferredSize(new java.awt.Dimension(410, 847));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Cedula");

        tf_cedulaE.setBackground(new java.awt.Color(217, 217, 217));
        tf_cedulaE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_cedulaE.setForeground(new java.awt.Color(51, 51, 51));
        tf_cedulaE.setBorder(null);
        tf_cedulaE.setName("Usuario"); // NOI18N
        tf_cedulaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cedulaEActionPerformed(evt);
            }
        });

        tf_nombreE.setBackground(new java.awt.Color(217, 217, 217));
        tf_nombreE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_nombreE.setForeground(new java.awt.Color(51, 51, 51));
        tf_nombreE.setBorder(null);
        tf_nombreE.setName("Usuario"); // NOI18N
        tf_nombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nombreEActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Nombre");

        tf_apellidoE.setBackground(new java.awt.Color(217, 217, 217));
        tf_apellidoE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_apellidoE.setForeground(new java.awt.Color(51, 51, 51));
        tf_apellidoE.setBorder(null);
        tf_apellidoE.setName("Usuario"); // NOI18N
        tf_apellidoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_apellidoEActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Apellidos");

        tf_salarioE.setBackground(new java.awt.Color(217, 217, 217));
        tf_salarioE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_salarioE.setForeground(new java.awt.Color(51, 51, 51));
        tf_salarioE.setBorder(null);
        tf_salarioE.setName("Usuario"); // NOI18N
        tf_salarioE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_salarioEActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Salario");

        tf_celularE.setBackground(new java.awt.Color(217, 217, 217));
        tf_celularE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_celularE.setForeground(new java.awt.Color(51, 51, 51));
        tf_celularE.setBorder(null);
        tf_celularE.setName("Usuario"); // NOI18N
        tf_celularE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_celularEActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Celular");

        tf_correoE.setBackground(new java.awt.Color(217, 217, 217));
        tf_correoE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_correoE.setForeground(new java.awt.Color(51, 51, 51));
        tf_correoE.setBorder(null);
        tf_correoE.setName("Usuario"); // NOI18N
        tf_correoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_correoEActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Correo");

        tf_cargoE.setBackground(new java.awt.Color(217, 217, 217));
        tf_cargoE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_cargoE.setForeground(new java.awt.Color(51, 51, 51));
        tf_cargoE.setBorder(null);
        tf_cargoE.setName("Usuario"); // NOI18N
        tf_cargoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cargoEActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Cargo");

        jLabel14.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Fecha nacimiento");

        jd_nacimientoE.setBackground(new java.awt.Color(217, 217, 217));
        jd_nacimientoE.setForeground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Fecha Afiliacion");

        tf_afiliacionE.setBackground(new java.awt.Color(217, 217, 217));
        tf_afiliacionE.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_afiliacionE.setForeground(new java.awt.Color(51, 51, 51));
        tf_afiliacionE.setBorder(null);
        tf_afiliacionE.setName("Usuario"); // NOI18N
        tf_afiliacionE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_afiliacionEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_cedulaE))
                        .addGap(170, 170, 170))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tf_correoE, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addGap(131, 131, 131))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tf_salarioE, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addGap(190, 190, 190))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_apellidoE, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_nombreE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tf_celularE)
                                .addGap(131, 131, 131))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jd_nacimientoE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_cargoE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                                .addGap(131, 131, 131))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tf_afiliacionE)
                                .addGap(131, 131, 131)))
                        .addGap(41, 41, 41))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_cedulaE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_apellidoE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_salarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_celularE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_correoE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_cargoE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jd_nacimientoE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_afiliacionE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel6);

        btn_guardar1.setBackground(new java.awt.Color(203, 136, 36));
        btn_guardar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_guardar1.setForeground(new java.awt.Color(244, 244, 244));
        btn_guardar1.setText("Guardar");
        btn_guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar1ActionPerformed(evt);
            }
        });

        btn_cancelar1.setBackground(new java.awt.Color(203, 49, 42));
        btn_cancelar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_cancelar1.setForeground(new java.awt.Color(244, 244, 244));
        btn_cancelar1.setText("Eliminar");
        btn_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        container.add(jPanel5, "empleados");

        jPanel4.setBackground(new java.awt.Color(244, 244, 244));

        jScrollPane3.setBackground(new java.awt.Color(244, 244, 244));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(244, 244, 244));
        jPanel2.setPreferredSize(new java.awt.Dimension(425, 485));

        jLabel16.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Nombre");

        tf_nombreD.setBackground(new java.awt.Color(217, 217, 217));
        tf_nombreD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_nombreD.setForeground(new java.awt.Color(51, 51, 51));
        tf_nombreD.setBorder(null);
        tf_nombreD.setName("Usuario"); // NOI18N
        tf_nombreD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nombreDActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Numero");

        jLabel19.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Dirección");

        tf_direccionD.setBackground(new java.awt.Color(217, 217, 217));
        tf_direccionD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_direccionD.setForeground(new java.awt.Color(51, 51, 51));
        tf_direccionD.setBorder(null);
        tf_direccionD.setName("Usuario"); // NOI18N
        tf_direccionD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_direccionDActionPerformed(evt);
            }
        });

        tf_numeroD.setBackground(new java.awt.Color(217, 217, 217));
        tf_numeroD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tf_numeroD.setForeground(new java.awt.Color(51, 51, 51));
        tf_numeroD.setBorder(null);
        tf_numeroD.setName("Usuario"); // NOI18N
        tf_numeroD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_numeroDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_direccionD, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tf_numeroD)
                                .addGap(131, 131, 131))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_nombreD))
                        .addGap(41, 41, 41))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_nombreD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_numeroD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_direccionD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel2);

        btn_guardar2.setBackground(new java.awt.Color(203, 136, 36));
        btn_guardar2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_guardar2.setForeground(new java.awt.Color(244, 244, 244));
        btn_guardar2.setText("Guardar");
        btn_guardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar2ActionPerformed(evt);
            }
        });

        btn_cancelar2.setBackground(new java.awt.Color(203, 49, 42));
        btn_cancelar2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_cancelar2.setForeground(new java.awt.Color(244, 244, 244));
        btn_cancelar2.setText("Eliminar");
        btn_cancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        container.add(jPanel4, "distribuidores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Cerrar_ventana(){
        p.setEnabled(true);
        this.dispose();
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        p.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void tf_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nombreActionPerformed

    private void tf_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_precioActionPerformed

    private void tf_cedulaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cedulaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cedulaEActionPerformed

    private void tf_nombreEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nombreEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nombreEActionPerformed

    private void tf_apellidoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_apellidoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_apellidoEActionPerformed

    private void tf_salarioEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_salarioEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_salarioEActionPerformed

    private void tf_celularEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_celularEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_celularEActionPerformed

    private void tf_correoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_correoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_correoEActionPerformed

    private void tf_cargoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cargoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cargoEActionPerformed

    private void tf_afiliacionEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_afiliacionEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_afiliacionEActionPerformed

    private void tf_nombreDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nombreDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nombreDActionPerformed

    private void tf_direccionDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_direccionDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_direccionDActionPerformed

    private void tf_numeroDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_numeroDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_numeroDActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        try {
            Editar_registro();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar:"+e.getMessage());
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardar1ActionPerformed

    private void btn_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelar1ActionPerformed

    private void btn_guardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardar2ActionPerformed

    private void btn_cancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cancelar1;
    private javax.swing.JButton btn_cancelar2;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_guardar1;
    private javax.swing.JButton btn_guardar2;
    private javax.swing.JComboBox<String> cb_cantidad;
    private javax.swing.JComboBox<String> cb_distribuidor;
    private javax.swing.JPanel container;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jd_nacimientoE;
    private javax.swing.JTextField tf_afiliacionE;
    private javax.swing.JTextField tf_apellidoE;
    private javax.swing.JTextField tf_cargoE;
    private javax.swing.JTextField tf_cedulaE;
    private javax.swing.JTextField tf_celularE;
    private javax.swing.JTextField tf_correoE;
    private javax.swing.JTextField tf_direccionD;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JTextField tf_nombreD;
    private javax.swing.JTextField tf_nombreE;
    private javax.swing.JTextField tf_numeroD;
    private javax.swing.JTextField tf_precio;
    private javax.swing.JTextField tf_salarioE;
    // End of variables declaration//GEN-END:variables
}
