package mm.hlca;

public class ContextCSV {

    private String userId;
    private String llcLabel;
    private String cal;
  
    
    public ContextCSV(String userId, String cal, String llcLabel) {

    	super();
        this.userId = userId;
        this.cal = cal;
        this.llcLabel = llcLabel;
    }
    public ContextCSV()
    {
    	
    }
    
    public String getuserId() {
        return userId;
    }
    public void setuserId(String userId) {
        this.userId = userId;
    }
    public String getcal() {
        return cal;
    }
    public void setCal(String cal) {
        this.cal= cal;
    }

    public String getllcLabel() {
        return llcLabel;
    }
    public void setllcLabel(String llcLabel) {
        this.llcLabel = llcLabel;
    }
    
//    @Override
//    public String toString() {
//        return "Employee [empId=" + userId //+ ", userId=" + userId
//                + ", llcLabel=" + llcLabel + ", cal=" + cal + "]";
//    }
//    
    
}








/*
 * 
 * package contextInputcsv;

public class ContextCSV {


    private int empId;
    private String firstName;
    private String lastName;
    private int salary;
    
    public ContextCSV(int empId, String firstName, 
                     String lastName, int salary) {
        super();
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
    
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", firstName=" + userId
                + ", lastName=" + lastName + ", salary=" + salary + "]";
    }
    
    
}

 * 
 * 
 * 
 * 
 * 
 * */
