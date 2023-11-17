/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contextos;

import Modelos.Distribuidor;
import Modelos.Empleado;
import Modelos.Producto;
import Modelos.Venta;
import com.google.api.client.util.ArrayMap;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elbot
 */
public class Conexion_Firestore {
    
    private static Firestore db;
    private static List<Venta> ventas = new ArrayList<>();
    private static final String credentials = "{\n" +
    "  \"type\": \"service_account\",\n" +
    "  \"project_id\": \"panaderia-4766e\",\n" +
    "  \"private_key_id\": \"bc5950e58809bff057b89d596d649c3b3a3ae392\",\n" +
    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/6MokDguz+n6C\\nGXCK4pp9/yAin+BuwhDgX+J0CfkmKZJG3CsTK4F5fLVJOETd+tBp5bd5zm9HmyVX\\nynwNZbnnb9zxt2951WKHni6QZ9/pYrNzlIsjcSGRFdldosKNn4oIbmNZFVyoAo1+\\n/k3xLATk/kICowLKrb6GvPEbZQFTQ6cpKefHrHHGHwGR5kyeAnUIgrhWF8oamr/y\\nNQcXm9AZBl9g+kL9hbYaC8aQK3Lb1Rjto/qp7Tou19B3hqhS/8xWi+cCxOzORB48\\n58h19cYTF/DRBMykaeU8t1+EiGWRbQi9rfRi0dC55x9oTagbxnee4h8fwahTvzBS\\nUKrQJ5mxAgMBAAECggEAB6/6SvPyFZFS+ICu4K1fnWz3G1P2sFEfTnGg11+f5p6z\\nMJn4ssGjI9vyQh7aKV828osEbMFvdTmahxgCHKJGzmXNfrRHp6R72C1mtZunnUh6\\nIkbgxPhu7QooRSc0Lmql450SZzrjFYrWoq/qrN7Dym2B9OuVPMu1bl3Ktbt30F87\\nWUhgbe9CbrUUl6O1RT/yjI6ZG4BRKN5ZRrPFqfkLm3JT+/Q7EFGpPrQ1skSKIQF1\\nO6JskBOlaVcRfLHSCuyIz4BkcK8rMLHx83w0U3n2V+In1gGcThdzz74atyPhMp2f\\ntBcg8B5DUSYvbzyD4J1+kUPdWqDR7oJJFAWGlP4NgQKBgQDt9ISg0dwj86dWJeHx\\nO6ANFssvPM0nZ8I/z9hzAPeUidxp7CIv2YCna7Ovj1J19KYnRNukS6Y+EY9KZdZZ\\nU7WZDH5bB2yQZmAHgLZ3ejp9F7ba3zZcaGgJTpppog01vicqjb58AwhTtQnZ9UxF\\nDGmPIGpN7qBpYLuG57LKJWKQYQKBgQDOdl9vpGUfxi3rXrnKoMv8MLJso6XLwi3W\\nA+I19onx1sts3B2HGuFWH6LR8NtKIVFkYEoiZJhpHNJzUhdoNIy07msRpHL4ZMHf\\nD6ZgZbnjgj3vSeRAVcZV/9H51aYVqt7ciHI8A1LeRiAutixndbeHtb2y9o4wKwoj\\nVBSqIVvLUQKBgBX/1D82B78/KIpbaD8wPwTWxaQxX9ozyo6xvZYsUbdm43zbevGn\\njzBrUap6eqozGyVoRVI0OENmxjOfpKiPh6uFryzlS9Ex1dD7wmLbZwrnpV0o49K5\\nSF0L6lS3ybbdRKu3fRPkB+INS8Ld3dZIZn34Bjv8QtJbQhsf0rTDrhAhAoGBAMOQ\\nwOnBcMBeOpTez2IuWcpyXjAl+yA8uKomV5oCA7x+V4awIZAeWB/6K4JtgWqAkNZh\\nlxdo7i1yza3Krr1YOYgQ5n55pSXAWxvteQ2KjkAA2KXW+kwuc4Z2ofDi/UU864k9\\n/eb2xwIgrs9QZFND4Cq+bggdH6Zkvl+kf0Z//rFhAoGBAJUC5/JknZF6cXLLD8ix\\nfz9/sig4fgEXaao4cw5rB4zeaIa5gAEauKTsZcsuVkCAxXGYUEir4RFumgoC23uf\\nnV/kAZwAA3CGiBOkXMINH5PmBLHWqpSyAS4UDUd7WQEt0YoacS9QKCAvHqDV1cSS\\n2T28m2Ehfh1M9JOCaJa+p15o\\n-----END PRIVATE KEY-----\\n\",\n" +
    "  \"client_email\": \"firebase-adminsdk-otgrj@panaderia-4766e.iam.gserviceaccount.com\",\n" +
    "  \"client_id\": \"117097151411952443718\",\n" +
    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-otgrj%40panaderia-4766e.iam.gserviceaccount.com\",\n" +
    "  \"universe_domain\": \"googleapis.com\"\n" +
    "}";
    
