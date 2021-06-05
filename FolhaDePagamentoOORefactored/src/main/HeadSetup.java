package main;

import calendar.MyCalendar;
import choicesmenu.ChoiceFactory;
import companypackage.Company;
import companypackage.Employee;
import companypackage.Hourly;

import java.util.Scanner;

public class HeadSetup {
    private Scanner read = new Scanner(System.in);
    private Company company = new Company();
    private String password = "admin";
    //private MyCalendar date = new MyCalendar();

    protected void startSystem(){
        System.out.println("Started system!");
        //date.setLastWorkDay();
        //enterSystem();
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


    private void payrollSystem(){
        //company.startUndoRedo(); // *****************
        visualizeOptions();
        Employee a = new Employee("a", "b", "c", "d");
        Hourly b = new Hourly("g", "H,", "i", "j", 8948);
        String b = "Hourly"
        if(a.getClass())
            System.out.println("deu ruiiiim");
//        while(true){
//
//            String choice;
//            choice = read.nextLine();
//            if(choice.equalsIgnoreCase("quit")){
//                System.out.println("\n\nPayroll system finished\n");
//                //read.nextLine();
//                break;
//            }
//            else factory.getOperation(choice);
//
//            visualizeOptions();
//        }
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
                           "Insert 'add' to ADD a new employee;");
        System.out.println("Insert 'delete' to REMOVE an employee;");
        System.out.println("Insert 'card' to UPDATE POINT CARD of an employee;");
        System.out.println("Insert 'new sale' to UPDATE THE RESULT OF SALES of an employee;");
        System.out.println("Insert 'service fee' to UPDATE SERVICE FEE of an employee;");
        System.out.println("Insert 'change register' to CHANGE REGISTER of an employee;");
        System.out.println("Insert 'undo' to UNDO!");
        System.out.println("Insert 'redo' to REDO!");
        System.out.println("Insert 'payroll' to RUN TODAY'S PAYROLL;");
        System.out.println("Insert 'set payday' to SET PAYMENT SCHEDULE of an employee;");
        System.out.println("Insert 'create schedule' to CREATE NEW PAYMENT SCHEDULES;");
        System.out.println("Insert 'info' to LIST EMPLOYEE(S) INFO;");
        System.out.println("Insert 'new password' to change password of system;");
        System.out.println("Insert 'quit' to end;");
        System.out.print("Option:  ");

    }
    private void screenSystemFinished(){
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||System completely finished!|||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
    }


}
