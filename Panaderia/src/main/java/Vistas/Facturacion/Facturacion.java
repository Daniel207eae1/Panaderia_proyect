/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.Facturacion;

import Contextos.Conexion_Firestore;
import Contextos.Sesion;
import Modelos.Producto;
import Modelos.Venta;
import Modificados.panel_degrade1;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Desktop;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhoni
 */


public class Facturacion extends javax.swing.JFrame {
    
    DefaultTableModel tm_facturacion;
    DefaultTableModel tm_inventario;
    panel_degrade1 p1 = new panel_degrade1();
    
    List<Producto> productos_venta = new ArrayList<>();
    List<Producto> productos_ventas_pan = new ArrayList<>();
    List<Producto> productos_ventas_malteadas = new ArrayList<>();
    List<Producto> productos_ventas_otros = new ArrayList<>();
    List<Producto> productos = new ArrayList<>();
    List<Producto> productos_pan = new ArrayList<>();
    List<Producto> productos_malteadas = new ArrayList<>();
    List<Producto> productos_otros = new ArrayList<>();
    
    LocalDate fechaActual = LocalDate.now();
    DateTimeFormatter formatoPersonalizado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    Venta venta;
    
    private Map<String, String> sucursales;
 
    public Facturacion() {
        try {
            //
            UIManager.setLookAndFeel( new FlatMacLightLaf());
            initComponents();
            p1.setSize(jPanel2.getSize());
            jPanel2.add(p1);
            cargar_default_table_model();
            Cargar_sucursales();
            cargar_productos_venta();
            actualizar_combobox();
            actualizar_cantidades();
            venta = new Venta(fechaActual.format(formatoPersonalizado),Sesion.cedula);
            jTabbedPane1.setTabComponentAt(0, null);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error en Principal: "+e.getMessage());
        }
    }
    
    private void Agregar_producto() throws Exception{
        try {
            String nombre = ComboProduct.getSelectedItem().toString();
            String tipo = ComboTipoProduct.getSelectedItem().toString();
            int cantidad = Integer.valueOf(ComboCant.getSelectedItem().toString());
            if(cantidad==0){
                return;
            }
            int pu=0,total;
            boolean estaba=false;
            
            for (Producto p: productos_venta){
                if(p.nombre.equals(nombre)){
                    pu = p.precio_u;
                    break;
                }
            }
            //Verificar si está
            for (int i = 0; i < tm_facturacion.getRowCount(); i++) {
                if(tm_facturacion.getValueAt(i, 0).toString().equals(nombre)){
                    tm_facturacion.removeRow(i);
                    estaba=true;
                }
            }
            
            //Si está, entonces hacer esto
            if(estaba){
                venta.total-=venta.productos.get(nombre)[1];
                venta.cantidad_productos-=1;
                venta.productos.remove(nombre);
            }
            total = pu*cantidad;
            venta.total+=total;
            venta.productos.put(nombre, new int[]{cantidad,total});
            venta.cantidad_productos+=1;
            
            jl_total.setText(String.valueOf(venta.total)+"$");
            tm_facturacion.addRow(new Object[]{nombre,tipo,cantidad,pu,total});
        } 
        catch (Exception e) {
            throw new Exception("Agregar_producto: \n"+e.getMessage());
        }
    }
    
    public void actualizar_cantidades() throws Exception{
        try {
            String producto;
            if(ComboProduct.getSelectedItem()==null){
                producto = ComboProduct.getItemAt(0);
            }
            else{
                 producto = ComboProduct.getSelectedItem().toString();
            }
            ComboCant.removeAllItems();
            for (Producto p: productos_venta){
                if(p.nombre.equals(producto)){
                    for (int i = 1; i <= p.stock; i++) {
                        ComboCant.addItem(String.valueOf(i));
                    }
                    break;
                }
            }
        } 
        catch (Exception e) {
            throw new Exception("Actualizar_cantidades: \n"+e.getMessage());
        }
    }
    
    private void actualizar_combobox() throws Exception{
        try {
            String tipo;
            if(ComboTipoProduct.getSelectedItem()==null){
                tipo = ComboTipoProduct.getItemAt(0);
            }
            else{
                tipo = ComboTipoProduct.getSelectedItem().toString();
            }
            ComboProduct.removeAllItems();
            if(tipo.equals("Pan")){
                for (Producto p : productos_ventas_pan){
                    ComboProduct.addItem(p.nombre);
                }
            }
            else if(tipo.equals("Malteadas")){
                for (Producto p : productos_ventas_malteadas){
                    ComboProduct.addItem(p.nombre);
                }
            }
            else{
                for (Producto p : productos_ventas_otros){
                    ComboProduct.addItem(p.nombre);
                }
            }
        } 
        catch (Exception e) {
            throw new Exception("Actualizar_combobox: \n"+e.getMessage());
        }
    }
    
