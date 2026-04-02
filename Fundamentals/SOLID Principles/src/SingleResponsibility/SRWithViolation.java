package SingleResponsibility;

class Employee {
    private String name;
    private double salary;

    public Employee(String name,double salary) {
        this.name=name;
        this.salary=salary;
    }

    public double calculateSalary(){
        return salary*1.5;
    }

    public void generateReport() {
        System.out.println("Generating the Employee Report...");
    }
}

public class SRWithViolation {
    public static void main(String[] args) {
        Employee employee=new Employee("Peter",90000);
        System.out.println(employee.calculateSalary());
        employee.generateReport();
    }
}
