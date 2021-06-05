/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector;

import java.util.Arrays;

/**
 *
 * @author Hiago
 */
public class Vector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n[] = new int[3];// ou int []n = new int[3];
        n[0] = 10;
        n[1] = 20;
        n[2] = 30;
        int a[] = {10,20,30}; // ou int []a = {...];
        String mes[] = {"jan", "fev", "mar", "abr", "mai", "jun"};
        Arrays.sort(a);
        int position = Arrays.binarySearch(a,20);
        System.out.println(position);// vetor e numero
        for(int value:  a) // recebe cada valor do vetor do i=0 ate vetor.length
        {
            System.out.println(value);
        }
        
        int num[] = new int[30];
        Arrays.fill(num, 0);
    }
    
}
