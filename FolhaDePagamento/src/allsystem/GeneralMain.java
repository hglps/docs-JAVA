package allsystem;
import java.util.Scanner;

public class GeneralMain {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        Worker listWorkers[] = new Worker[1000];
        int indexArray=0;
        int choice;
        int idEmployee;

        visualizeOptions(); // interface

        while(read.hasNextInt()){

            choice = read.nextInt();

            if(choice == -1){
                System.out.println("\n\nSystem finished!\n");
                break;
            }
            else if(choice == 1){
                //add new employee
                listWorkers[indexArray] = addEmployee(listWorkers[indexArray], indexArray, 1);
                indexArray+=1;

            }
            else if(choice == 2){
                //delete employee
                System.out.println("\nInsert employee's ID:");
                idEmployee = read.nextInt();
                if(listWorkers[idEmployee] != null){
                    listWorkers[idEmployee] = null;
                    System.out.println("\nEmployee deleted from system with success!");
                }
                else System.out.println("The selected ID is not associated with any employee registered!\nOperation not executed");



            }
            else if(choice == 3){
                //get card info and insert in employee data
            }
            else if(choice == 4){
                //get selling info and insert in employee data
            }
            else if(choice == 5){
                //get service fee and insert in employee data
            }
            else if(choice == 6){
                //change basic employee info
                System.out.println("Insert ID:");
                idEmployee = read.nextInt();

                if(listWorkers[ idEmployee ] != null){
                    listWorkers[ idEmployee ] = addEmployee(listWorkers[ idEmployee ], idEmployee, 6);
                    System.out.println("Info from employee: " +listWorkers[ idEmployee ].getName()+  " and ID: "+idEmployee+ " modified!");
                }

                else System.out.println("Employee not found!\nChanges must not be applied!");




            } //change basic info
            else if(choice == 7){
                //get date, set salary and send to employees, if necessary
            }
            else if(choice == 8){
                // undo/redo one of previous options
            }
            else if(choice == 9){
                //set payday defined by employee
            }
            else if(choice == 10){
                //create new payday settings
            }
            else if(choice == 11){
                //control method
                System.out.println("select id:");
                int selected = read.nextInt();

                if(listWorkers[selected] != null){

                    System.out.println("name="+ listWorkers[selected].getName());
                    System.out.println("address="+ listWorkers[selected].getAddress());
                    System.out.println("type of payment="+ listWorkers[selected].getTypePayment());
                    System.out.println("id="+ listWorkers[selected].getId());
                    System.out.println("part of union= "+ listWorkers[selected].getUnionMember());
                    System.out.println("union fee= "+ listWorkers[selected].getUnionFee());
                }
                else System.out.println("Employee not found\n\n");
            }
            else System.out.println("Invalid option!\nPlease, insert a valid option:\n");
            visualizeOptions(); // interface

        }





    }// end of main method

    public static void visualizeOptions(){
            System.out.println("\n\nInsert 1 to ADD a new employee;");
            System.out.println("Insert 2 to REMOVE an employee;");
            System.out.println("Insert 3 to UPDATE TIME CARD of an employee;");
            System.out.println("Insert 4 to UPDATE THE RESULT OF SALES of an employee;");
            System.out.println("Insert 5 to UPDATE SERVICE FEE of an employee;");
            System.out.println("Insert 6 to CHANGE REGISTER of an employee;");
            System.out.println("Insert 7 to SET PAYMENTS OF PAYROLL of today;");
            System.out.println("Insert 8 to UNDO OR REDO any mistaken action;");
            System.out.println("Insert 9 to SET PAYMENT SCHEDULE;");
            System.out.println("Insert 10 to CREATE NEW PAYMENT SCHEDULES;");
            System.out.println("11 to see info using ID;");
            System.out.println("Insert -1 to end;");
            System.out.print("Option: ");

    }

    public static Worker addEmployee(Worker newEmployee, int index, int operation){

        Scanner read = new Scanner(System.in);
        int unionFeeGeneral;
        //read.nextLine(); // getchar()

        System.out.print("\nInsert name: ");
        String name = read.nextLine();

        System.out.print("Insert your address: ");
        String address = read.nextLine();

        System.out.print("Insert your type of payment:\nh - hourly\ns - salaried\nc - commissioned\n");
        String typePayment = read.nextLine();
        //checking if input is correct
        if(!typePayment.equals("h") && !typePayment.equals("s") && !typePayment.equals("c")){
            while(!typePayment.equals("h") && !typePayment.equals("s") && !typePayment.equals("c")){

                System.out.println("\nPlease, insert a valid option:\n");
                System.out.print("Insert your type of payment:\nh - hourly\ns - salaried\nc - commissioned\n");
                typePayment = read.nextLine();

            }
        }
        ///////////////////////////////

        System.out.println("Are you part of any union?\nyes / no:");
        String optionUnion = read.nextLine();
        boolean memberUnion = optionUnion.equals("yes") ? true : false;
        if(memberUnion) System.out.println("Then, insert the union fee:");
        unionFeeGeneral = read.nextInt();
        int unionFee = memberUnion ? unionFeeGeneral : 0;

        System.out.println("How would you like to get paid? Insert:\nm - check via mail\nh - check in hands\nd - pay via deposit");
        String wayPayment = read.nextLine();
        //checking if input is correct
        if(!wayPayment.equals("m") && !wayPayment.equals("h") && !wayPayment.equals("d")){
            while(!wayPayment.equals("m") && !wayPayment.equals("h") && !wayPayment.equals("d")){

                System.out.println("\nPlease, insert a valid option:");
                System.out.println("How would you like to get paid? Insert:\nm - check via mail\nh - check in hands\nd - pay via deposit");
                wayPayment = read.nextLine();

            }
        }
        ///////////////////////////////



        if(operation == 1){
            /* Adding a new employee to the system! */
            newEmployee = new Worker(name,address,typePayment,memberUnion,wayPayment,unionFee,index);
            System.out.println("The ID of current employee " +newEmployee.getName()+ " is " +newEmployee.getId()+ "\nDon't forget it!");
            /*///////////////////////////////////// */
        }
        else System.out.println("Employee's info updated with success!");
        return newEmployee;
    }
}
