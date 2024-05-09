/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import Contextos.Conexion_Firestore;
import Contextos.Sesion;
import Vistas.Facturacion.Facturacion;
import static org.assertj.swing.data.TableCell.row;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author elbot
 */
public class Administracion_Test {
    private FrameFixture window;
    private FrameFixture client;

    /**
     * Constructor de clase
     */
    public Administracion_Test() {}

    /**
     * Fuerza a una prueba a fallar si el acceso a los componentes de la GUI
     * no se realiza en el EDT (Event Dispatch Thread)
     */
    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    /**
     * Inicializa los dispositivos de prueba, se ejecuta cada vez 
     * que se ejecute un método de prueba
     */
    @Before
    public void setUp() {
        try {
            Conexion_Firestore.conectarFirebase();
            Sesion.Nombre = "Sherman Lopez";
            Sesion.Usuario = "Sherman10";
            Sesion.cedula = "012344567";
            Sesion.sucursal = "UJHQCBwK1FBUXxDWFq2T";
        } catch (Exception ex) {
            Logger.getLogger(Administracion_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        Facturacion frame = GuiActionRunner.execute(() -> new Facturacion());
        window = new FrameFixture(frame);
        window.show();
    }

    /**     
     * Limpia los recursos utilizados después de ejecutar cada método de prueba
     * y libera el bloqueo de teclado y moyse para la siguiente prueba     
     */
    @After
    public void tearDown() {
        window.cleanUp();
    }
    
    @Test
    public void Facturacion() throws InterruptedException{
        Thread.sleep(1000);
        
        String tipo, producto, total;
        int cantidad, precio, subtotal;
        //Comprobar primera fila
        window.button("Agregar").click().robot().waitForIdle();
        producto = window.table("Tabla").cell(row(0).column(0)).value();
        tipo = window.table("Tabla").cell(row(0).column(1)).value();
        cantidad = Integer.parseInt(window.table("Tabla").cell(row(0).column(2)).value());
        precio = Integer.parseInt(window.table("Tabla").cell(row(0).column(3)).value());
        subtotal = Integer.parseInt(window.table("Tabla").cell(row(0).column(4)).value());
        total = window.label("Total").text();
        Assert.assertEquals("Pan de queso pequeño", producto);
        Assert.assertEquals("Pan", tipo);
        Assert.assertEquals(1, cantidad);
        Assert.assertEquals(900, precio);
        Assert.assertEquals(900, subtotal);
        Assert.assertEquals("900$", total);
        
        Thread.sleep(2000);
        //Comprobar segunda fila 
        window.comboBox("Tipo").selectItem(1);
        window.comboBox("Producto").selectItem(1);
        window.comboBox("Cantidad").selectItem(1);
        window.button("Agregar").click().robot().waitForIdle();
        producto = window.table("Tabla").cell(row(1).column(0)).value();
        tipo = window.table("Tabla").cell(row(1).column(1)).value();
        cantidad = Integer.parseInt(window.table("Tabla").cell(row(1).column(2)).value());
        precio = Integer.parseInt(window.table("Tabla").cell(row(1).column(3)).value());
        subtotal = Integer.parseInt(window.table("Tabla").cell(row(1).column(4)).value());
        total = window.label("Total").text();
        Assert.assertEquals("Malteada de Fresa", producto);
        Assert.assertEquals("Malteadas", tipo);
        Assert.assertEquals(2, cantidad);
        Assert.assertEquals(5800, precio);
        Assert.assertEquals(11600, subtotal);
        Assert.assertEquals("12500$", total);
        
        Thread.sleep(2000);
        //Comprobar crear factura
        client = new FrameFixture(window.button("Crear").click().robot(),"ClienteFrame");
        Thread.sleep(2000);
        client.textBox().enterText("QAS");
        client.button().click().robot().waitForIdle();
        
        assertThat(client.requireNotVisible());
        Thread.sleep(2000);
    }
    
    @Test
    public void Inventario() throws InterruptedException{
        Thread.sleep(1000);
        int i;
        //BELLO
        //Combinacion .1
        window.toggleButton("Inventario").click().robot().waitForIdle();
        window.comboBox("Categoria").selectItem(0);
        window.label("Buscar").click().robot().waitForIdle();
        
        i = window.table().rowCount();
        Assert.assertEquals(3, i);
        
        //Combinacion .2
        window.comboBox("Categoria").selectItem(1);
        window.label("Buscar").click().robot().waitForIdle();
        i = window.table().rowCount();
        Assert.assertEquals(2, i);
        
        //Combinacion .3
        window.comboBox("Categoria").selectItem(2);
        window.label("Buscar").click().robot().waitForIdle();
        i = window.table().rowCount();
        Assert.assertEquals(2, i);
        
        //ROBLEDO
        window.comboBox("Sucursal").selectItem(0);
        //Combinacion .1
        window.comboBox("Categoria").selectItem(0);
        window.toggleButton("Inventario").click();
        window.label("Buscar").click().robot().waitForIdle();
        
        i = window.table().rowCount();
        Assert.assertEquals(3, i);
        
        //Combinacion .2
        window.comboBox("Categoria").selectItem(1);
        window.label("Buscar").click().robot().waitForIdle();
        i = window.table().rowCount();
        Assert.assertEquals(2, i);
        
        //Combinacion .3
        window.comboBox("Categoria").selectItem(2);
        window.label("Buscar").click().robot().waitForIdle();
        i = window.table().rowCount();
        Assert.assertEquals(2, i);
        Thread.sleep(2000);
    }
}
