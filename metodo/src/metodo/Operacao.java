/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

/**
 *
 * @author Hiago
 */
public class Operacao {
    public static String counter(int i, int j) {
        String s = "";
        for(int aux = i; aux <= j; aux++)
        {
            s += aux + " ";
        }
        return s;
    }
    
}
