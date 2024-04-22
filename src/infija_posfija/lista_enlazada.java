
package infija_posfija;


import java.util.NoSuchElementException;

public class lista_enlazada {
    private Nodo primero;
    private Nodo ultimo;

    public lista_enlazada() {
        this.primero = null;
        this.ultimo = null;
    }
    

    public void insertarAlFinal(char dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (this.primero == null) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            this.ultimo.siguiente = nuevoNodo;
            this.ultimo = nuevoNodo;
        }
    }

    public char eliminarAlFinal() {
        if (this.primero == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        char datoEliminado;
        if (this.primero == this.ultimo) {
            datoEliminado = this.primero.dato;
            this.primero = null;
            this.ultimo = null;
        } else {
            Nodo actual = this.primero;
            while (actual.siguiente != this.ultimo) {
                actual = actual.siguiente;
            }
            datoEliminado = this.ultimo.dato;
            actual.siguiente = null;
            this.ultimo = actual;
        }
        return datoEliminado;
    }

    public boolean estaVacia() {
        return this.primero == null;
    }

    public char obtenerUltimo() {
        if (this.ultimo == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return this.ultimo.dato;
    }
}
