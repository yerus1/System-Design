package SingleResponsibility;

class Employees {
    private String name;
    private double salary;

    public Employees(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

class SalaryCalculator {
    public double calculateSalary(Employees employee) {
        return employee.getSalary()*1.5;
    }
}

class ReportGenerator {
    public String generateReport(Employees employee) {
        return "Generating employee report for " +employee.getName();
    }
}

public class SRWithOutViolation {
    public static void main(String[] args) {
        Employees employee=new Employees("Peter",90000);
        SalaryCalculator salaryCalculator=new SalaryCalculator();
        ReportGenerator reportGenerator=new ReportGenerator();
        System.out.println(reportGenerator.generateReport(employee));
        System.out.println(employee.getName()+" Salary is "+salaryCalculator.calculateSalary(employee));
    }
}
