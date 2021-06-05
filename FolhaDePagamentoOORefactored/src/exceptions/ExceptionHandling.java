package exceptions;

import java.util.Scanner;

public class ExceptionHandling {
    private Scanner read = new Scanner(System.in);

    public double returnValidDouble(){
        double value = -1;
        boolean error = true;
        while(error) {
            try {
                value = read.nextDouble();
                error = false;
            } catch (Exception e) {
                System.out.print("INVALID INPUT! Expected a float value!\nNumber format: 9999,99. Try again!\nR$");
                read.nextLine();
                error = true;
            }
            if (value <= 0) {
                System.out.println("Expecting value greater than zero! Try again!");
                error = true;
            }
        }
        return value;
    }
    public double returnValidRate(){
        boolean error = true;
        double commissionRate = -1;
        while(error && (commissionRate < 0.01 || commissionRate > 0.99)){
            try{
                commissionRate = read.nextDouble();
                error = false;
            }
            catch(Exception e){
                System.out.print("Invalid INPUT! Expected float value!\nNumber format: 0,01 to 0,99. Try again!\nRate: ");
                read.nextLine();
                error = true;
            }
            if (commissionRate < 0.01 || commissionRate > 0.99) {
                System.out.println("Invalid input! Insert again! (Input: from 0,01 to 0,99)");
                error = true;
            }
            else break;
        }
        return commissionRate;
    }
    public String returnValidTypePayment(){
        String typePayment;
        while(true){
            typePayment = read.nextLine();
            if(!typePayment.equalsIgnoreCase("h" ) && !typePayment.equalsIgnoreCase("s") && !typePayment.equalsIgnoreCase("c"))
                System.out.println("Invalid input! Only accepting 'h', 's' or 'c'");
            else break;
        }
        return typePayment;
    }
    public String returnValidWayPayment(){
        String wayPayment;
        while(true){
            wayPayment = read.nextLine();
            if(!wayPayment.equals("check via mail") && !wayPayment.equals("check in hands") && !wayPayment.equalsIgnoreCase("deposit"))
                System.out.println("Invalid input!");
            else break;
        }
        return wayPayment;
    }
    public boolean returnValidPartOfUnion(){
        String partOfUnion;
        while(true){
            partOfUnion = read.nextLine();
            if(!partOfUnion.equalsIgnoreCase("yes") && !partOfUnion.equalsIgnoreCase("no"))
                System.out.println("Invalid input! Expecting only 'yes' or 'no' input! Try again!");
            else break;
        }
        return partOfUnion.equalsIgnoreCase("yes");
    }
    public int returnValidChangeRegisterChoice(){
        boolean error = true;
        int choice = -1;
        while(error){
            try{
                choice = read.nextInt();
                error = false;
            }
            catch(Exception e){
                System.out.print("INVALID INPUT! Expecting an integer value! Try again!\nChoice: ");
                read.nextLine();
                error = true;
            }
            if(choice < 0 || choice > 6){
                System.out.print("Invalid entry! Expecting values from 0 to 6! Try again! Choice:");
                error = true;
            }
            else break;
        }
        return choice;
    }
    public int returnValidShowInfo(){
        boolean error = true;
        int choice = -1;
        while(error){
            try{
                choice = read.nextInt();
                error = false;
            }
            catch(Exception e){
                System.out.print("INVALID INPUT! Expecting an integer value! Try again!\nChoice: ");
                read.nextLine();
                error = true;
            }
            if(choice < 1 || choice > 2){
                System.out.print("Invalid entry! Expecting values as '1' or '2' ! Try again! Choice:");
                error = true;
            }
            else break;
        }
        return choice;
    }
    public int returnValidHour(){
        int hour = -1;
        boolean error = true;
        while ((hour < 0 || hour > 23) && error ) {
            try {
                hour = read.nextInt();
                error = false;
            }
            catch(Exception e){
                System.out.println("Invalid input ! Expecting integer value! Try again!");
                read.nextLine();
                error = true;
            }
            if (hour < 0 || hour > 23) {
                System.out.println("\nExpecting hour between 0 and 23! Insert again!\n");
                error = true;
            }
            else break;
        }
        return hour;
    }
    public int returnValidMinute(){
        int minute = -1;
        boolean error = true;
        while ((minute < 0 || minute > 59) && error) {
            System.out.println("Insert minute of entrance: (Number format: 0 to 59");
            try {
                minute = read.nextInt();
                error = false;
            }
            catch(Exception e){
                System.out.println("Expecting integer value! Try again!");
                read.nextLine();
                error = true;
            }
            if (minute < 0 || minute > 59) {
                System.out.println("\nInvalid input! Insert again!\n");
                error = true;
            }
            else break;
        }
        return minute;
    }
}
