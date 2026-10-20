import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamsCodingPractice {
    record Employee(
            int id,
            String name,
            String department,
            double salary
    ) {}
    record Employee1(
            int id,
            String name,
            String department,
            String city,
            double salary
    ) {}
    public static void main(String[] args) {

        List<Employee1> employeess = List.of(
                new Employee1(1, "Asha", "IT", "Hyderabad", 90000),
                new Employee1(2, "Rahul", "IT", "Bengaluru", 120000),
                new Employee1(3, "Priya", "HR", "Hyderabad", 70000),
                new Employee1(4, "Kiran", "HR", "Bengaluru", 80000),
                new Employee1(5, "Vikram", "IT", "Hyderabad", 110000),
                new Employee1(6, "Neha", "Finance", "Mumbai", 95000)
        );
        List<Employee> employees = List.of(
                new Employee(1, "Asha", "IT", 90000),
                new Employee(2, "Rahul", "IT", 120000),
                new Employee(3, "Priya", "HR", 70000),
                new Employee(4, "Kiran", "HR", 80000),
                new Employee(5, "Vikram", "IT", 110000),
                new Employee(6, "Neha", "Finance", 95000)
        );

        Map<String,List<Employee>> res=employees.stream()
                .collect(Collectors.groupingBy(Employee::department));


        Map<String,Long> res1=employees.stream()
                .collect(Collectors.groupingBy(Employee::department,Collectors.counting()));
        System.out.println(res1);

        Map<String,Double> res2=employees.stream()
                .collect(Collectors.groupingBy(Employee::department,Collectors.averagingDouble(Employee::salary)));

        System.out.println(res2);


        Map<String, Optional<Employee>> res3=employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.maxBy(Comparator.comparing(Employee::salary))));


        System.out.println(res3);


        Map<String,Map<String,List<Employee1>>> res0=employeess.stream()
                .collect(Collectors.groupingBy(
                        Employee1::department,
                        Collectors.groupingBy(Employee1::city)));

        System.out.println(res0);

        Optional<Double> resoo1=employees.stream()
                .map(Employee::salary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        System.out.println(resoo1.orElseThrow());

        List<Employee> arr=employees.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .limit(3)
                .toList();

        System.out.println(arr);

        Map<String,List<Employee>> result=employees.stream()
                .collect(Collectors.groupingBy(Employee::department,Collectors.collectingAndThen(
                        Collectors.toList(),
                        list->list.stream()
                                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                                .limit(2)
                                .toList()

                )));

        System.out.println(result);



    }
}
