
package infija_posfija;


public class AnalizadorExpresion {
    public static boolean verificarBalance(String expresion){
        int balance=0;
        for (int i =0; i< expresion.length(); i++){
            char caracter = expresion.charAt(i);
            if (caracter == '(') {
                balance++;
            } else if (caracter == ')') {
                balance--;
            }
            if (balance < 0) {
                return false; 
            }
        }
        return balance == 0;  
    }
}
