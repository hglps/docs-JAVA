package companypackage;

import calendar.MyCalendar;
import schedules.SchedulesManager;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Company {
    private Scanner read = new Scanner(System.in);
    private final int patternId = 19002100;
    private final int maxSize = 500;

    private ArrayList<Employee> employees;// = new ArrayList<Employee>(maxSize);
    private SchedulesManager schedulesManager = new SchedulesManager();
    private Stack<ArrayList<Employee>> undo= new Stack<ArrayList<Employee>>();
    private Stack<ArrayList<Employee>> redo = new Stack<ArrayList<Employee>>();

//    /*
//     * ID = patternID + @ + name.substring(0,3)
//     * */

    private int getIndex(String id){
        for(int i=0; i < employees.size() ; i++){
            if (employees.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    private String getId(int index){
        if(employees.get(index).getName().length() < 3){
            return (patternId + index) + "@" + employees.get(index).getName();
        }
        else
            return (patternId + index) + "@" + employees.get(index).getName().substring(0,3);
    }


    private void setUnionCondition(double unionFee, boolean unionMember, int index) {
        if(unionMember){
            employees.get(index).enterUnion(unionFee, employees.get(index).getName(), patternId);
        }
        else
            employees.get(index).leaveUnion();
    }
    private void setUnionCondition( double unionFee, boolean unionMember, Employee employee){ // used in change of type of payment case
        if(unionMember)
            employee.enterUnion(unionFee, employee.getName(), patternId);
    }

    private double returnValidDouble(){
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
    private double returnValidRate(){
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
    private String returnValidTypePayment(){
        String typePayment;
        while(true){
            typePayment = read.nextLine();
            if(!typePayment.equalsIgnoreCase("h" ) && !typePayment.equalsIgnoreCase("s") && !typePayment.equalsIgnoreCase("c"))
                System.out.println("Invalid input! Only accepting 'h', 's' or 'c'");
            else break;
        }
        return typePayment;
    }
    private String returnValidWayPayment(){
        String wayPayment;
        while(true){
            wayPayment = read.nextLine();
            if(!wayPayment.equals("check via mail") && !wayPayment.equals("check in hands") && !wayPayment.equalsIgnoreCase("deposit"))
                System.out.println("Invalid input!");
            else break;
        }
        return wayPayment;
    }
    private boolean returnValidPartOfUnion(){
        String partOfUnion;
        while(true){
            partOfUnion = read.nextLine();
            if(!partOfUnion.equalsIgnoreCase("yes") && !partOfUnion.equalsIgnoreCase("no"))
                System.out.println("Invalid input! Expecting only 'yes' or 'no' input! Try again!");
            else break;
        }
        return partOfUnion.equalsIgnoreCase("yes");
    }
    private int returnValidChangeRegisterChoice(){
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
    private int returnValidShowInfo(){
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
    private int returnValidHour(){
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
    private int returnValidMinute(){
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

    public void addEmployee() {
        read.nextLine();
        String name, address, typePayment , wayPayment;
        boolean unionMember;
        double baseSalary= -1, hourlyRate= -1, unionFee = -1, commissionRate= -1;

        System.out.println("/////////////////////////////////////////////////////////////////");
        System.out.print("Insert your name (text entry): ");
        name = read.nextLine();
        System.out.print("Insert your address (text entry): "); address = read.nextLine();
        System.out.print("Insert the type of payment:\n" +
                         "Entry format:\nh - hourly / s - salaried / c - commissioned\nType of payment: ");
        typePayment = returnValidTypePayment();

        if(typePayment.equals("s") || typePayment.equals("c")){
            System.out.print("Insert base salary:\nNumber format: 9999,99\nR$ ");
            baseSalary = returnValidDouble();

            if(typePayment.equals("c")) {
                System.out.print("Insert commission rate:\nNumber format: 0,01 to 0,99\nRate: ");
                commissionRate = returnValidRate();
            }
            read.nextLine();
        }
        else if(typePayment.equals("h")){
            System.out.print("Insert the hourly rate for this employee:\nNumber format: 9999,99\nR$");
            hourlyRate = returnValidDouble();
            read.nextLine();
        }

        System.out.println("Insert how you want to get paid:\n" +
                           "Entry format:\n'check via mail' - check via mail  /  'check in hands' - check via hands  /  'deposit' - pay via deposit");
        wayPayment = returnValidWayPayment();

        System.out.println("Are you part of any union?\n" +
                "Entry format:\n 'yes' or 'no'\nAnswer:");
        unionMember = returnValidPartOfUnion();

        if(unionMember){
                System.out.print("Then, insert the union fee:\nNumber format: 9999,99\n R$ ");
                unionFee = returnValidDouble();
        }

        Employee newEmployee = null;
        int index;
        String id;

        if(typePayment.equalsIgnoreCase("h"))
            newEmployee = new Hourly(name,address,typePayment.toLowerCase(), wayPayment.toLowerCase(), hourlyRate );

        else if(typePayment.equalsIgnoreCase("s"))
            newEmployee = new Salaried(name, address, typePayment.toLowerCase(), wayPayment.toLowerCase(), baseSalary);

        else if(typePayment.equalsIgnoreCase("c"))
            newEmployee = new Commissioned(name,address, typePayment.toLowerCase(), wayPayment.toLowerCase(), baseSalary, commissionRate);

        employees.add(newEmployee);
        index = employees.indexOf(newEmployee);
        id = getId(index);
        employees.get(index).setId(id);
        employees.get(index).startPayday();
        setUnionCondition(unionFee,unionMember, index);

        screenWelcomeNewEmployee(index);

        saveState();

    }
    private void screenWelcomeNewEmployee(int index) {
        if( employees.get(index) instanceof Hourly)
            System.out.println("Welcome hourly employee " + employees.get(index).getName() + "!\nID: " + employees.get(index).getId());
        else if(employees.get(index) instanceof Commissioned)
            System.out.println("Welcome commissioned employee " + employees.get(index).getName() + "!\nID: " + employees.get(index).getId());
        else if(employees.get(index) instanceof  Salaried)
            System.out.println("Welcome salaried employee " + employees.get(index).getName() + "!\nID: " + employees.get(index).getId());

    }// colocar o metodo override em cada classe do tipo, e chamar o metodo da classe

    public void deleteEmployee() {
        read.nextLine();
        System.out.println("Insert ID: (text entry :: Format - '190021xx@abc' )");
        String id = read.nextLine();
        int index = getIndex(id);
        if(index != -1){
            String name = employees.get(index).getName();
            employees.remove(index);
            System.out.println("Employee " + name + "removed :)");
            saveState();
        }
        else
            System.out.println("Employee with ID : "+ id +" not found!");
    }

    public void setTimeCheck() {
        String id;
        int index;
        boolean error;
        while(true) {
            read.nextLine();
            System.out.println("Insert ID: (insert 'quit' to go back to main menu)");
            id = read.nextLine();
            index = getIndex(id);
            if(id.equalsIgnoreCase("quit")) {
                System.out.println("Back to main screen!");
                break;
            }
            else if(index == -1)
                System.out.println("Employee with ID : " + id + " not found! Press enter to insert again!");
            else {//////////////////
                Employee currEmployee = employees.get(index);
                int choice = 0;
                error = true;
                System.out.println("Do you want to check-in or check-out?" +
                                   "\nInsert 1 to check-in" +
                                   "\nInsert 2 to check-out");
                choice = returnValidShowInfo();

                if (choice == 1) {
                    int hourIn, minuteIn;

                    System.out.println("Insert hour of entrance: (Number format : 0 to 23)");
                    hourIn = returnValidHour();
                    System.out.println("Insert minute of entrance: (Number format: 0 to 59");
                    minuteIn = returnValidMinute();

                    currEmployee.setHourIn(hourIn);
                    currEmployee.setMinuteIn(minuteIn);
                    System.out.println(currEmployee.getHourIn() +":"+ currEmployee.getMinuteIn() +" entry registered successfully to employee "+ currEmployee.getName());

                    saveState();
                }
                else if (choice == 2) {

                    int hourOut, minuteOut;
                    System.out.println("Insert hour of exit: (Number format : 0 to 23)");
                    hourOut = returnValidHour();
                    System.out.println("Insert minute of exit: (Number format: 0 to 59");
                    minuteOut = returnValidMinute();

                    currEmployee.setHourOut(hourOut);
                    currEmployee.addHours( currEmployee.setMinuteOut(minuteOut) );
                    currEmployee.addDayWorked();
                    // chamar metodo da propria variavel
                    if(currEmployee instanceof Hourly)
                        //((Hourly)currEmployee).calculateHours();
                        currEmployee.calculateHours();
                    else if(currEmployee instanceof Commissioned)
                        ((Commissioned)currEmployee).calculateHours();
                    else if(currEmployee instanceof Salaried)
                        ((Salaried)currEmployee).calculateHours();
                    System.out.println("hours = "+currEmployee.getHours());
                    System.out.println(currEmployee.getHourOut() +":"+ currEmployee.getMinuteOut() +" exit registered successfully to employee "+ currEmployee.getName());

                    currEmployee.setHourIn(0); currEmployee.setHourOut(0); currEmployee.setMinuteIn(0); currEmployee.setMinuteOut(0);

                    saveState();
                }
                else
                    System.out.println("Invalid input!Insert again!");
            }////////////////

        }
    }

    public void resultSales(){
        String id;
        int index;
        double value, commissionRate;
        while(true){
            read.nextLine();
            System.out.println("Insert ID: (insert quit to go back to main screen)");
            id = read.nextLine();
            index = getIndex(id);
            if(id.equalsIgnoreCase("quit")){
                System.out.println("Back to main screen!");
                break;
            }
            else if(index == -1)
                System.out.println("Employee with ID: " +  id + " not found! Press enter to try again!");
            else{
                Employee currEmployee = employees.get(index);
                if(currEmployee instanceof Commissioned){
                    System.out.print("Insert value of sale: (Number format : 9999,99)\nR$ ");
                    value = returnValidDouble();

                    commissionRate = ((Commissioned) currEmployee).getCommissionRate();
                    ((Commissioned) currEmployee).addCommission(value * commissionRate);
                    System.out.println("Sale registered successfully to employee " + currEmployee.getName());

                    saveState();
                }
                else
                    System.out.println("NOT a commissioned employee! ");
            }
        }
    }

    public void serviceFee() {
        read.nextLine();
        String id;
        int index;
        while(true){
            System.out.println("--ServiceFee--\nInsert ID: (insert 'quit' to go back to main screen)");
            id = read.nextLine();
            index = getIndex(id);
            if(id.equalsIgnoreCase("quit")) {
                System.out.println("Back to main menu!");
                break;
            }
            else if(index == -1)
                System.out.println("The employee with ID : " + id + " was not found! Try again!");
            else{
                if(employees.get(index).getPartUnion()){
                    double serviceFee;
                    System.out.print("Insert service fee: (Number format: 9999,99)\nR$");
                    serviceFee = returnValidDouble();

                    employees.get(index).getUnion().addServiceFee(serviceFee);
                    System.out.println("R$ " + serviceFee + " of service fee successfully registered on " + employees.get(index).getName() + "'s union account!");

                    saveState();
                    read.nextLine();
                }
                else
                    System.out.println("The selected employee is not part of any union!");
            }
        }


    }

    public void changeRegister(){
        read.nextLine();
        System.out.println("Enter ID:");
        String id = read.nextLine();
        int index = getIndex(id);
        if(index != -1){
            int option;

            while(true){

                screenChangeRegister();
                option = returnValidChangeRegisterChoice();
                if(option == 0)
                    break;
                else if(option == 1){
                    read.nextLine();
                    screenPrintChangeRegister(1);
                    String name = read.nextLine();
                    employees.get(index).setName(name);
                    System.out.println("Name changed successfully to :" + employees.get(index).getName());

                    saveState();
                }
                else if(option == 2){
                    read.nextLine();
                    screenPrintChangeRegister(2);
                    String address = read.nextLine();
                    employees.get(index).setAddress(address);
                    System.out.println("Address changed successfully to : " + employees.get(index).getAddress());

                    saveState();
                }
                else if(option == 3){
                    Employee changedEmployee = null;
                    Employee currEmployee = employees.get(index);
                    double hourlyRate=-1, baseSalary=-1, commissionRate = -1;
                    String newType;
                    read.nextLine();
                    System.out.println("\nInsert new type of payment:\n" +
                            "h - hourly / s - salaried / c - commissioned");
                    newType = returnValidTypePayment();

                    if(!newType.equals( currEmployee.getTypePayment() )){
                        if( newType.equals("h")){
                            System.out.print("Insert new hourly rate: (Number format: 9999,99)\nR$ ");
                            hourlyRate = returnValidDouble();
                            changedEmployee = new Hourly(currEmployee.getName(), currEmployee.getAddress(), "h", currEmployee.getWayPayment(), hourlyRate);
                        }
                        else if(newType.equals("s") || newType.equals("c")){
                            System.out.print("Insert new base salary: (Number format: 9999,99)\nR$ ");
                            baseSalary = returnValidDouble();
                            if(newType.equals("s"))
                                changedEmployee = new Salaried(currEmployee.getName(), currEmployee.getAddress(), "s", currEmployee.getWayPayment(), baseSalary);
                            else {
                                System.out.print("Insert commission rate: (Number format: 0,01 to 0,99)\nR$ ");
                                commissionRate = returnValidRate();
                                changedEmployee = new Commissioned(currEmployee.getName(), currEmployee.getAddress(), "c", currEmployee.getWayPayment().toLowerCase(), baseSalary, commissionRate);
                            }
                        }
                        changedEmployee.setId(currEmployee.getId());
                        changedEmployee.startPayday();
                        setUnionCondition(currEmployee.getUnion().getUnionFee(), currEmployee.getPartUnion(), changedEmployee);
                        employees.set(index,changedEmployee);

                        saveState();
                    }
                    else
                        System.out.println("Same type of current type of payment! Change not done!");
                }
                else if(option == 4){
                    read.nextLine();
                    screenPrintChangeRegister(4);
                    String wayPayment = returnValidWayPayment();
                    employees.get(index).setWayPayment(wayPayment.toLowerCase());
                    System.out.println("Way of payment changed successfully to : " + employees.get(index).getWayPayment());

                    saveState();
                }
                else if(option == 5){
                    screenPrintChangeRegister(5);
                    read.nextLine();
                    boolean partUnion = returnValidPartOfUnion();

                    if(partUnion != employees.get(index).getPartUnion()){
                        if(partUnion){
                            screenPrintChangeRegister(6);
                            double unionFee= returnValidDouble();

                            setUnionCondition(unionFee, true, index);
                            System.out.println(employees.get(index).getName() + "is a union member now!");

                            saveState();
                        }
                        else{
                            setUnionCondition(0,false,index);
                            System.out.println(employees.get(index).getName() + "is not a union member anymore!");

                            saveState();
                        }
                    }
                    else
                        System.out.println("Same status of current union status! Changes not made!");
                }
                else if(option == 6){
                    if(employees.get(index).getPartUnion()){
                        screenPrintChangeRegister(6);
                        double unionFee= returnValidDouble();

                        employees.get(index).getUnion().setUnionFee(unionFee);
                        System.out.println("Union fee changed successfully to : " + employees.get(index).getUnion().getUnionFee());

                        saveState();
                    }
                    else
                        System.out.println("Not part of union! Changes not made!");
                }

            }// end of while true
        }
        else
            System.out.println("Employee with ID : "+ id +" not found!");


    }
    private void screenChangeRegister () {
        System.out.println("Select the required change:\n" +
                "1 - Name\n" +
                "2 - Address\n" +
                "3 - Type of payment\n" +
                "4 - Method of payment\n" +
                "5 - Part of union\n" +
                "6 - Union fee\n" +
                "0 - Back to main screen");
    }
    private void screenPrintChangeRegister(int choice){
        if(choice == 1)
            System.out.println("\nInsert new name (text entry):");
        else if(choice == 2)
            System.out.println("\nInsert new address (text entry):");
        else if(choice == 4){
            System.out.println("\nInsert new method of payment:\n" +
                    "Entry format:\n'check via mail' - check via mail  /  'check in hands' - check via hands  /  'deposit' - pay via deposit");
        }
        else if(choice == 5){
            System.out.println("\nAre you part of any union?\n" +
                    "Entry format: 'yes'  or  'no'");
        }
        else if(choice == 6)
            System.out.print("\nInsert new union fee:\nNumber format: 9999,99\nUnion fee: ");
    }

    public void payroll(MyCalendar date) {

        for(int i =0 ; i < employees.size(); i++){
            if(employees.get(i) instanceof Hourly)
                ((Hourly) employees.get(i)).calculateSalary(date.getDayWeek(), date.getCounterDate(), date.getLastWorkDay());
            else if(employees.get(i) instanceof  Commissioned)
                ((Commissioned) employees.get(i)).calculateSalary(date.getDayWeek(), date.getCounterDate(), date.getLastWorkDay());
            else if(employees.get(i) instanceof  Salaried)
                ((Salaried) employees.get(i)).calculateSalary(date.getDayWeek(), date.getCounterDate(), date.getLastWorkDay());
        }
    }

    public void resetPaidUnion(){
        for(int i=0;i< employees.size();i++){
            employees.get(i).getUnion().setPaidUnionFee(false);
        }
    }

    public void setNewPayday() {
        read.nextLine();
        String id, opt;
        int index, optionSchedule = -1;
        while(true){
            System.out.println("Insert ID: (insert 'quit' to go back to main menu)");
            id = read.nextLine();
            index = getIndex(id);
            if(id.equalsIgnoreCase("quit")){
                System.out.println("Back to main menu!");
                break;
            }
            else if(index == -1){
                System.out.println("The employee with ID: "+ id + " was not found! Try again!");
            }
            else if(index != -1){
                Employee currEmployee = employees.get(index);
                if(schedulesManager.setNewPayday(currEmployee.getTypePayment(), currEmployee.getPayday(), currEmployee)) {
                    saveState();
                }
            }


        }

    }

    public void createSchedules() {
        schedulesManager.createSchedules();
    }

    public void showInfo(){
        System.out.println("Show info of 1 employee or all employees?\n 1 - 1 employee\n 2 - All employees");
        int index, choice = returnValidShowInfo();

        if(choice == 1){
            read.nextLine();
            System.out.println("Insert ID: ");
            String id = read.nextLine();
            index = getIndex(id);
            if(index != -1) {
                System.out.println("----------------------------------------------");
                System.out.println(employees.get(index));
                System.out.println("----------------------------------------------");
            }
            else
                System.out.println("Employee with ID : "+ id +" not found!");
        }
        else if(choice == 2){
            for(int i=0;i < employees.size(); i++){
                System.out.println("----------------------------------------------");
                System.out.println(employees.get(i));
                System.out.println("----------------------------------------------");
            }
        }
    }


    private void saveState(){
            ArrayList<Employee> newEmployees = new ArrayList<Employee>();
            newEmployees = copyEmployee(employees);
            undo.push(newEmployees);
    }
    public void startUndoRedo(){
        ArrayList<Employee> startArrayList = new ArrayList<Employee>();
        undo.push(startArrayList);
    }
    private ArrayList<Employee> copyEmployee(ArrayList<Employee> employees){

        ArrayList<Employee>  newEmployees = new ArrayList<Employee>();

        for(int i=0; i< employees.size();i++){
            if(employees.get(i) instanceof  Hourly){
                newEmployees.add( i, new Hourly(employees.get(i).getName(), employees.get(i).getAddress(), employees.get(i).getTypePayment(), employees.get(i).getWayPayment(), ((Hourly) employees.get(i)).getHourlyRate()));
                ((Hourly)newEmployees.get(i)).setExtraHours(((Hourly) employees.get(i)).getExtraHours());
            }
            else if(employees.get(i) instanceof  Commissioned){
                newEmployees.add( i, new Commissioned(employees.get(i).getName(), employees.get(i).getAddress(), employees.get(i).getTypePayment(), employees.get(i).getWayPayment(), ((Commissioned) employees.get(i)).getBaseSalary(), ((Commissioned) employees.get(i)).getCommissionRate()));
                ((Commissioned)newEmployees.get(i)).setCommission(((Commissioned) employees.get(i)).getCommission());
            }
            else if(employees.get(i) instanceof  Salaried){
                newEmployees.add(i, new Salaried(employees.get(i).getName(), employees.get(i).getAddress(), employees.get(i).getTypePayment(), employees.get(i).getWayPayment(), ((Salaried) employees.get(i)).getBaseSalary()));
            }

            newEmployees.get(i).setHours(employees.get(i).getHours());
            newEmployees.get(i).setId(employees.get(i).getId());
            newEmployees.get(i).setPayday(employees.get(i).getPayday());
            newEmployees.get(i).setDaysWorked(employees.get(i).getDaysWorked());
            newEmployees.get(i).setSalary(employees.get(i).getSalary());
            newEmployees.get(i).setWeeksWorked(employees.get(i).getWeeksWorked());
            newEmployees.get(i).setHourIn(employees.get(i).getHourIn());
            newEmployees.get(i).setMinuteIn(employees.get(i).getMinuteIn());

            if(employees.get(i).getPartUnion()){
                newEmployees.get(i).setPartUnion(true);
                newEmployees.get(i).getUnion().setUnionFee(employees.get(i).getUnion().getUnionFee());
                newEmployees.get(i).getUnion().setPaidUnionFee(employees.get(i).getUnion().getPaidUnionFee());
                newEmployees.get(i).getUnion().setFinalUnionId(employees.get(i).getUnion().getUnionId());
            }
            else
                newEmployees.get(i).leaveUnion();

        }// end for

        return newEmployees;
    }
    public void undoRedo() {
        read.nextLine();
        System.out.print("Insert:\n" +
                "1 - UNDO\n" +
                "2 - REDO\n" +
                "Choice: ");
        int choice = returnValidShowInfo();
        if(choice == 1){
            undo();
        }
        else if(choice == 2){
            redo();
        }

    }
    private void undo(){

        if(undo.peek().equals(undo.get(0))){
            ArrayList<Employee> newEmployees = new ArrayList<Employee>();
            employees = newEmployees;
        }

        else{
                redo.push(undo.pop());
                ArrayList<Employee> newEmployees = new ArrayList<Employee>();
                newEmployees = copyEmployee(undo.peek());
                employees = newEmployees;

                System.out.println("UNDO made successfully!\n");

        }

    }
    private void redo(){
        if(redo.isEmpty()){
            System.out.println("CANNOT REDO! Empty redo stack!\n");
        }
        else{
            ArrayList<Employee> newEmployees = new ArrayList<Employee>();
            newEmployees = copyEmployee(redo.peek());
            undo.push(newEmployees);

            ArrayList<Employee> redoFinal = new ArrayList<Employee>();
            redoFinal = copyEmployee(redo.pop());
            employees = redoFinal;

            System.out.println("REDO made successfully!\n");
        }
    }


    public void showStates(){
        for(int i=0;i< undo.size();i++){
            System.out.println(undo.pop());
        }
    } //debug

}
