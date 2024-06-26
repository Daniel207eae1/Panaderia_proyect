/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.Administracion;

import Contextos.Conexion_Firestore;
import Contextos.Sesion;
import Modelos.Distribuidor;
import Modelos.Empleado;
import Modelos.Producto;
import Modificados.Colores;
import Modificados.panel_degrade1;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
public class Principal extends javax.swing.JFrame {

    
    panel_degrade1 p1 = new panel_degrade1();
    boolean pan_click = false;
    boolean malteadas_click = false;
    boolean otros_click = false;
    boolean empleados_cargados = false;
    boolean productos_cargados = false;
    boolean distribuidores_cargados = false;
    boolean ventas_cargadas = false;
    
    boolean I=false,E=false,V=false,D=false;
    
    List<Producto> productos = new ArrayList<>();
    List<Producto> productos_pan = new ArrayList<>();
    List<Producto> productos_malteadas = new ArrayList<>();
    List<Producto> productos_otros = new ArrayList<>();
    List<Empleado> empleados = new ArrayList<>();
    List<Distribuidor> distribuidores = new ArrayList<>();
    
    
    DefaultTableModel tm_productos;
    DefaultTableModel tm_empleados;
    DefaultTableModel tm_ventas;
    DefaultTableModel tm_distribuidores;
    
