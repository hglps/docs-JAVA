package schedules;

import companypackage.Company;
import companypackage.Employee;
import java.util.ArrayList;
import java.util.Scanner;

public class SchedulesManager {

    private Scanner read = new Scanner(System.in);
    private ArrayList<Schedules> schedules = new ArrayList<Schedules>();


    private String returnValidType(){
        String entry;
        while(true){
            entry = read.nextLine();
            if (entry.equalsIgnoreCase("quit")) break;
            else if(!entry.equalsIgnoreCase("m") && !entry.equalsIgnoreCase("s"))
                System.out.println("\nInvalid input! Expecting only 'm' or 's' input! Try again!");
            else break;
        }
        return entry;
    }
    private String returnValidDateSalaried(){
        String entry;
        boolean exist = false;
        String[] days = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25"};
        while(true){
            entry = read.nextLine();
            for(int i=0;i<days.length;i++){
                if(entry.equals(days[i])) {
                    exist = true;
                    break;
                }
                else if(entry.equalsIgnoreCase("quit"))
                    exist = true;
            }
            if(exist)
                break;
            else
                System.out.print("\nInvalid input! Expecting day as '00' - '25' or '00' for last business day! Try again! Entry: ");
        }
        return entry;
    }
    private String returnValidWeeks(){
        String entry;
        while(true){
            entry = read.nextLine();
            if (entry.equalsIgnoreCase("quit")) break;
            else if(!entry.equalsIgnoreCase("01") && !entry.equalsIgnoreCase("02"))
                System.out.print("\nInvalid input! Expecting only '01' or '02' weeks! Try again!\nEntry: ");
            else break;
        }
        return entry;
    }
    private String returnValidDayWeek(){
        String entry;
        boolean exist = false;
        String[] days = new String[]{"0","1","2","3","4","5","6"};
        while(true){
            entry = read.nextLine();
            for(int i=0;i<days.length;i++){
                if(entry.equals(days[i])){
                    exist = true;
                    break;
                }
                else if(entry.equalsIgnoreCase("quit"))
                    exist = true;
            }
            if(exist)
                break;
            else
                System.out.print("\nInvalid input! Expecting '0' to Monday, '1' to Tuesday,... '6' ! Try again!\n Entry: ");
        }
        return entry;
    }

    public void createSchedules() {
        read.nextLine();
        String schedule = "";
        String entry = "";
        while (true) {
            schedule = "";
            entry="";

            System.out.println("Insert 'quit' to go back to main screen");
            System.out.println("....Adding new payment schedules....\n");
            System.out.print("Insert type:\nm - monthly  /  s - weekly\n->");
            entry = returnValidType();
            if(entry.equalsIgnoreCase("quit"))
                break;

            schedule += entry;
            entry="";

            if (schedule.equals("m")) {
                System.out.print("Insert a number for the day of payment:\nNumber format: '01' - '25' or '00' to last business day\nEntry: ");
                entry = returnValidDateSalaried();

                if (entry.equalsIgnoreCase("quit")) break;
                schedule += " " + entry;
                entry="";
            }

            else if (schedule.equals("s")) {
                System.out.println("Insert number of worked weeks required: 01 or 02");
                entry = returnValidWeeks();

                if (entry.equalsIgnoreCase("quit")) break;
                schedule += " " + entry;
                entry="";

                System.out.println("Insert the day of week:\n" +
                        "0 - Monday\n" +
                        "1 - Tuesday\n" +
                        "2 - Wednesday\n" +
                        "3 - Thursday\n" +
                        "4 - Friday\n" +
                        "5 - Saturday\n" +
                        "6 - Sunday");
                entry = returnValidDayWeek();
                if (entry.equalsIgnoreCase("quit")) break;
                schedule += " " + entry;
                entry="";
            }
            Schedules scheduleFinal = new Schedules(schedule);

            this.schedules.add(scheduleFinal);
            System.out.println("Schedule: -" + schedule + "- set!");
        }
    }

    public boolean setNewPayday(String typePayment, String payday, Employee currEmployee){
        ArrayList<Integer> quantity = new ArrayList<Integer>();

        for(int i=0;i< schedules.size();i++){
            if(typePayment.equalsIgnoreCase("h") && schedules.get(i).getSchedule().substring(0,4).equals("s 01")) {
                System.out.println(i + " - " + schedules.get(i).getSchedule());
                quantity.add(i);
            }
            else if(typePayment.equalsIgnoreCase("c") && schedules.get(i).getSchedule().substring(0,4).equals("s 02")) {
                System.out.println(i + " - " + schedules.get(i).getSchedule());
                quantity.add(i);
            }
            else if(typePayment.equalsIgnoreCase("s") && schedules.get(i).getSchedule().substring(0,1).equals("m")) {
                System.out.println(i + " - " + schedules.get(i).getSchedule());
                quantity.add(i);
            }
        }

        System.out.println("If you want to change your payment schedule, select one valid shown number\nElse, insert 'over' to go back to main screen");
        String opt = read.nextLine();
        if (opt.equalsIgnoreCase("over")) {
            System.out.println("Back to main screen.\n-----------------------------------------------------------------------");
            return false;
        }
        else {
              int option = returnValidOption(opt, quantity);
              if(option != -1) {
                  currEmployee.setPayday(schedules.get(option).getSchedule());
                  System.out.println("New schedule -"+ currEmployee.getPayday()+"- registered to employee " + currEmployee.getName());
                  return true;
              }
              else {
                  System.out.println("Changes not made! Payday of " + currEmployee.getName() + " did not change!");
                  return false;
              }
        }

    }

    private int returnValidOption(String opt, ArrayList<Integer> quantity){
        boolean error = true;
        int option = -1;
        boolean exist = false;
        while (error) {
            try {
                option = Integer.parseInt(opt);
                error = false;
            }
            catch (Exception e) {
                System.out.println("INVALID INPUT! Expected integer number! Try again!");
                error = true;
            }
            for(int i=0;i< quantity.size();i++){
                if(option == quantity.get(i)){
                    error = false;
                    exist = true;
                    break;
                }
                else if(i == quantity.size()-1) {
                    option = -1;
                    error = false;
                    exist = true;
                }
            }
            if(exist)
                break;

        }
        return option;
    }



}
