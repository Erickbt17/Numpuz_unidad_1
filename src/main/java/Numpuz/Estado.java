/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Numpuz;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Estado {
    private int[][] tablero;
    private int vacioX, vacioY; // Posición del espacio vacío
    private Estado predecesor;
    private int movimientos;

    public Estado(int[][] tablero, Estado predecesor, int movimientos) {
        this.tablero = new int[3][3];
        this.predecesor = predecesor;
        this.movimientos = movimientos;
        for (int i = 0; i < 3; i++) {
            System.arraycopy(tablero[i], 0, this.tablero[i], 0, tablero[i].length);
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == 0) { // 0 es el espacio vacío
                    vacioX = i;
                    vacioY = j;
                }
            }
        }
    }

    public List<Estado> generarSucesores() {
        List<Estado> sucesores = new ArrayList<>();
        int[][] posiblesMovimientos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Arriba, abajo, izquierda, derecha

        for (int[] movimiento : posiblesMovimientos) {
            int nuevoX = vacioX + movimiento[0];
            int nuevoY = vacioY + movimiento[1];
            if (nuevoX >= 0 && nuevoX < 3 && nuevoY >= 0 && nuevoY < 3) {
                int[][] nuevoTablero = clonarTablero(tablero);
                nuevoTablero[vacioX][vacioY] = nuevoTablero[nuevoX][nuevoY];
                nuevoTablero[nuevoX][nuevoY] = 0;
                sucesores.add(new Estado(nuevoTablero, this, movimientos + 1));
            }
        }
        return sucesores;
    }

    private int[][] clonarTablero(int[][] original) {
        int[][] copia = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, original[i].length);
        }
        return copia;
    }

    public boolean esObjetivo() {
        int contador = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] != contador && !(i == 2 && j == 2)) {
                    return false;
                }
                contador = (contador == 8) ? 0 : contador + 1;
            }
        }
        return true;
    }

    public Estado getPredecesor() {
        return predecesor;
    }

    public int getMovimientos() {
        return movimientos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estado)) return false;
        Estado estado = (Estado) o;
        return Arrays.deepEquals(tablero, estado.tablero);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tablero);
    }

    public void imprimirTablero() {
    for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
            System.out.print(tablero[i][j] + " ");
        }
        System.out.println();
    }
    System.out.println("Movimientos: " + movimientos);
    System.out.println();
}
}

