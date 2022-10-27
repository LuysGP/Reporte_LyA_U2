package infijopostfijo;

import java.util.Scanner;
import java.util.Stack;

public class InfijoPostFijo {

    //Depurar expresion algebraica
    static String depurar(String s){
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";
        
        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++){
            if(simbols.contains("" + s.charAt(i))){
                str += " " + s.charAt(i) + " ";
            } else {
                str += s.charAt(i);
            }
        }
        return str.replaceAll(("\\s+"), " ").trim();
    }
    
        //Jerarquia de los operadores    //Jerarquia de los operadores
    private static int pref(String op){
        int prf = 99;
        if (op.equals("^")) prf = 5;
        if (op.equals("*") || op.equals("/")) prf = 4;
        if (op.equals("+") || op.equals("-")) prf = 3;
        if (op.equals(")")) prf = 2;
        if (op.equals("(")) prf = 1;
        return prf;
    }


    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        //Entrada de datos
        System.out.println("Escribe una expresion algebraica:");
        String entrada = leer.next();

        //Depurar la expresion algebraica
        String expr = depurar(entrada);
        String[] arrayInfix = expr.split(" ");
        
        //Declaracion de las pilas
        Stack <String> E = new Stack <String>(); //Pila Entrada
        Stack <String> P = new Stack <String>(); //Pila Temporal para 
        Stack <String> S = new Stack <String>(); //Pila Salida
        
        //Añadir el array a la Pila de entrada (E)
        for(int i = arrayInfix.length - 1; i >= 0; i--){
            E.push(arrayInfix[i]);
        }
        
        try{
            while (!E.isEmpty()){
                switch(pref(E.peek())){
                    case 1:
                        P.push(E.pop());
                        break;
                    case 3:
                    case 4:
                        while(pref(P.peek()) >= pref(E.peek())){
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;
                    case 2:
                        while(!P.peek().equals("(")){
                            S.push(P.pop());
                        }
                        P.pop();
                        E.pop();
                        break;
                    default:
                        S.push(E.pop());
                        break;
                }
            }
            
            //Eliminacion de 'impureza' en la expresiones algebraicas.
            String infix = expr.replace(" ", "");
            String postfix = S.toString().replaceAll("[\\]\\[,]", "");
            
            //Mostrar resultados
            System.out.println("Expresion Infija: " + infix);
            System.out.println("Expresion Postfija: " + postfix);
        } catch(Exception ex){
            System.out.println("Error en la expresion algebraica");
            System.err.println(ex);
        }
        
    }
    
}
