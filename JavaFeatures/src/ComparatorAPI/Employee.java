package ComparatorAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee {
    private String EmployeeName;
    private String Department;
    private int salary;

    public Employee(String employeeName, String department, int salary) {
        EmployeeName = employeeName;
        Department = department;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getDepartment() {
        return Department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeName='" + EmployeeName + '\'' +
                ", Department='" + Department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
class Main
{
    public static void main(String[] args){
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Ravi", "IT", 80000),
                new Employee("Arun", "HR", 60000),
                new Employee("Sumanth", "IT", 95000),
                new Employee("Priya", "HR", 60000),
                new Employee("Amit", "IT", 95000)
        ));

        Comparator<Employee>comparator=(Comparator.comparing(Employee::getDepartment))
                .thenComparing(
                        Comparator.comparing(Employee::getSalary).reversed()
                ).thenComparing(Employee::getEmployeeName);
        employees.sort(comparator);

        employees.forEach(System.out::println);
    }
}