    public void cargar_productos_venta() throws Exception{
        try {
            tm_inventario.setRowCount(0);
            productos_ventas_pan.clear();
            productos_ventas_malteadas.clear();
            productos_ventas_otros.clear();
            
            productos_venta = Conexion_Firestore.ver_inventario(Sesion.sucursal);
            for(Producto p : productos_venta){
                if(p.tipo.equals("Pan")){
                    productos_ventas_pan.add(p);
                }
                else if(p.tipo.equals("Malteadas")){
                    productos_ventas_malteadas.add(p);
                }
                else{
                    productos_ventas_otros.add(p);
                }
            }
        } 
        catch (Exception e) {
            throw new Exception("Cargar_productos venta: \n"+e.getMessage());
        }
    }
    
    private void Limpiar_campos() throws Exception{
        try {
            cargar_productos_venta();
            actualizar_combobox();
            actualizar_cantidades();
            jl_total.setText("0$");
            tm_facturacion.setRowCount(0);
        } 
        catch (Exception e) {
            throw new Exception("Limpiar_campos: "+e.getMessage());
        }
    }

    private void cargar_default_table_model() throws Exception {
        try {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            String[] c1 = {"Producto","Tipo de producto","Cantidad","Precio","Subtotal"};
            tm_facturacion = new DefaultTableModel(c1, 0);
            tble_factura.setModel(tm_facturacion);
            tble_factura.setDefaultRenderer(Object.class, centerRenderer);

            String[] c2 = {"Producto", "Stock", "Precio unitario","Categoria","Sucursal"};
            tm_inventario = new DefaultTableModel(c2, 0);
            tble_inventario.setModel(tm_inventario);
            tble_inventario.setDefaultRenderer(Object.class, centerRenderer);
        } 
        catch (Exception e) {
            throw new Exception("cargar_default_table_model: \n"+e.getMessage());
        }
    }
    
    private void Cargar_sucursales(){
        try {
            sucursales = Conexion_Firestore.Buscar_sucursales();
        } 
        catch (Exception e) {
            
        }
    }
    
    private void Cargar_inventario() throws Exception{
        try {
            tm_inventario.setRowCount(0);
            String sucursal = sucursales.get(cb_sucursal.getSelectedItem().toString());
            String categoria = cb_categoria.getSelectedItem().toString();
            productos_pan.clear();
            productos_malteadas.clear();
            productos_otros.clear();
            
            productos = Conexion_Firestore.ver_inventario(sucursal);
            for(Producto p : productos){
                if(p.tipo.equals("Pan")){
                    productos_pan.add(p);
                }
                else if(p.tipo.equals("Malteadas")){
                    productos_malteadas.add(p);
                }
                else{
                    productos_otros.add(p);
                }
            }
            if(categoria.equals("Pan")){
                for(Producto p : productos_pan){
                    tm_inventario.addRow(new Object[]{p.nombre,p.stock,p.precio_u,p.tipo,cb_sucursal.getSelectedItem().toString()});
                }
            }
            else if(categoria.equals("Malteadas")){
                for(Producto p : productos_malteadas){
                    tm_inventario.addRow(new Object[]{p.nombre,p.stock,p.precio_u,p.tipo,cb_sucursal.getSelectedItem().toString()});
                }
            }
            else{
                for(Producto p : productos_otros){
                    tm_inventario.addRow(new Object[]{p.nombre,p.stock,p.precio_u,p.tipo,cb_sucursal.getSelectedItem().toString()});
                }
            }
        } 
        catch (Exception e) {
            throw new Exception("Cargar_inventario: \n"+e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        tb_inventario = new javax.swing.JToggleButton();
        tb_facturacion = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        ComboTipoProduct = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        ComboProduct = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboCant = new javax.swing.JComboBox<>();
        btn_crear_factura = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tble_factura = new javax.swing.JTable();
        btn_agregar_producto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jl_total = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tble_inventario = new javax.swing.JTable();
        cb_categoria = new javax.swing.JComboBox<>();
        cb_sucursal = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        ItemHerramientas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentResized(evt);
            }
        });

