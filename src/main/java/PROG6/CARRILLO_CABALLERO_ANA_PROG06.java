/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG6;

       
import java.util.Random;
import java.util.Scanner;

/**
 *Desarrollar un programa en Java que permita jugar a un juego de preguntas y respuestas
inspirado en el conocido concurso de televisión Saber y ganar.
* El programa deberá permitir jugar a una versión simplificada de un concurso de preguntas y
respuestas en el que varios jugadores responden preguntas por turnos, acumulan puntos y
al final gana el que más puntos tiene. Toda la interacción con los usuarios será mediante
entrada y salida estándar (teclado y pantalla).
 * 
 */
public class CARRILLO_CABALLERO_ANA_PROG06 {
    
   
    /**Este es el main principal, se encarga de gestionar la partida.
     * 
     */
     
    public static void main(String[] args) {
        escribeMensaje("Introduce el número de jugadores ");
        int numJugadores = leerEnteroDeTeclado(); // leemos el número de jugadores por teclado
        String[] nombresJugadores = obtenerJugadores(numJugadores); // Pedimos el nombre de jugadores por teclado
        int[] ordenJugadores = generarOrdenAleatorioDeJugadores(numJugadores); // Generamos el orden aleatorio para los jugadores
        
        mostrarArrayJugadores(ordenJugadores, nombresJugadores);//Mostramos el orden de jugadores
         escribeMensaje("Introduce el número de rondas");
        escribeMensaje("1. Rápida (3 rondas)");
        escribeMensaje("2. Corta (5 rondas)");
        escribeMensaje("3. Normal (10 rondas)");
        escribeMensaje("4. Larga (20 rondas)");
        int opcion = leerEnteroDeTeclado();
        int rondas = leerRondas(opcion);
        int rondaActual = 0;

        int[] puntuaciones = inicializarPuntuaciones(numJugadores); //Inicializamos el array de puntuaciones
        
        while (rondaActual < rondas) {
            escribeMensaje("Ronda: " + (rondaActual+1)); //Mostramos la ronda actual. Le sumamos 1, para que visualmente sea la ronda 1 en vez de la 0.
            for(int j = 0; j < numJugadores; j++) {
                escribeMensaje("Turno del jugador: " + nombresJugadores[ordenJugadores[j]]);//Mostramos el nombre del jugador al que le toca.
                int respuestaCorrecta = mostrarPregunta(); //Mostrar pregunta, se encarga de mostrar la pregunta al usuario y calcular la respuesta correcta.
                escribeMensaje("Introduzca la respuesta");
                int respuestaUsuario = leerEnteroDeTeclado(); //Leemos la respuesta del teclado
                if (respuestaCorrecta == respuestaUsuario) { //Si la respuesta del usuario es la correcta
                    escribeMensaje("Respuesta correcta!"); // mostramos mensaje de que ha acertado
                    puntuaciones[j]++; // sumamos un punto
                } else {
                    escribeMensaje("Respuesta incorrecta!, el resultado es: " + respuestaCorrecta); // sino le decimos que es incorrecta y mostramos la respuesta correcta.
                }
            }
            mostrarPuntosJugadores(ordenJugadores, puntuaciones, nombresJugadores); //Mostramos los puntos de los jugadores con los nombres
            rondaActual++; // sumamos una ronda
        }
        
      mostrarGanador(ordenJugadores, puntuaciones, nombresJugadores); //mostramos el ganador 
    }
    
    /**
     * Función para imprimir un mensaje por pantalla
     */
    
    public static void escribeMensaje (String mensaje) { 
        System.out.println(mensaje);  
    }
    /**
     * Función para leer un entero de teclado
     */
    
    public static int leerEnteroDeTeclado () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    //Función para leer un String por teclado
    public static String leerStringDeTeclado() {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
    }
    
    /**
     * Función para generar el orden aleatorio de los jugadores
     */
    public static int[] generarOrdenAleatorioDeJugadores (int numJugadores) {
        int[] ordenJugadores = new int[numJugadores]; //Generamos un array de tamaño numJugadores
        for (int i = 0; i < numJugadores; i++) {//Lo inicializamos con valor -1 para que no tome el 0 de referencia
            ordenJugadores[i] = -1;
        }
        
        for (int j = 0; j < numJugadores; j++) { //Para cada jugador vamos a generar su posición aleatoriamente
            boolean posicionLibre = false; //definicimos si la posición de la array está libre con un buleano (por defecto es false)
            int posicion = 0;
            while (!posicionLibre) { //Si la posición no está libre
                posicion = generarNumeroAleatorio(0, numJugadores); //Generamos un número aleatorio entre 0 y el número máximo de jugadores
                if (estaLaPosicionLibre(ordenJugadores, posicion)) { //Comprobamos si esa posición está libre
                    posicionLibre = true; 
                }
            }
            ordenJugadores[j] = posicion; //Asignamos la posición libre al array con los órdenes
            posicionLibre = false; //Volvemos a poner la posición libre en false para generar la siguiente posición aleatoria
        }
        return ordenJugadores; //devolver un array con las posiciones de los jugadores ordenadas aleatoriamente.
    }
    
