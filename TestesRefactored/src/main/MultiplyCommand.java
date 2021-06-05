package main;

public class MultiplyCommand implements Operation{

    public long apply(long a, long b){
        System.out.println("Returning from MultiplyCommand:");
        return a*b;
    }

}
