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
public class Metodo {

    // metodo principal.. como o outro esta no mesmo package, pode ser chamado
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("start:");
        System.out.println(Operacao.counter(1,1000));
        /*
        *chamo a outra classe e seu metodo procurado,
        *que conta como uma "funcao"
        */
    }
    
}
