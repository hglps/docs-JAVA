package allsystem;

public class Worker {

    private String name;
    private String address;
    private String typePayment;
    private double salaryRegular = 0;
    private double salaryCommission = 0;
    private int unionFee = 0;
    private int id;
    private int idUnion;
    private boolean unionMember;

    public Worker(String name, String address, String typePayment, boolean unionMember, String wayPayment, int unionFee, int id) {

        this.name = name;
        this.address = address;
        this.typePayment = typePayment;
        this.unionMember = unionMember;
        this.unionFee = unionFee;
        if(this.unionMember)
        this.id = id;
        this.idUnion = id;
    }

    //Setting Methods
    /////////////////////////////////////////////////////////////////////////////////////////
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public void setUnionMember(boolean unionMember) {
        this.unionMember = unionMember;
    }

    public void setUnionFee(double unionFee) {
        this.unionFee = unionFee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUnion(int idUnion) {
        this.idUnion = idUnion;
    }

    public void setSalaryRegular(double salaryRegular) {
        this.salaryRegular = salaryRegular;
    }

    public void setSalaryCommission(double salaryCommission) {
        this.salaryCommission = salaryCommission;
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    //Getting Methods
    /////////////////////////////////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public boolean getUnionMember(){
        return unionMember;
    }

    public int getId() {
        return id;
    }

    public int getIdUnion() {
        return idUnion;
    }

    public double getUnionFee() {
        return unionFee;
    }

    public double getSalaryRegular() {
        return salaryRegular;
    }

    public double getSalaryCommission() {
        return salaryCommission;
    }
    /////////////////////////////////////////////////////////////////////////////////////////

}

