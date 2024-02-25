/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Numpuz;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class BusquedaProfundidad {

    public static List<Estado> buscar(Estado inicial) {
        Stack<Estado> frontera = new Stack<>();
        Map<Estado, Estado> camino = new HashMap<>();
        Set<Estado> explorados = new HashSet<>();

        frontera.push(inicial);
        camino.put(inicial, null);

        while (!frontera.isEmpty()) {
            Estado actual = frontera.pop();

            if (actual.esObjetivo()) {
                return construirCamino(actual, camino);
            }

            explorados.add(actual);

            for (Estado hijo : actual.generarSucesores()) {
                if (!explorados.contains(hijo) && !frontera.contains(hijo)) {
                    frontera.push(hijo);
                    camino.put(hijo, actual);
                }
            }
        }

        return null; // No se encontró solución
    }

    private static List<Estado> construirCamino(Estado objetivo, Map<Estado, Estado> camino) {
        LinkedList<Estado> solucion = new LinkedList<>();
        Estado estado = objetivo;
        while (estado != null) {
            solucion.addFirst(estado);
            estado = camino.get(estado);
        }
        return solucion;
    }
}
