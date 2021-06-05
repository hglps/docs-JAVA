package main;
import java.util.Scanner;
public class Calculator {

    private Scanner read = new Scanner(System.in);
    public void startCode(){
            String entry;
            long numberOne, numberTwo;

            System.out.println("Enter command: 'add' or 'multiply'");
            entry = read.nextLine();

            System.out.println("Enter 2 numbers:");
            numberOne = read.nextLong();
            numberTwo = read.nextLong();
            System.out.println(calculateUsingFactory(numberOne, numberTwo, entry));

    }

    private long calculateUsingFactory(long a, long b, String operator){
        OperatorFactory factory = new OperatorFactory();
        Operation targetOperation = factory.getOperation(operator);

        return targetOperation.apply(a,b);
    }


}
