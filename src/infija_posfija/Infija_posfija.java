package infija_posfija;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Infija_posfija {
    private static String expresionMatematica;
    private static String mensaje;
    private static String expresionPosfija;

    public static String getExpresionPosfija() {
        return expresionPosfija;
    }

    public static void setExpresionPosfija(String expresionPosfija) {
        Infija_posfija.expresionPosfija = expresionPosfija;
        System.out.println("Expresión posfija: " + expresionPosfija); // Mostrar por consola la expresión posfija
    }
    
    private static final Map<Character, Integer> precedencia = new HashMap<>();
    
static {
    precedencia.put('+', 1);
    precedencia.put('-', 1);
    precedencia.put('*', 2);
    precedencia.put('/', 2);
    precedencia.put('(', 0);
    precedencia.put(' ', 0); // Agregar una entrada para el espacio en blanco
}

    public static String getMensaje() {
        return mensaje;
    }

    public static void setMensaje(String mensaje) {
        Infija_posfija.mensaje = mensaje;
    }

    public static String getExpresionMatematica() {
        return expresionMatematica;   
    }

public static String setExpresionMatematica(String expresion) {
    expresionMatematica = expresion;
    boolean balance = AnalizadorExpresion.verificarBalance(expresionMatematica);

    if (balance) {
        mensaje = "La expresión está balanceada.";
        expresionPosfija = convertirInfijaAPosfija(expresionMatematica);
        
        // Mostrar la ventana de resultado_de_balanceado solo si la expresión está balanceada
        resultado_de_balanceado resultado = new resultado_de_balanceado();
        resultado.setVisible(true);
    } else {
        mensaje = "La expresión no está balanceada.";
        expresionPosfija = "";
    }

    return mensaje;
}

    public static double resolverExpresionPosfija(String expresionPosfija) {
    lista_enlazada lista = new lista_enlazada();

    for (char c : expresionPosfija.toCharArray()) {
        if (Character.isDigit(c)) {
            lista.insertarAlFinal(c); // Insertar los operandos en la lista
        } else {
            double operand2 = Character.getNumericValue(lista.eliminarAlFinal());
            double operand1 = Character.getNumericValue(lista.eliminarAlFinal());
            switch (c) {
                case '+':
                    lista.insertarAlFinal((char) (operand1 + operand2 + '0'));
                    break;
                case '-':
                    lista.insertarAlFinal((char) (operand1 - operand2 + '0'));
                    break;
                case '*':
                    lista.insertarAlFinal((char) (operand1 * operand2 + '0'));
                    break;
                case '/':
                    lista.insertarAlFinal((char) (operand1 / operand2 + '0'));
                    break;
                // Agrega casos para otros operadores si es necesario
            }
        }
    }
    // Al final, la lista contendrá solo un elemento que es el resultado final
    return Character.getNumericValue(lista.eliminarAlFinal());
}



    public static String convertirInfijaAPosfija(String expresionInfija) {
    StringBuilder expresionPosfija1 = new StringBuilder();
    lista_enlazada lista = new lista_enlazada();

    for (char caracter : expresionInfija.toCharArray()) {
        if (caracter == ' ') {
            continue; // Ignorar los espacios en blanco
        }
        if (Character.isLetterOrDigit(caracter)) {
            expresionPosfija1.append(caracter); // Agrega operandos a la cadena de salida
        } else if (caracter == '(') {
            lista.insertarAlFinal(caracter); // Agrega el paréntesis de apertura '(' a la lista
        } else if (caracter == ')') {
            while (!lista.estaVacia() && lista.obtenerUltimo() != '(') {
                expresionPosfija1.append(lista.eliminarAlFinal()); // Saca operadores de la lista y agrégalos a la cadena de salida hasta encontrar '('
            }
            lista.eliminarAlFinal(); // Elimina el paréntesis de apertura '(' de la lista
        } else {
            while (!lista.estaVacia() && precedencia.get(caracter) <= precedencia.get(lista.obtenerUltimo())) {
                expresionPosfija1.append(lista.eliminarAlFinal()); // Saca operadores de la lista y agrégalos a la cadena de salida
            }
            lista.insertarAlFinal(caracter); // Agrega el operador actual a la lista
        }
    }

    while (!lista.estaVacia()) {
        expresionPosfija1.append(lista.eliminarAlFinal()); // Saca los operadores restantes de la lista y agrégalos a la cadena de salida
    }

    // Agrega esta línea para imprimir la expresión posfija por consola
    System.out.println("Expresión posfija: " + expresionPosfija1.toString());

    return expresionPosfija1.toString();
}
}