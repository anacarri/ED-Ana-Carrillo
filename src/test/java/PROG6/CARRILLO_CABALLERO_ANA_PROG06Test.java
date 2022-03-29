/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package PROG6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ana
 */
public class CARRILLO_CABALLERO_ANA_PROG06Test {
      
     /**
     * Test of generarOrdenAleatorioDeJugadores method, of class CARRILLO_CABALLERO_ANA_PROG06.
     */
    @Test
    public void testGenerarOrdenAleatorioDeJugadores() {
        //inicializar clase
        CARRILLO_CABALLERO_ANA_PROG06 sut=null; //sut=software under test
       int [] resultado= sut.generarOrdenAleatorioDeJugadores(1);
       int [] expected = new int[1];
       expected[0] = 0;
       assertArrayEquals(expected, resultado); //Si le hemos dicho que genere un array de jugadores aleatorios con solo 1, siempre va a ser igual
        int [] resultado2= sut.generarOrdenAleatorioDeJugadores(2);
        assertEquals(2 , resultado2.length ); //Está generando dos posiciones
            
    }
    

    /**
     * Test of estaLaPosicionLibre method, of class CARRILLO_CABALLERO_ANA_PROG06.
     * Este metodo comprueba si una posición de un array está libre
     */
    @Test
    public void testEstaLaPosicionLibre() {
        CARRILLO_CABALLERO_ANA_PROG06 sut=null; //sut=software under test
        int [] posiciones = new int[1];
        boolean resultado = sut.estaLaPosicionLibre(posiciones, 0); //Si tiene un valor distinto del -1 es que la posición esta ocupada
        assertFalse(resultado);
        posiciones[0] = -1;
        resultado = sut.estaLaPosicionLibre(posiciones, 0);
        assertTrue(resultado);
    }



    /**
     * Test of mostrarPregunta method, of class CARRILLO_CABALLERO_ANA_PROG06.
     */
    @Test
    public void testMostrarPregunta() {
    }

 

    /**
     * Test of leerRondas method, of class CARRILLO_CABALLERO_ANA_PROG06.
     */
    @Test
    public void testLeerRondas() {
                CARRILLO_CABALLERO_ANA_PROG06 sut=null; //sut=software under test
                assertEquals(3, sut.leerRondas(1));
                assertEquals(5, sut.leerRondas(2));
                assertEquals(10, sut.leerRondas(3));
                assertEquals(20, sut.leerRondas(4));
                assertEquals(3, sut.leerRondas(0));
                assertEquals(3, sut.leerRondas(5));

    }

    /**
     * Test of inicializarPuntuaciones method, of class CARRILLO_CABALLERO_ANA_PROG06.
     */
    @Test
    public void testInicializarPuntuaciones() {
                        CARRILLO_CABALLERO_ANA_PROG06 sut=null; //sut=software under test
                        int [] puntuaciones = sut.inicializarPuntuaciones(5);
                        assertEquals(5, puntuaciones.length);
                        assertEquals(0, puntuaciones[0]);
                         assertEquals(0, puntuaciones[1]);
                        assertEquals(0, puntuaciones[2]);
                        assertEquals(0, puntuaciones[3]);
                        assertEquals(0, puntuaciones[4]);
    }

    
}
