/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolBinario;

import java.util.Scanner;

class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

class ArbolBinarioDeBusqueda1 {
    Nodo raiz;

    ArbolBinarioDeBusqueda1() {
        raiz = null;
    }

    void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    Nodo insertarRecursivo(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.izquierdo = insertarRecursivo(raiz.izquierdo, valor);
        } else if (valor > raiz.valor) {
            raiz.derecho = insertarRecursivo(raiz.derecho, valor);
        }

        return raiz;
    }

    void imprimirArbol() {
        imprimirInOrden(raiz);
    }

    void imprimirInOrden(Nodo raiz) {
        if (raiz != null) {
            imprimirInOrden(raiz.izquierdo);
            System.out.print(raiz.valor + " ");
            imprimirInOrden(raiz.derecho);
        }
    }
}

public class ArbolBinarioDeBusqueda {
    public static void main(String[] args) {
        ArbolBinarioDeBusqueda1 arbol = new ArbolBinarioDeBusqueda1();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese el número de elementos que desea agregar al árbol:");
        int cantidad = scanner.nextInt();
        
        System.out.println("Ahora ingrese los números que desea agregar al árbol:");
        for(int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese el número #" + (i + 1) + ": ");
            int num = scanner.nextInt();
            arbol.insertar(num);
        }
        
        System.out.println("Los números ingresados, ordenados, son:");
        arbol.imprimirArbol();
        
        scanner.close();
    }
}

