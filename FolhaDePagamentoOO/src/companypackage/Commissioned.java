package companypackage;

import payment.Payment;

public class Commissioned extends Salaried implements Payment {
    private double commission;
    private double commissionRate;

    public void setCommission(double commission) {
        this.commission = commission;
    }
    public void addCommission(double commission){
        this.commission += commission;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public Commissioned(String name, String address, String typePayment, String wayPayment, double baseSalary, double commissionRate){
        super(name,address,typePayment,wayPayment,baseSalary);
        this.commissionRate = commissionRate;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public boolean isPayday(int dayOfWeek){
        return getPayday().substring(5,6).equalsIgnoreCase(Integer.toString(dayOfWeek));
    }

    @Override
    public void calculateSalary(int dayOfWeek, int day, int lastBuss) {
        if(isPayday(dayOfWeek)){
            if(getDaysWorked() >= 10){
                setWeeksWorked(0);

                double salary=0;
                salary = commission +  getBaseSalary();
                if(getPartUnion()){
                    salary -= getUnion().getServiceFee();
                    if(!getUnion().getPaidUnionFee()){
                        salary -= getUnion().getUnionFee();
                        getUnion().setPaidUnionFee(true);
                    }
                }
                setSalary(salary);
                System.out.println("Commissioned employee "+ getName()+ " - Salary: R$ " + getSalary());
                setSalary(0);
                setCommission(0);
                getUnion().setServiceFee(0);
                setDaysWorked(0);
                setHoursWorked(0);
            }
        }
    }

    @Override
    public String toString() {
        String[] week = new String[]{"Mondays", "Tuesdays", "Wednesdays", "Thursdays", "Fridays", "Saturdays", "Sundays"};
        String str, union,payday;
        str = "Commissioned employee\nName: " + getName() + "\nAddress: " + getAddress() + "\nWay of payment: " + getWayPayment()+ "\nID: " + getId()+ "\nPayday: " + getPayday() + "\nBase Salary: " + getBaseSalary() + "\nCommission Rate: " + getCommissionRate()*100 + "%" +"\nDays worked: "+ getDaysWorked() +" days\nCommission : R$" + getCommission() +"\n";
        payday = "Bi-weekly paid at ";
        payday += week [ Integer.parseInt( getPayday().substring(5,6))] + "\n";
        str += payday;
        if(getPartUnion()) {
            union = "--Union Member--" + "\nUnion Fee: R$" + getUnion().getUnionFee() + " , Service Fee(until now): R$" + getUnion().getServiceFee() + "\nUnion ID: " + getUnion().getUnionId()+ "\n\n";
        }
        else
        	union = "--Not union member--\n\n";
        return str + union;
    }


}
