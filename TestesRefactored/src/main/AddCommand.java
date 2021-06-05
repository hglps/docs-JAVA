package main;

public class AddCommand implements Operation{

    public long apply(long a, long b){
        System.out.println("Returning from AddCommand:");
        return a+b;
    }
}
