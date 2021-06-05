/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package condicional;
import java.util.Scanner;
/**
 *
 * @author Hiago
 */
public class Condicional {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner read = new Scanner(System.in);
        boolean n = true;
        while(n)
        {
            System.out.println("Insert 2 numbers:");
            float n1 = read.nextFloat();
            float n2 = read.nextFloat();
            if( n1 == -1 || n2 == -1)
            {
                //n = false;
                break;
            }
            float result = (n1+n2)/2;
            System.out.println("result = "+ result);
            if(result < 7) System.out.println("You're NOT approved!");
            else if( result == 7) System.out.println("You're approved! " + result);
            else if(result == 8) System.out.println("You're approved! " + result);
            else System.out.println("Your average is higher than 8");
        }
        
        
    }
    
}
