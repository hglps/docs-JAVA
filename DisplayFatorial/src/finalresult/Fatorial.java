/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalresult;

/**
 *
 * @author Hiago
 */
public class Fatorial {
    private long num = 0;
    private long fat = 1;
    private String formula = "";
    
    public void setValue(long n){
        num = n;
        long f = 1;
        String s = "";
        for(long i=n; i>0;i--){
            f*= i;
            s += i + " x ";
        }
        s += "=";
        fat = f;
        formula = s;
    }
    
    public long resultFactorial(){
        return fat;
    }
    
    public String resultFormula(){
        return formula;
    }
}
