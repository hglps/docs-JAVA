package companypackage;

import payment.Payment;

public class Salaried extends Employee implements Payment {

    private double baseSalary;

    public Salaried(String name, String address, String typePayment, String wayPayment, double baseSalary){
        super(name,address,typePayment,wayPayment);
        this.baseSalary = baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        String str,union,payday;
        payday = "Monthly paid at ";
        if(getPayday().substring(2,4).equalsIgnoreCase("00"))
            payday += "last business day!\n";
        else
            payday += "day " + getPayday().substring(2,4) + "\n";
        str = "Salaried employee\nName: " + getName() + "\nAddress: " + getAddress() + "\nWay of payment: " + getWayPayment()+ "\nID: " + getId()+ "\nPayday: " + getPayday() + "\nBase Salary: R$" + getBaseSalary() + "\nDays worked: "+ getDaysWorked() +" days\n";
        str += payday;
        if(getPartUnion()) {
            union = "--Union Member--" + "\nUnion Fee: R$" + getUnion().getUnionFee() + " , Service Fee(until now): R$" + getUnion().getServiceFee() + "\nUnion ID: " + getUnion().getUnionId()+ "\n\n";
        }
        else
        	union = "--Not union member--\n\n";
        return str + union;
    }




    public boolean isPayday(int day){
        String finalDate;
        if(day >= 1 && day <= 9)
            finalDate = "0" + Integer.toString(day);
        else
            finalDate = Integer.toString(day);

        return getPayday().substring(2,4).equalsIgnoreCase(finalDate);
    }

    @Override
    public void calculateSalary(int dayOfWeek, int day, int lastBuss) {
        if(getPayday().substring(2,4).equalsIgnoreCase("00")){
            if((day == lastBuss && (getDaysWorked() >= 20))|| getDaysWorked() >= 20){
                setWeeksWorked(0);

                double salary=0;
                salary = getBaseSalary();
                if(getPartUnion()){
                    salary -= getUnion().getServiceFee();
                    if(!getUnion().getPaidUnionFee()){
                        salary -= getUnion().getUnionFee();
                        getUnion().setPaidUnionFee(true);
                    }
                }
                setSalary(salary);
                System.out.println("Salaried employee "+ getName()+ " - Salary: R$ " + getSalary());
                setSalary(0);
                getUnion().setServiceFee(0);
                setDaysWorked(0);
                setHoursWorked(0);
            }
        }
    }


}
