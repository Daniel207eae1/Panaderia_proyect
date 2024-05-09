/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import Contextos.Conexion_Firestore;
import Vistas.Administracion.Inicio_Sesion;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author elbot
 */
public class LogIn_Test {
    private FrameFixture window;

    /**
     * Constructor de clase
     */
    public LogIn_Test() {}

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
        } catch (Exception ex) {
            Logger.getLogger(LogIn_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        Inicio_Sesion frame = GuiActionRunner.execute(() -> new Inicio_Sesion());
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
    public void Mal_inicio_1() throws InterruptedException{
        window.textBox("Usuario").enterText("cajero");
        window.textBox("Contraseña").enterText("password");
        window.button("Entrar").click().robot().waitForIdle();
        assertThat(window.requireVisible());
        Thread.sleep(2500);
        window.optionPane().okButton().click();
        
        //Pulsar Administración
        window.toggleButton("Administracion").click();
        window.button("Entrar").click().robot().waitForIdle();
        Thread.sleep(4500);
        window.optionPane().okButton().click();
        
        //Cambiar de sucursal
        window.comboBox().selectItem(1);
        window.button("Entrar").click().robot().waitForIdle();
        Thread.sleep(3500);
        window.optionPane().okButton().click();
        
        assertThat(window.requireVisible());
    }
    
    @Test
    public void Iniciar_sesion_Facturacion(){
        window.textBox("Usuario").enterText("Sherman10");
        window.textBox("Contraseña").enterText("password");
        window.toggleButton("Facturacion").click();
        JButtonFixture buttonFixture = window.button("Entrar").click();
        buttonFixture.robot().waitForIdle();
        assertThat(window.requireNotVisible());
    }
    
    @Test
    public void Iniciar_sesion_Administrador(){
        window.textBox("Usuario").enterText("Augusto20");
        window.textBox("Contraseña").enterText("password");
        window.toggleButton("Administracion").click();
        JButtonFixture buttonFixture = window.button("Entrar").click();
        buttonFixture.robot().waitForIdle();
        assertThat(window.requireNotVisible());
    }
}