    /**
     * Función Para ver si la posición del array está libre para meter el órden de jugadores
     */
    public static boolean estaLaPosicionLibre(int[]ordenJugadores, int posicion) {
        for (int i = 0; i < ordenJugadores.length; i++) {
            if (ordenJugadores[i] == posicion) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Función en la que se genera un número aleatorio pero el max-min lo especificamos en la Función "MontrarPregunta"
     */
    public static int generarNumeroAleatorio(int min, int max)  
    {
        return (int) (Math.random() * (max - min)) + min;
    }
    /**
     * Función para mostrar el orden de los jugadores que se guardan en el array, Luego devuelve el jugador y la posición
     */
    public static void mostrarArrayJugadores(int[] array, String[] nombresJugadores) { 
        escribeMensaje("El orden de los jugadores es:");
        for (int i = 0; i < array.length; i++) {
            escribeMensaje(nombresJugadores[array[i]]);
        }
    }
    
    /**
     * Función mostrarPregunta: Aqui he creado el bucle con Switch para generar las preguntas con las operaciones de suma,resta y multiplicación, en ellas especificamos la operación 
     * aritmética a realizar  + función numeroAleatorio para que lo genere.
     * 
     */
    public static int mostrarPregunta() {
        int numEnteros = generarNumeroAleatorio(4, 8); //Generamos el número aleaotrio de operaciones que hay que hacer (entre4-8)
        int numeroAleatorio = generarNumeroAleatorio(2, 12);//Generamos el primer número aleatorio(entre 2 y 12)
        int respuesta = numeroAleatorio; //declaramos la variable respuesta que es la que contendrá el resultado final de la operación (el valor inicial es el primer nº aleatorio generado)
        String operacion = "Operacion: " + numeroAleatorio; //declaramos la variable operación, que contiene la pregunta que se mostrará al jugador
        for (int i = 0; i < numEnteros-1; i++)
        {
           numeroAleatorio = generarNumeroAleatorio(2, 12);//Llamamos a la función para generar un nº aleatorio y lo almacenamos en la variable numeroAleatorio
            switch (generarNumeroAleatorio(0, 2)) {
                case 0: //suma
                    operacion+= " + " + numeroAleatorio;
                    respuesta = respuesta + numeroAleatorio;
                    ;break;
                case 1: //resta
                    operacion+= " - " + numeroAleatorio;
                    respuesta = respuesta - numeroAleatorio;
                    ;break;
                case 2: //multiplicacion
                    operacion+= " * " + numeroAleatorio;
                    respuesta = respuesta * numeroAleatorio;
                    ;break;
            } 
        }
        escribeMensaje(operacion); //Mostramos al usuario la operación que tiene que calcular
        return respuesta; //devolvemos el resultado de la operación para luego comprobarla con la del usuario
    } 
    
    public static void mostrarPuntosJugadores(int[] jugadores, int[] puntuaciones, String[] nombresJugadores) {
        for (int i = 0; i < jugadores.length; i ++) {
            escribeMensaje(nombresJugadores[jugadores[i]] + ", puntos: " + puntuaciones[i]);
        }
    }
    
    public static int leerRondas(int opcion) { // En esta función especificamos el tipo de rondas que hay y lo hacemos con un switch, poniendo en cada case cuantas rondas son.
       
        int rondas = 3;
        switch (opcion) {
            case 1: 
                rondas = 3;
                break;
            case 2: 
                rondas = 5;
                break;
            case 3: 
                rondas = 10;
                break;
            case 4: 
                rondas = 20;
                break;
        }
        
        return rondas;
    }
    /** 
     * con esta función montramos el ganador, para ello recorremos el array de las puntuaciones .
     */
    public static void mostrarGanador(int[]ordenJugadores, int[] puntuaciones, String[] nombreJugadores) 
    {

        int posicionGanador = 0;
        for (int i = 0; i < puntuaciones.length; i++) {
            if (puntuaciones[i] > puntuaciones[posicionGanador]) {
                posicionGanador = i;
            }
        }
        
        escribeMensaje("El jugador ganador es el jugador" + nombreJugadores[ordenJugadores[posicionGanador]]);
    }
    /**
     * En esta función inicializamos las puntuaciones de los parcipantes, que comienzan en 0.
     */
    public static int[] inicializarPuntuaciones(int numJugadores) { 
        int[] puntuaciones = new int[numJugadores];
        for (int i = 0; i < numJugadores; i++) {
            puntuaciones[i] = 0;
        }
        return puntuaciones;
    }
    
    /**
     * Función que pide los nombres de los jugadores por teclado
     */
    public static String[] obtenerJugadores(int numJugadores) 
    {
        String[] nombreJugadores = new String[numJugadores];
        for (int i = 0; i < numJugadores; i++) {
            escribeMensaje("Introduce el nombre del jugador " + i);
            nombreJugadores[i] = leerStringDeTeclado();
        }
        return nombreJugadores;
    }
}
