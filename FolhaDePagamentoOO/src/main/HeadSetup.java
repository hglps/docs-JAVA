package main;

import calendar.MyCalendar;
import companypackage.Company;
import java.util.Scanner;

public class HeadSetup {
    private Scanner read = new Scanner(System.in);
    private Company company = new Company();
    private String password = "admin";
    private MyCalendar date = new MyCalendar();

    public void startSystem(){
        System.out.println("Started system!");
        date.setLastWorkDay();
        enterSystem();
    }

    private void screenEnterPassword(){
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("||Insert password to access payroll system or insert 'quit' to quit the system:||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.print("Password: ");
    }
    private int returnValidInteger(){
        boolean error = true;
        int choice = -1;
        while(error){
            try{
                choice = read.nextInt();
                error = false;
            }
            catch(Exception e){
                System.out.println("INVALID INPUT! Expecting an integer value! Try again!");
                visualizeOptions();
                read.nextLine();
                error = true;
            }
        }
        return choice;
    }

    private void enterSystem(){
        String reading;

        while(true){
            screenEnterPassword();
            reading = read.nextLine();
            if(reading.equals(password)){
                payrollSystem();
            }
            else if(reading.equalsIgnoreCase("quit")){
                screenSystemFinished();
                break;
            }
            else if(!reading.equalsIgnoreCase("quit"))
                System.out.println("Invalid input! Try again!");
        }
    }
    private void payrollSystem(){
        company.startUndoRedo();
        visualizeOptions();
        while(true){

            int choice = returnValidInteger();

            if(choice == -1){
                System.out.println("\n\nPayroll system finished\n");
                read.nextLine();
                break;
            }
            else if(choice == 1){
                company.addEmployee();
            }
            else if(choice == 2){
                company.deleteEmployee();
            }
            else if (choice == 3){
                company.setTimeCheck();
            }
            else if(choice == 4){
                company.resultSales();
            }
            else if(choice == 5){
                company.serviceFee();
            }
            else if(choice == 6){
                company.changeRegister();
            }
            else if(choice == 7) {
                company.undoRedo();
            }
            else if(choice == 8){
                runPayroll();
            }
            else if(choice == 9){
                company.setNewPayday();
            }
            else if(choice == 10){
                company.createSchedules();
            }
            else if(choice == 11){
                company.showInfo();
            }
            else if(choice == 12){
                changeSystemPassword();
            }
            else if(choice == 13){
                company.showStates();
            }
            else
                System.out.println("Invalid option!\nPlease, insert a valid option:\n");
            visualizeOptions();
        }
    }

    private void runPayroll(){
        read.nextLine();
        screenWarningPayroll();
        String enter = read.nextLine();
        if (enter.equalsIgnoreCase("yes")){
            company.payroll(date);
            if(date.setNewDate())
                company.resetPaidUnion();
        }
        else
            System.out.println("Back to main screen");
    }

    private void changeSystemPassword(){
        read.nextLine();
        System.out.println("Insert the current password:");
        String currentPasswordCheck = read.nextLine();
        if (currentPasswordCheck.equals(password)) {
            System.out.println("OK, now insert the new password:");
            password = read.nextLine();
            System.out.println("The new password is " + password);
        } else
            System.out.println("Wrong password!");
    }
    private void screenWarningPayroll(){
        System.out.println("Warning!! Be aware that a day is passed after the payroll execution!");
        System.out.println("If you want to proceed, enter 'yes' or anything else to go back to main screen");
    }
    private void visualizeOptions() {
        date.getDate();
        System.out.println("\n------------------------------------------------------------\n" +
                           "Insert 1 to ADD a new employee;");
        System.out.println("Insert 2 to REMOVE an employee;");
        System.out.println("Insert 3 to UPDATE POINT CARD of an employee;");
        System.out.println("Insert 4 to UPDATE THE RESULT OF SALES of an employee;");
        System.out.println("Insert 5 to UPDATE SERVICE FEE of an employee;");
        System.out.println("Insert 6 to CHANGE REGISTER of an employee;");
        System.out.println("Insert 7 to UNDO or REDO!");
        System.out.println("Insert 8 to RUN TODAY'S PAYROLL;");
        System.out.println("Insert 9 to SET PAYMENT SCHEDULE of an employee;");
        System.out.println("Insert 10 to CREATE NEW PAYMENT SCHEDULES;");
        System.out.println("Insert 11 to LIST EMPLOYEE(S) INFO;");
        System.out.println("Insert 12 to change password of system;");
        System.out.println("Insert -1 to end;");
        System.out.print("Option:  ");

    }
    private void screenSystemFinished(){
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||System completely finished!|||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
    }


}
