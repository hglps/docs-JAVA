package com;

import java.util.ArrayList;

public class TesteDasNotasDosProjetos {

    public static void main(String args[]) {

        int i=0, j=0;
        double x,y;
        String texto = "";
        double result = 0;
        ArrayList<String> lista = new ArrayList<>();

        for(i=0; i<= 100; i++){
            for(j=0;j <= 100; j++){
                if(i % 5 == 0 && j % 5 == 0) {
                    x = i;
                    y = j;
                    result = x * 0.3 + y * 0.7; // 94 95.5 //
                    if (result > 91.5 && result <= 92.9) {
                        //x = i;
                        //y = j;
                        texto = "java= " + (x / 10) + " ; OO= " + (y / 10) + " ; nota final= " + (result / 10);
                        lista.add(texto);
                    }
                    result = 0;
                }
            }
        }

        for( String z : lista)
            System.out.println(z);

    }
}