    public static void conectarFirebase() throws Exception{
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(credentials.getBytes())))
                .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            
            //JOptionPane.showMessageDialog(null, "Conexion");
            System.out.println("Conexión Firebase");
        } 
        catch (Exception e) {
            throw new Exception("\nOcurrio un error en la conexión: "+e);
        }
    }
    
    public static boolean LogIn(String tipo_ingreso,String id, String contraseña, String sucursal) throws Exception{
        boolean pass = false;
        try {
            Query query = db.collection("Sucursales")
            .whereEqualTo("Nombre",sucursal);
            
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Sesion.sucursal = document.getId();
                Query q1 = db.collection("Sucursales").document(Sesion.sucursal).collection("Usuarios")
                        .whereEqualTo("id_usuario", id);
                
                ApiFuture<QuerySnapshot> q1snapshot = q1.get();
                
                if(q1snapshot.get().isEmpty()){
                    throw new Exception("Id de usuario no encontrado.");
                }
                for(DocumentSnapshot d1 : q1snapshot.get().getDocuments()){
                    Map<String, Object> mp = d1.getData();
                    System.out.println("c "+contraseña+ "  mp "+ mp.get("contraseña"));
                    if(contraseña.equals(mp.get("contraseña").toString())){
                        if(tipo_ingreso.equals("administracion")&&!(boolean)mp.get("admin")){
                            throw new Exception("Debe ser administrador para poder entrar a esta sección.");
                        }
                        Sesion.Nombre = (String)mp.get("nombre");
                        Sesion.Usuario = (String)mp.get("id_usuario");
                        Sesion.cedula = (String)mp.get("cedula");
                        pass = true;
                    }
                    else{
                        throw new Exception("Contraseña incorrecta.");
                    }
                }
            }
        } 
        catch (Exception e) {
            throw new Exception("\nLogIn: "+e.getMessage());
        }
        return pass;
    }
    
    public static Map<String, String> Buscar_sucursales() throws Exception{
        //Nombre, ID
        Map<String, String> ids = new ArrayMap<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("Sucursales").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> mp = document.getData();
                ids.put(mp.get("Nombre").toString(), document.getId());
            }
        } 
        catch (Exception e) {
            throw new Exception("Buscar_sucursales: \n"+e.getMessage());
        }
        return ids;
    }
    
    public static List<Producto> ver_inventario(String sucursal) throws Exception{
        List<Producto> productos = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("Sucursales").document(sucursal).collection("Inventario").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> mp = document.getData();
                Producto p = new Producto(document.getId(),(String)mp.get("nombre"), ((Number)mp.get("stock")).intValue(), ((Number)mp.get("precio_unitario")).intValue(), (String)mp.get("distribuidor"), (String)mp.get("tipo"));
                productos.add(p);
            }
        } 
        catch (Exception e) {
            throw new Exception("Ver_Inventario: \n"+e.getMessage());
        }
        return productos;
    }
    
    public static List<Empleado> ver_empleados(String sucursal) throws Exception{
        List<Empleado> empleados = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("Sucursales").document(sucursal).collection("Empleados").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> mp = document.getData();
                String fecha_afiliacion = mp.get("fecha_afiliacion").toString();
                String fecha_nacimiento = mp.get("fecha_nacimiento").toString();
                Empleado p = new Empleado(document.getId(),(String)mp.get("cedula"), (String)mp.get("nombre"), (String)mp.get("apellidos"), ((Number)mp.get("salario")).intValue(), (String)mp.get("celular"), (String)mp.get("correo"), fecha_nacimiento, fecha_afiliacion, (String)mp.get("cargo"));
                empleados.add(p);
            }
        } 
        catch (Exception e) {
            throw new Exception("Ver_empleados: \n"+e.getMessage());
        }
        return empleados;
    }
    
    public static List<Distribuidor> ver_distribuidores(String sucursal) throws Exception{
        List<Distribuidor> distribuidores = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("Sucursales").document(sucursal).collection("Distribuidores").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> mp = document.getData();
                Distribuidor p = new Distribuidor(document.getId(),(String)mp.get("nombre"), (String)mp.get("numero"), (String)mp.get("direccion"));
                distribuidores.add(p);
            }
        } 
        catch (Exception e) {
            throw new Exception("Ver_empleados: \n"+e.getMessage());
        }
        return distribuidores;
    }
    
    public static void ver_ventas(String sucursal, DefaultTableModel tm_ventas) throws Exception{
        try {
            
            ////////
            ApiFuture<QuerySnapshot> future = db.collection("Sucursales").document(sucursal).collection("Ventas").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> mp = document.getData();
                Agregar_venta(document.getId(),mp,tm_ventas);
            }
            ////////
            CollectionReference collection = db.collection("Sucursales").document(sucursal).collection("Ventas");
            collection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirestoreException e) {
                if (e != null) {
                    System.err.println("Error al escuchar cambios en la colección: " + e.getMessage());
                    return;
                }
                for (DocumentChange change : queryDocumentSnapshots.getDocumentChanges()) {
                    switch (change.getType()) {
                        case ADDED:
                            Map<String, Object> mp = change.getDocument().getData();
                        {
                            try {
                                Agregar_venta(change.getDocument().getId(),mp,tm_ventas);
                            } catch (Exception ex) {
                                Logger.getLogger(Conexion_Firestore.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                            break;
                    }
                }
            }
        });
            
        } 
        catch (Exception e) {
            throw new Exception("Ver_empleados: \n"+e.getMessage());
        }
    }
    
    private static void Agregar_venta(String id, Map<String, Object> mp, DefaultTableModel tm_ventas) throws Exception{
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fecha = dateFormat.format(((Timestamp)mp.get("fecha")).toDate());
            int cn = ((Number)mp.get("cantidad_productos")).intValue();
            Venta p = new Venta(id, fecha, (String)mp.get("vendedor_cc"), (String)mp.get("cliente"), ((Number)mp.get("total")).intValue(),cn);

            for (int i = 1; i <= cn; i++) {
                p.productos.put((String)mp.get("p"+String.valueOf(i)), 
                        new int[]{
                            ((Number)mp.get("cn"+String.valueOf(i))).intValue(),
                            ((Number)mp.get("v"+String.valueOf(i))).intValue()});
            }

            tm_ventas.addRow(new Object[]{p.fecha,p.vendedor,p.cliente,p.total});
            ventas.add(p);
        } 
        catch (Exception e) {
            throw new Exception("Agregar_venta: \n"+e.getMessage());
        }
    }
    
    public static int Cargar_venta_detallada(int i, DefaultTableModel tm_venta) throws Exception{
        int total=0;
        try {
            Venta p = ventas.get(i);
            for (String clave : p.productos.keySet()) {
                int[] valor = p.productos.get(clave);
                tm_venta.addRow(new Object[]{clave,String.valueOf(valor[0]),String.valueOf(valor[1])});
            }
            total = p.total;
        } 
        catch (Exception e) {
            throw new Exception("Cargar_venta_detallada: \n"+e.getMessage());
        }
        return total;
    }
    
    public static void Actualizar_inventario(Producto p) throws Exception{
        try {
            //Convertir a mapa
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("distribuidor", p.distribuidor);
            mp.put("nombre", p.nombre);
            mp.put("precio_unitario", p.precio_u);
            mp.put("stock", p.stock);
            mp.put("tipo", p.tipo);
            
            DocumentReference documentReference = db.collection("Sucursales").document(Sesion.sucursal).collection("Inventario").document(p.id);
            ApiFuture<WriteResult> future = documentReference.update(mp);
        } 
        catch (Exception e) {
            throw new Exception("Actualizar_inventario: \n"+e.getMessage());
        }
    }
    
    public static void Actualizar_distribuidor(Distribuidor p) throws Exception{
        try {
            //Convertir a mapa
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("direccion", p.direccion);
            mp.put("nombre", p.nombre);
            mp.put("numero", p.numero);
            
            DocumentReference documentReference = db.collection("Sucursales").document(Sesion.sucursal).collection("Distribuidores").document(p.id);
            ApiFuture<WriteResult> future = documentReference.update(mp);
        } 
        catch (Exception e) {
            throw new Exception("Actualizar_distribuidor: \n"+e.getMessage());
        }
    }
    
    public static void Actualizar_empleado(Empleado p) throws Exception{
        try {
            //Convertir a mapa
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("apellidos", p.apellido);
            mp.put("cargo", p.cargo);
            mp.put("cedula", p.cedula);
            mp.put("celular", p.celular);
            mp.put("correo", p.correo);
            mp.put("nombre", p.nombre);
            mp.put("fecha_afiliacion", p.fecha_afiliacion);
            mp.put("fecha_nacimiento", p.fecha_nacimiento);
            mp.put("salario", p.salario);
            
            DocumentReference documentReference = db.collection("Sucursales").document(Sesion.sucursal).collection("Empleados").document(p.id);
            ApiFuture<WriteResult> future = documentReference.update(mp);
        } 
        catch (Exception e) {
            throw new Exception("Actualizar_empleado: \n"+e.getMessage());
        }
    }
    
    public static String Insertar_empleado() throws Exception{
        String id = "";
        try {
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("apellidos", "");
            mp.put("cargo", "");
            mp.put("cedula", "");
            mp.put("celular", "");
            mp.put("correo", "");
            mp.put("nombre", "");
            mp.put("fecha_afiliacion", "");
            mp.put("fecha_nacimiento", "");
            mp.put("salario", 0);
            ApiFuture<DocumentReference> future = db.collection("Sucursales").document(Sesion.sucursal).collection("Empleados").add(mp);
            id = future.get().getId();
        } 
        catch (Exception e) {
            throw new Exception("Insertar_empleado: \n"+e.getMessage());
        }
        return id;
    }
    
    public static String Insertar_producto() throws Exception{
        String id = "";
        try {
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("distribuidor", "");
            mp.put("nombre", "");
            mp.put("precio_unitario", "");
            mp.put("stock", "");
            mp.put("tipo", "");
            ApiFuture<DocumentReference> future = db.collection("Sucursales").document(Sesion.sucursal).collection("Inventario").add(mp);
            id = future.get().getId();
        } 
        catch (Exception e) {
            throw new Exception("Insertar_producto: \n"+e.getMessage());
        }
        return id;
    }
    
    public static String Insertar_distribuidor() throws Exception{
        String id = "";
        try {
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("direccion", "");
            mp.put("nombre", "");
            mp.put("numero", "");
            ApiFuture<DocumentReference> future = db.collection("Sucursales").document(Sesion.sucursal).collection("Distribuidores").add(mp);
            id = future.get().getId();
        } 
        catch (Exception e) {
            throw new Exception("Insertar_distribuidor: \n"+e.getMessage());
        }
        return id;
    }
    
    public static void Insertar_venta(Venta v) throws Exception{
        try {
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            Map<String, Object> mp = new ArrayMap<>();
            mp.put("cliente", v.cliente);
            mp.put("total", v.total);
            mp.put("vendedor_cc", v.vendedor);
            mp.put("fecha",fechaHoraActual);
            mp.put("cantidad_productos", v.cantidad_productos);
            
            for (int i = 1; i <= v.cantidad_productos; i++) {
                Object[] keys = v.productos.keySet().toArray();
                
                String cn = "cn"+i;
                String p = "p"+i;
                String vi = "v"+i;
                
                mp.put(cn, v.productos.get(keys[i].toString())[0]);
                mp.put(vi, v.productos.get(keys[i].toString())[1]);
                mp.put(p,keys[i].toString());
            }  
            
            ApiFuture<DocumentReference> future = db.collection("Sucursales").document(Sesion.sucursal).collection("Ventas").add(mp);
        } 
        catch (Exception e) {
            throw new Exception("Insertar_venta: \n"+e.getMessage());
        }
    }
}
