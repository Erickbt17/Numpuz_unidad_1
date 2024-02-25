/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Numpuz;

import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Numpuz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] tableroInicial = null;

        System.out.println("Elige el modo de inicio del tablero:");
        System.out.println("1. Números al azar");
        System.out.println("2. Definir números manualmente");
        System.out.print("Selecciona una opción (1 o 2): ");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            tableroInicial = generarTableroAleatorio();
        } else if (opcion == 2) {
            tableroInicial = new int[3][3];
            System.out.println("Ingresa los números del tablero de 3x3 (usa 0 para el espacio vacío):");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tableroInicial[i][j] = scanner.nextInt();
                }
            }
        }

        Estado estadoInicial = new Estado(tableroInicial, null, 0);

        // Realizar búsqueda en anchura
        System.out.println("Buscando solución con búsqueda en anchura...");
        List<Estado> solucionAnchura = BusquedaAnchura.buscar(estadoInicial);
        if (solucionAnchura != null) {
            System.out.println("Solución encontrada en " + (solucionAnchura.size() - 1) + " movimientos.");
            for (Estado e : solucionAnchura) {
                e.imprimirTablero();
            }
        } else {
            System.out.println("No se encontró una solución con búsqueda en anchura.");
        }

        // Realizar búsqueda en profundidad
        System.out.println("\nBuscando solución con búsqueda en profundidad...");
        List<Estado> solucionProfundidad = BusquedaProfundidad.buscar(estadoInicial);
        if (solucionProfundidad != null) {
            System.out.println("Solución encontrada en " + (solucionProfundidad.size() - 1) + " movimientos.");
            for (Estado e : solucionProfundidad) {
                e.imprimirTablero();
            }
        } else {
            System.out.println("No se encontró una solución con búsqueda en profundidad.");
        }

        scanner.close();
    }

    private static int[][] generarTableroAleatorio() {
       int[] numeros = new int[9];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i;
        }

        Random random = new Random();
        for (int i = 0; i < numeros.length; i++) {
            int indexToSwap = i + random.nextInt(numeros.length - i);
            int temp = numeros[i];
            numeros[i] = numeros[indexToSwap];
            numeros[indexToSwap] = temp;
        }

        int[][] tablero = new int[3][3];
        for (int i = 0; i < numeros.length; i++) {
            tablero[i / 3][i % 3] = numeros[i];
        }
        return tablero;
    }
    
}

