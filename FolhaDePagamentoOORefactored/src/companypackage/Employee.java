package companypackage;

import payment.TimeCard;
import union.UnionContract;

public class Employee extends TimeCard {

	private String name;
    private String address;
    private String typePayment;
    private String wayPayment;
    private String id;
    private String payday;
    private int hours;
    private double salary;
    private UnionContract union = new UnionContract();
    private boolean partUnion;
    private int daysWorked = 0;
    private int weeksWorked;

    public Employee(String name, String address, String typePayment, String wayPayment){
        this.name = name;
        this.address = address;
        this.typePayment = typePayment; // setar o payday a partir daqui :)
        this.wayPayment= wayPayment;
    }


    public void setHours(int hours) {
        this.hours = hours;
    }
    public void addHours(int hours){
        this.hours += hours;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWeeksWorked() {
        return weeksWorked;
    }

    public void setWeeksWorked(int weeksWorked) {
        this.weeksWorked = weeksWorked;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public void enterUnion(double unionFee, String name, int patternId){
    	if(partUnion == false){
            partUnion = true;
            union.setUnionFee(unionFee);
            union.setUnionId(patternId, name);
        }
    	else
            System.out.println("Already in union!");
    }

    public String getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public String getPayday() {
        return payday;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public String getWayPayment() {
        return wayPayment;
    }
    
    public boolean getPartUnion() {
    	return partUnion;
    }

    public UnionContract getUnion(){
        return union;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public void setWayPayment(String wayPayment) {
        this.wayPayment = wayPayment;
    }

    public void setPayday(String payday) {
        this.payday = payday;
    }

    public void setPartUnion(boolean partUnion) {
        this.partUnion = partUnion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void startPayday() {
        if(getTypePayment().equals("c"))
            this.payday = "s 02 4";
        else if(getTypePayment().equals("h"))
            this.payday = "s 01 4";
        else if(getTypePayment().equals("s"))
            this.payday = "m 00";
    }

    public void leaveUnion() {
        this.setPartUnion(false);
        this.union.setUnionFee(0);
        this.union.setPaidUnionFee(false);
        this.union.setServiceFee(0);
    }


    public void calculateHours(){
        int hours = getHoursWorked();
        this.hours = hours;
    }

    public void addDayWorked(){
        this.daysWorked += 1;
        if(this.daysWorked >= 7)
            this.weeksWorked +=1;

    }

}
