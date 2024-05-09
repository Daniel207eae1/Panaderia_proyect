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
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author elbot
 */
public class TestApp {
    private FrameFixture window;

    /**
     * Constructor de clase
     */
    public TestApp() {}

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
            Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
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
    public void Iniciar_sesion(){
        window.textBox("Usuario").enterText("Admin");
        window.textBox("Contraseña").enterText("password");
        window.button("Facturacion").click();
        window.button("Entrar").click();
        //Verficiar que no deje entrar.
        assertThat(window.optionPane().dialog().label().text().equals("Hola"));
    }
}
