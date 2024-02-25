/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Numpuz;

import java.util.*;

import java.util.*;

public class BusquedaAnchura {

    public static List<Estado> buscar(Estado inicial) {
        Queue<Estado> frontera = new LinkedList<>(); // Cola para los estados por explorar
        Set<Estado> explorados = new HashSet<>(); // Conjunto para recordar los estados ya explorados
        frontera.add(inicial);

        while (!frontera.isEmpty()) {
            Estado estado = frontera.poll(); // Sacamos el estado al frente de la cola

            // Si el estado es el objetivo, reconstruir el camino y terminar la búsqueda
            if (estado.esObjetivo()) {
                return reconstruirCamino(estado);
            }

            explorados.add(estado); // Marcamos el estado como explorado

            // Generamos los sucesores del estado actual
            for (Estado sucesor : estado.generarSucesores()) {
                // Si no ha sido explorado ni está en la frontera, lo añadimos a la frontera
                if (!explorados.contains(sucesor) && !frontera.contains(sucesor)) {
                    frontera.add(sucesor);
                }
            }
        }

        return null; // Si la frontera está vacía y no hemos encontrado solución, retornar nulo
    }

    private static List<Estado> reconstruirCamino(Estado estado) {
        List<Estado> camino = new LinkedList<>();
        while (estado != null) {
            camino.add(0, estado); // Añadimos el estado al frente de la lista para reconstruir el camino
            estado = estado.getPredecesor();
        }
        return camino; // Devolvemos el camino desde el estado inicial hasta el objetivo
    }
}