        tb_inventario.setBackground(new java.awt.Color(217, 217, 217));
        buttonGroup1.add(tb_inventario);
        tb_inventario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tb_inventario.setForeground(new java.awt.Color(198, 87, 45));
        tb_inventario.setText("Inventario");
        tb_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_inventarioActionPerformed(evt);
            }
        });

        tb_facturacion.setBackground(new java.awt.Color(217, 217, 217));
        buttonGroup1.add(tb_facturacion);
        tb_facturacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tb_facturacion.setForeground(new java.awt.Color(198, 87, 45));
        tb_facturacion.setSelected(true);
        tb_facturacion.setText("Facturación");
        tb_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_facturacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(tb_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tb_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(244, 244, 244));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));

        ComboTipoProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboTipoProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pan", "Malteadas", "Otros" }));
        ComboTipoProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipoProductActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Seleccione el tipo de producto");

        ComboProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboProductActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Seleccione el producto");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Cantidad");

        ComboCant.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboCant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        btn_crear_factura.setBackground(new java.awt.Color(203, 136, 36));
        btn_crear_factura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_crear_factura.setForeground(new java.awt.Color(255, 255, 255));
        btn_crear_factura.setText("Crear factura");
        btn_crear_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crear_facturaActionPerformed(evt);
            }
        });

        tble_factura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tble_factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Tipo de producto", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tble_factura.setShowGrid(true);
        jScrollPane1.setViewportView(tble_factura);

        btn_agregar_producto.setBackground(new java.awt.Color(217, 217, 217));
        btn_agregar_producto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_agregar_producto.setForeground(new java.awt.Color(0, 0, 0));
        btn_agregar_producto.setText("Agregar producto");
        btn_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_productoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("TOTAL:");

        jl_total.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jl_total.setForeground(new java.awt.Color(51, 51, 51));
        jl_total.setText("0$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_agregar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btn_crear_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboTipoProduct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(ComboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(ComboCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jl_total)))
                        .addGap(0, 273, Short.MAX_VALUE)))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboTipoProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboCant, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_crear_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jl_total))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );

        jTabbedPane1.addTab("", jPanel1);

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Categoria");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Sucursal");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/searchicon.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        tble_inventario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tble_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Stock", "Precio unitario", "Categoria", "Sucursal"
            }
        ));
        tble_inventario.setShowGrid(true);
        jScrollPane2.setViewportView(tble_inventario);

        cb_categoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pan", "Malteadas", "Otros" }));

        cb_sucursal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_sucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bello", "Robledo" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cb_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))))
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(71, 71, 71)
                .addComponent(jScrollPane2)
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("", jPanel3);

        ItemHerramientas.setText("Preferencias");

        jMenuItem1.setText("Ayuda ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        ItemHerramientas.add(jMenuItem1);

        jMenuItem2.setText("Usuario");
        ItemHerramientas.add(jMenuItem2);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        ItemHerramientas.add(jMenuItem3);

        jMenuBar1.add(ItemHerramientas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentResized
        // TODO add your handling code here:
        jPanel2.remove(p1);
        p1.setSize(jPanel2.getSize());
        jPanel2.add(p1);
        jPanel2.repaint();
    }//GEN-LAST:event_jPanel2ComponentResized

    private void agg_producto(){
        
        try{
            
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: "+e.getMessage());
        }
    }
    
    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        try {
            // TODO add your handling code here:
            Agregar_producto();
        } catch (Exception ex) {
            Logger.getLogger(Facturacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_agregar_productoActionPerformed

    private void tb_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_inventarioActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_tb_inventarioActionPerformed

    private void tb_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_facturacionActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_tb_facturacionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        String url = "https://www.tortasytortas.com.co/panaderia";
        
        try{
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: "+e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //Salir
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btn_crear_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crear_facturaActionPerformed
        // TODO add your handling code here:
        //CONTROLES
        //CLIENTE NOMBRE
        
        
        try {
            FacturacionNameClient fnc = new FacturacionNameClient(venta, this);
            fnc.setLocationRelativeTo(this);
            fnc.setVisible(true);
            Limpiar_campos();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error creando la factura: \n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_crear_facturaActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        try {
            // TODO add your handling code here:
            Cargar_inventario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el inventario: "+ex.getMessage());
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void ComboTipoProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoProductActionPerformed
        try {
            // TODO add your handling code here:
            actualizar_combobox();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar los productos: \n"+ex.getMessage());
        }
    }//GEN-LAST:event_ComboTipoProductActionPerformed

    private void ComboProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProductActionPerformed
        try {
            // TODO add your handling code here:
            actualizar_cantidades();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar las cantidades: \n"+ex.getMessage());
        }
    }//GEN-LAST:event_ComboProductActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCant;
    private javax.swing.JComboBox<String> ComboProduct;
    private javax.swing.JComboBox<String> ComboTipoProduct;
    private javax.swing.JMenu ItemHerramientas;
    private javax.swing.JButton btn_agregar_producto;
    private javax.swing.JButton btn_crear_factura;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_categoria;
    private javax.swing.JComboBox<String> cb_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jl_total;
    private javax.swing.JToggleButton tb_facturacion;
    private javax.swing.JToggleButton tb_inventario;
    private javax.swing.JTable tble_factura;
    private javax.swing.JTable tble_inventario;
    // End of variables declaration//GEN-END:variables
}
