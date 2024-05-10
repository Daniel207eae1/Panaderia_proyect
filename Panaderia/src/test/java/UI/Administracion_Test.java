/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import Contextos.Conexion_Firestore;
import Contextos.Sesion;
import Vistas.Administracion.Principal;
import Vistas.Facturacion.Facturacion;
import static org.assertj.swing.data.TableCell.row;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Int2DArrayAssert;
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
    private FrameFixture venta;
    private FrameFixture editar;
    private FrameFixture insertar;

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
            Sesion.Nombre = "Augusto Orquiza Ramirez";
            Sesion.Usuario = "Augusto20";
            Sesion.cedula = "0123456789";
            Sesion.sucursal = "UJHQCBwK1FBUXxDWFq2T";
        } catch (Exception ex) {
            Logger.getLogger(Administracion_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal frame = GuiActionRunner.execute(() -> new Principal());
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
    public void Inventario() throws InterruptedException{
        Thread.sleep(2000);

        //Comprobar Panes
        window.toggleButton("Inventario").click().robot().waitForIdle();
        window.label("Panes").click().robot().waitForIdle();
        Assert.assertEquals(3, window.table().rowCount());
        Thread.sleep(1000);
        window.table().selectRows(1);
        //Editar
        editar = new FrameFixture(window.button("Editar").click().robot(),"Subseccion");
        editar.textBox("Nombre1").enterText("Pancacho");
        editar.button("Guardar").click().robot().waitForIdle();
        window.optionPane().okButton().click();
        //Insertar
        insertar = new FrameFixture(window.button("Insertar").click().robot(),"Subseccion");
        insertar.textBox("Nombre1").enterText("Pan borracho");
        insertar.textBox("Precio").enterText("1000");
        insertar.comboBox("Stock").selectItem(5);
        insertar.comboBox("Distribuidor").selectItem(0);
        insertar.button("Guardar").click().robot().waitForIdle();
        window.optionPane().okButton().click();
        Thread.sleep(4000);
        //Eliminar
        window.table().selectRows(window.table().rowCount()-1);
        editar = new FrameFixture(window.button("Editar").click().robot(),"Subseccion");
        editar.button("Eliminar").click().robot().waitForIdle();
        window.optionPane().yesButton().click();
        window.optionPane().okButton().click();
        Thread.sleep(3000);
        
        //Comprobar Malteadas
        window.label("Malteadas").click().robot().waitForIdle();
        Assert.assertEquals(2, window.table().rowCount());
        Thread.sleep(2000);
        
        //Comprobar Otros
        window.label("Otros").click().robot().waitForIdle();
        Assert.assertEquals(2, window.table().rowCount());
        Thread.sleep(2000);
    }
    
    @Test
    public void Empleados() throws InterruptedException{
        Thread.sleep(2000);

        //Comprobar Panes
        window.toggleButton("Empleados").click().robot().waitForIdle();
        Assert.assertEquals(3, window.table().rowCount());
        Thread.sleep(1000);
        window.table().selectRows(2);
        //Editar
        editar = new FrameFixture(window.button("Editar").click().robot(),"Subseccion");
        editar.textBox("Nombre2").enterText("Eduardo");
        editar.button("Guardar").click().robot().waitForIdle();
        window.optionPane().okButton().click();
        Thread.sleep(4000);
    }
    
    @Test
    public void Ventas() throws InterruptedException{
        Thread.sleep(2000);
        
        //Comprobar Panes
        window.toggleButton("Ventas").click().robot().waitForIdle();
        Thread.sleep(1000);
        int total = Integer.parseInt(window.table().cell(row(2).column(3)).value());
        Thread.sleep(2000);
        window.table().unselectRows(2);
        Thread.sleep(2000);
        //Ventas
        venta = new FrameFixture(window.table().selectRows(2).doubleClick().robot(),"Venta");
        Thread.sleep(2000);
        String total_venta = venta.label("TotalVenta").text();
        String ventas = total+"$";
        Assert.assertEquals(total_venta, ventas);
        Thread.sleep(2000);
        venta.close();
        Thread.sleep(4000);
    }
    
}