    public Principal() {
        try {
            UIManager.setLookAndFeel( new FlatMacLightLaf());
            initComponents();
            p1.setSize(jPanel1.getSize());
            jPanel1.add(p1);
            jTabbedPane2.setTabComponentAt(0, null);
            cargar_default_model();
            Cargar_inventario();
            Cargar_Sub_Inventario("Pan");
            productos_cargados = true;
            jl_panesMouseClicked(null);
            
            //Tablas
            tabla_ventas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) { // Doble clic
                        int filaSeleccionada = tabla_ventas.getSelectedRow();
                        // Llamar al método cuando se produce un doble clic
                        ver_venta_detallada(filaSeleccionada);
                    }
                }
            });
            I=true;
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error en Principal: "+e.getMessage());
        }
    }
    
    private void Insertar_empleado(){
        try {
            String id = Conexion_Firestore.Insertar_empleado();
            empleados.add(new Empleado(id,"","","",0,"","","","",""));
            tm_empleados.addRow(new Object[]{"", "", "","", "", "","", "", ""});
            
            Editar_campo(1,tm_empleados.getRowCount()-1);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar productos: \n"+e.getMessage());
        }
    }
    
    private void Insertar_producto(){
        try {
            String id = Conexion_Firestore.Insertar_producto();
            if(pan_click)
                productos_pan.add(new Producto(id,"",0,0,"","Pan"));
            if(malteadas_click)
                productos_malteadas.add(new Producto(id,"",0,0,"","Malteadas"));
            if(otros_click)
                productos_otros.add(new Producto(id,"",0,0,"","Otros"));
            
            tm_productos.addRow(new Object[]{"", "", "",""});
            
            Editar_campo(1,tm_productos.getRowCount()-1);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar productos: \n"+e.getMessage());
        }
    }
    
    private void Insertar_distribuidor(){
        try {
            String id = Conexion_Firestore.Insertar_distribuidor();
            distribuidores.add(new Distribuidor(id,"","",""));
            tm_distribuidores.addRow(new Object[]{"", "", ""});
            
            Editar_campo(1,tm_distribuidores.getRowCount()-1);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar productos: \n"+e.getMessage());
        }
    }
    
    private void ver_venta_detallada(int i){
        try {
            Venta_detallada vd = new Venta_detallada(i);
            vd.setLocationRelativeTo(this);
            vd.setVisible(true);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al detallar la venta: \n"+e.getMessage());
        }
    }
    
    protected void Actualizar_inventario(int i){
        try {
            if(pan_click){
                productos_pan.get(i).nombre = (String)tm_productos.getValueAt(i, 0);
                productos_pan.get(i).stock = Integer.valueOf(tm_productos.getValueAt(i, 1).toString());
                productos_pan.get(i).precio_u = Integer.valueOf(tm_productos.getValueAt(i, 2).toString());
                productos_pan.get(i).distribuidor = (String)tm_productos.getValueAt(i, 3);
                Conexion_Firestore.Actualizar_inventario(productos_pan.get(i));
            }
            else if(malteadas_click){
                productos_malteadas.get(i).nombre = (String)tm_productos.getValueAt(i, 0);
                productos_malteadas.get(i).stock = Integer.valueOf(tm_productos.getValueAt(i, 1).toString());
                productos_malteadas.get(i).precio_u = Integer.valueOf(tm_productos.getValueAt(i, 2).toString());
                productos_malteadas.get(i).distribuidor = (String)tm_productos.getValueAt(i, 3);
                Conexion_Firestore.Actualizar_inventario(productos_malteadas.get(i));
            }
            else{
                productos_otros.get(i).nombre = (String)tm_productos.getValueAt(i, 0);
                productos_otros.get(i).stock = Integer.valueOf(tm_productos.getValueAt(i, 1).toString());
                productos_otros.get(i).precio_u = Integer.valueOf(tm_productos.getValueAt(i, 2).toString());
                productos_otros.get(i).distribuidor = (String)tm_productos.getValueAt(i, 3);
                Conexion_Firestore.Actualizar_inventario(productos_otros.get(i));
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al actualizar el inventario: \n"+e.getMessage());
        }
    }
    
    protected void Actualizar_distribuidores(int i){
        try {
            distribuidores.get(i).nombre = (String)tm_distribuidores.getValueAt(i, 0);
            distribuidores.get(i).numero = (String)tm_distribuidores.getValueAt(i, 1);
            distribuidores.get(i).direccion = (String)tm_distribuidores.getValueAt(i, 2);
            Conexion_Firestore.Actualizar_distribuidor(distribuidores.get(i));
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al actualizar los distribuidores: \n"+e.getMessage());
        }
    }
    
    protected void Actualizar_empleados(int i){
        try {
            empleados.get(i).cedula = tm_empleados.getValueAt(i, 0).toString();
            empleados.get(i).nombre = tm_empleados.getValueAt(i, 1).toString();
            empleados.get(i).apellido = tm_empleados.getValueAt(i, 2).toString();
            empleados.get(i).salario = Integer.valueOf(tm_empleados.getValueAt(i, 3).toString());
            empleados.get(i).celular = tm_empleados.getValueAt(i, 4).toString();
            empleados.get(i).correo = tm_empleados.getValueAt(i, 5).toString();
            empleados.get(i).fecha_nacimiento = tm_empleados.getValueAt(i, 6).toString();
            empleados.get(i).fecha_afiliacion = tm_empleados.getValueAt(i, 7).toString();
            empleados.get(i).cargo = tm_empleados.getValueAt(i, 8).toString();
            Conexion_Firestore.Actualizar_empleado(empleados.get(i));
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al actualizar los empleados: \n"+e.getMessage());
        }
    }
    
    private void cargar_default_model(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        String[] c1 = {"Nombre","Stock","Precio Unitario","Distribuidor"};
        tm_productos = new DefaultTableModel(c1, 0);
        tabla_inventario.setModel(tm_productos);
        tabla_inventario.setDefaultRenderer(Object.class, centerRenderer);
        tabla_inventario.setCellEditor(null);
        
        String[] c2 = {"Cedula","Nombre","Apellidos","Salario","Celular",
        "Correo","Fecha nacimiento","Fecha afiliacion","Cargo"};
        tm_empleados = new DefaultTableModel(c2, 0);
        tabla_empleados.setModel(tm_empleados);
        tabla_empleados.setDefaultRenderer(Object.class, centerRenderer);
        tabla_empleados.setCellEditor(null);
        
        String[] c3 = {"Fecha","Vendedor CC","Cliente","Total"};
        tm_ventas = new DefaultTableModel(c3, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Devolver false para hacer que todas las celdas sean no editables
                return false;
            }
        };
        tabla_ventas.setModel(tm_ventas);
        tabla_ventas.setDefaultRenderer(Object.class, centerRenderer);
        tabla_ventas.setCellEditor(null);
        
        String[] c4 = {"Nombre","Numero","Direccion"};
        tm_distribuidores = new DefaultTableModel(c4, 0);
        tabla_distribuidores.setModel(tm_distribuidores);
        tabla_distribuidores.setDefaultRenderer(Object.class, centerRenderer);
        tabla_distribuidores.setCellEditor(null);
    }
    
    private void Cargar_inventario(){
        try {
            System.out.println("asd");
            productos = Conexion_Firestore.ver_inventario(Sesion.sucursal);
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
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el inventario: \n"+e.getMessage());
        }
    }
    
    private void Cargar_Sub_Inventario(String tipo){
        try {
            tm_productos.setRowCount(0);
            if(tipo.equals("Pan")){
                for(Producto p : productos_pan){
                    tm_productos.addRow(new Object[]{p.nombre,p.stock,p.precio_u,p.distribuidor});
                }
            }
            else if(tipo.equals("Malteadas")){
                for(Producto p : productos_malteadas){
                    tm_productos.addRow(new Object[]{p.nombre,p.stock,p.precio_u,p.distribuidor});
                }
            }
            else{
                for(Producto p : productos_otros){
                    tm_productos.addRow(new Object[]{p.nombre,p.stock,p.precio_u,p.distribuidor});
                }
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el sub-inventario: \n"+e.getMessage());
        }
    }
    
    private void Cargar_empleados(){
        try {
            empleados = Conexion_Firestore.ver_empleados(Sesion.sucursal);
            tm_empleados.setRowCount(0);
            for(Empleado e : empleados){
                tm_empleados.addRow(new Object[]{e.cedula,e.nombre,e.apellido,e.salario,e.celular,e.correo,e.fecha_nacimiento,e.fecha_afiliacion,e.cargo});
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar los empleados: \n"+e.getMessage());
        }
    }
    
    private void Cargar_ventas(){
        try {
            //List<Venta> ventas = new ArrayList<>();
            Conexion_Firestore.ver_ventas(Sesion.sucursal, tm_ventas);
//            tm_ventas.setRowCount(0);
//            for(Venta v : ventas){
//                tm_ventas.addRow(new Object[]{v.fecha,v.vendedor,v.cliente,v.total});
//            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar los empleados: \n"+e.getMessage());
        }
    }
    
    protected void Cargar_distribuidores(){
        try {
            distribuidores = Conexion_Firestore.ver_distribuidores(Sesion.sucursal);
            tm_distribuidores.setRowCount(0);
            for(Distribuidor d : distribuidores){
                tm_distribuidores.addRow(new Object[]{d.nombre,d.numero,d.direccion});
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar los empleados: \n"+e.getMessage());
        }
    }
    
    protected void Eliminar_producto(int index) throws Exception{
        try {
            String tipo,identificador,field;
            
            tipo = "Inventario";
            field = "nombre";
            identificador = tm_productos.getValueAt(index, 0).toString();
            
            Conexion_Firestore.Eliminar_registro(tipo, identificador, field);
            
            tm_productos.removeRow(index);
            tm_productos.fireTableDataChanged();
        } 
        catch (Exception e) {
            throw new Exception("Eliminar_producto "+e.getMessage());
        }
    }
    
    protected void Eliminar_empleado(int index) throws Exception{
        try {
            String tipo,identificador,field;
            
            tipo = "Empleados";
            field = "cedula";
            identificador = tm_empleados.getValueAt(index, 0).toString();
            
            Conexion_Firestore.Eliminar_registro(tipo, identificador, field);
            
            tm_empleados.removeRow(index);
            tm_empleados.fireTableDataChanged();
        } 
        catch (Exception e) {
            throw new Exception("Eliminar_empleados "+e.getMessage());
        }
    }
    
    protected void Eliminar_distribuido(int index) throws Exception{
        try {
            String tipo,identificador,field;
            
            tipo = "Distribuidores";
            field = "nombre";
            identificador = tm_distribuidores.getValueAt(index, 0).toString();
            
            Conexion_Firestore.Eliminar_registro(tipo, identificador, field);
            
            tm_distribuidores.removeRow(index);
            tm_distribuidores.fireTableDataChanged();
        } 
        catch (Exception e) {
            throw new Exception("Eliminar_empleados "+e.getMessage());
        }
    }
    
    private void Editar_campo(int opcion,int index) throws Exception{
        try {
            Subseccion sb = new Subseccion(this, opcion, index);
            this.setEnabled(false);
            sb.setVisible(true);
        } 
        catch (Exception e) {
            throw new Exception("Editar campo: "+e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tb_inventario = new javax.swing.JToggleButton();
        tb_empleados = new javax.swing.JToggleButton();
        tb_ventas = new javax.swing.JToggleButton();
        tb_distribuidores = new javax.swing.JToggleButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_inventario = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jl_panes = new javax.swing.JLabel();
        jl_malteadas = new javax.swing.JLabel();
        jl_otros = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_empleados = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_ventas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_distribuidores = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        ItemHerramientas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 244, 244));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        tb_inventario.setBackground(new java.awt.Color(217, 217, 217));
        buttonGroup1.add(tb_inventario);
        tb_inventario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tb_inventario.setForeground(new java.awt.Color(198, 87, 45));
        tb_inventario.setSelected(true);
        tb_inventario.setText("Inventario");
        tb_inventario.setBorderPainted(false);
        tb_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_inventarioActionPerformed(evt);
            }
        });

        tb_empleados.setBackground(new java.awt.Color(217, 217, 217));
        buttonGroup1.add(tb_empleados);
        tb_empleados.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tb_empleados.setForeground(new java.awt.Color(198, 87, 45));
        tb_empleados.setText("Empleados");
        tb_empleados.setBorderPainted(false);
        tb_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_empleadosActionPerformed(evt);
            }
        });

        tb_ventas.setBackground(new java.awt.Color(217, 217, 217));
        buttonGroup1.add(tb_ventas);
        tb_ventas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tb_ventas.setForeground(new java.awt.Color(198, 87, 45));
        tb_ventas.setText("Ventas");
        tb_ventas.setBorderPainted(false);
        tb_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_ventasActionPerformed(evt);
            }
        });

        tb_distribuidores.setBackground(new java.awt.Color(217, 217, 217));
        buttonGroup1.add(tb_distribuidores);
        tb_distribuidores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tb_distribuidores.setForeground(new java.awt.Color(198, 87, 45));
        tb_distribuidores.setText("Distribuidores");
        tb_distribuidores.setBorderPainted(false);
        tb_distribuidores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_distribuidoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tb_inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tb_empleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tb_ventas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tb_distribuidores, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(tb_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tb_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tb_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tb_distribuidores, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.setBackground(new java.awt.Color(244, 244, 244));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel2.setBackground(new java.awt.Color(244, 244, 244));

        tabla_inventario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Stock", "Precio Unitario", "Distribuidor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_inventario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_inventario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_inventario.setShowGrid(true);
        tabla_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_inventarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_inventario);

        jButton1.setBackground(new java.awt.Color(203, 136, 36));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(244, 244, 244));
        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jl_panes.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jl_panes.setForeground(new java.awt.Color(51, 51, 51));
        jl_panes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_panes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sublinea.png"))); // NOI18N
        jl_panes.setText("Panes");
        jl_panes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jl_panes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl_panes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jl_panes.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jl_panes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_panesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jl_panesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jl_panesMouseExited(evt);
            }
        });

        jl_malteadas.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jl_malteadas.setForeground(new java.awt.Color(51, 51, 51));
        jl_malteadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_malteadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sublinea.png"))); // NOI18N
        jl_malteadas.setText("Malteadas");
        jl_malteadas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jl_malteadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl_malteadas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jl_malteadas.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jl_malteadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_malteadasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jl_malteadasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jl_malteadasMouseExited(evt);
            }
        });

        jl_otros.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jl_otros.setForeground(new java.awt.Color(51, 51, 51));
        jl_otros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_otros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sublinea.png"))); // NOI18N
        jl_otros.setText("Otros");
        jl_otros.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jl_otros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl_otros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jl_otros.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jl_otros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_otrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jl_otrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jl_otrosMouseExited(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(198, 87, 45));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(244, 244, 244));
        jButton4.setText("Editar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jl_panes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jl_malteadas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jl_otros, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_panes)
                    .addComponent(jl_malteadas)
                    .addComponent(jl_otros))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jTabbedPane2.addTab("", jPanel2);

        jPanel3.setBackground(new java.awt.Color(244, 244, 244));

        tabla_empleados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Apellidos", "Salario", "Celular", "Correo", "Fecha nacimiento", "Fecha Afiliacion", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_empleados.setRowSelectionAllowed(true);
        tabla_empleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_empleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_empleados.setShowGrid(true);
        jScrollPane2.setViewportView(tabla_empleados);

        jButton2.setBackground(new java.awt.Color(203, 136, 36));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(244, 244, 244));
        jButton2.setText("Insertar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(198, 87, 45));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(244, 244, 244));
        jButton5.setText("Editar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)))
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane2.addTab("", jPanel3);

        jPanel4.setBackground(new java.awt.Color(244, 244, 244));

        tabla_ventas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Vendedor", "Cliente", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_ventas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_ventas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_ventas.setShowGrid(true);
        jScrollPane3.setViewportView(tabla_ventas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );

        jTabbedPane2.addTab("", jPanel4);

        jPanel5.setBackground(new java.awt.Color(244, 244, 244));

        tabla_distribuidores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla_distribuidores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Numero", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_distribuidores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_distribuidores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_distribuidores.setShowGrid(true);
        jScrollPane4.setViewportView(tabla_distribuidores);

        jButton3.setBackground(new java.awt.Color(203, 136, 36));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(244, 244, 244));
        jButton3.setText("Insertar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(198, 87, 45));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(244, 244, 244));
        jButton6.setText("Editar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jTabbedPane2.addTab("", jPanel5);

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
        ItemHerramientas.add(jMenuItem3);

        jMenuBar1.add(ItemHerramientas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane2)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tb_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_ventasActionPerformed
        jTabbedPane2.setSelectedIndex(2);
        if(!ventas_cargadas){
            Cargar_ventas();
            ventas_cargadas = true;
        }
        I=false;
        E=false;
        V=true;
        D=false;
    }//GEN-LAST:event_tb_ventasActionPerformed

    private void tb_distribuidoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_distribuidoresActionPerformed
        jTabbedPane2.setSelectedIndex(3);
        if(!distribuidores_cargados){
            Cargar_distribuidores();
            distribuidores_cargados = true;
        }
        I=false;
        E=false;
        V=false;
        D=true;
    }//GEN-LAST:event_tb_distribuidoresActionPerformed

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        // TODO add your handling code here:
        jPanel1.remove(p1);
        p1.setSize(jPanel1.getSize());
        jPanel1.add(p1);
        jPanel1.repaint();
    }//GEN-LAST:event_jPanel1ComponentResized

    private void tb_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_inventarioActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
        if(!productos_cargados){
            jl_panesMouseClicked(null);
            Cargar_inventario();
            Cargar_Sub_Inventario("Pan");
            productos_cargados = true;
        }
        I=true;
        E=false;
        V=false;
        D=false;
    }//GEN-LAST:event_tb_inventarioActionPerformed

    private void tb_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_empleadosActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
        if(!empleados_cargados){
            Cargar_empleados();
            empleados_cargados = true;
        }
        I=false;
        E=true;
        V=false;
        D=false;
    }//GEN-LAST:event_tb_empleadosActionPerformed

    private void jl_otrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_otrosMouseEntered
        // TODO add your handling code here:
        mouse_entered_options(jl_otros);
    }//GEN-LAST:event_jl_otrosMouseEntered
    private void jl_panesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_panesMouseEntered
        // TODO add your handling code here:
        mouse_entered_options(jl_panes);
    }//GEN-LAST:event_jl_panesMouseEntered

    private void jl_malteadasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_malteadasMouseEntered
        // TODO add your handling code here:
        mouse_entered_options(jl_malteadas);
    }//GEN-LAST:event_jl_malteadasMouseEntered

    private void jl_panesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_panesMouseExited
        // TODO add your handling code here:
        if(pan_click==false)
            mouse_exit_options(jl_panes);
    }//GEN-LAST:event_jl_panesMouseExited

    private void jl_malteadasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_malteadasMouseExited
        // TODO add your handling code here:
        if(malteadas_click==false)
            mouse_exit_options(jl_malteadas);
    }//GEN-LAST:event_jl_malteadasMouseExited

    private void jl_otrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_otrosMouseExited
        // TODO add your handling code here:
        if(otros_click==false)
            mouse_exit_options(jl_otros);
    }//GEN-LAST:event_jl_otrosMouseExited

    private void jl_panesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_panesMouseClicked
        // TODO add your handling code here:
        pan_click = true;
        malteadas_click = false;
        otros_click = false;
        mouse_entered_options(jl_panes);
        mouse_exit_options(jl_otros);
        mouse_exit_options(jl_malteadas);
        
        Cargar_Sub_Inventario("Pan");
    }//GEN-LAST:event_jl_panesMouseClicked

    private void jl_malteadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_malteadasMouseClicked
        // TODO add your handling code here:
        pan_click = false;
        malteadas_click = true;
        otros_click = false;
        mouse_entered_options(jl_malteadas);
        mouse_exit_options(jl_otros);
        mouse_exit_options(jl_panes);
        
        Cargar_Sub_Inventario("Malteadas");
    }//GEN-LAST:event_jl_malteadasMouseClicked

    private void jl_otrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_otrosMouseClicked
        // TODO add your handling code here:
        pan_click = false;
        malteadas_click = false;
        otros_click = true;
        mouse_entered_options(jl_otros);
        mouse_exit_options(jl_panes);
        mouse_exit_options(jl_malteadas);
        
        Cargar_Sub_Inventario("Otros");
    }//GEN-LAST:event_jl_otrosMouseClicked

    private void tabla_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_inventarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_inventarioKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Insertar_producto();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Insertar_empleado();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Insertar_distribuidor();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            //Inventario
            
            if(tabla_inventario.getSelectedRow()==-1){
                throw new Exception("Seleccione una fila.");
            }
            
            Editar_campo(0,tabla_inventario.getSelectedRow());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error:\n"+ex.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            //Empleados
            
            if(tabla_empleados.getSelectedRow()==-1){
                throw new Exception("Seleccione una fila.");
            }
            
            Editar_campo(0,tabla_empleados.getSelectedRow());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error:\n"+ex.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       try {
            // TODO add your handling code here:
            //Distribuidores
            
            if(tabla_distribuidores.getSelectedRow()==-1){
                throw new Exception("Seleccione una fila.");
            }
            
            Editar_campo(0,tabla_distribuidores.getSelectedRow());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error:\n"+ex.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void mouse_entered_options(JLabel jl){
        ImageIcon ImItemHerramientas = new ImageIcon(getClass().getResource("/Imagenes/sublinea_entered.png"));
        Icon icHerramientas = new ImageIcon(ImItemHerramientas.getImage().getScaledInstance(107,4, Image.SCALE_DEFAULT));
        jl.setIcon(icHerramientas);
        jl.setForeground(Colores.color1);
    }
    
    private void mouse_exit_options(JLabel jl){
        ImageIcon ImItemHerramientas = new ImageIcon(getClass().getResource("/Imagenes/sublinea.png"));
        Icon icHerramientas = new ImageIcon(ImItemHerramientas.getImage().getScaledInstance(107,4, Image.SCALE_DEFAULT));
        jl.setIcon(icHerramientas);
        jl.setForeground(Colores.color_foreground_options);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ItemHerramientas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel jl_malteadas;
    private javax.swing.JLabel jl_otros;
    private javax.swing.JLabel jl_panes;
    private javax.swing.JTable tabla_distribuidores;
    private javax.swing.JTable tabla_empleados;
    private javax.swing.JTable tabla_inventario;
    private javax.swing.JTable tabla_ventas;
    private javax.swing.JToggleButton tb_distribuidores;
    private javax.swing.JToggleButton tb_empleados;
    private javax.swing.JToggleButton tb_inventario;
    private javax.swing.JToggleButton tb_ventas;
    // End of variables declaration//GEN-END:variables
}
