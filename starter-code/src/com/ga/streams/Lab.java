package com.ga.streams;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> workers = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        list.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employees =
            workers.stream()
                .filter(employee -> employee.getSalary() >= 50_000)
                .collect(Collectors.toList());

        printList(employees);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<Employee> employees =
            workers.stream()
                .filter(employee -> employee.getHireDate().compareTo(LocalDate.of(2012, 1, 1)) >= 0)
                .collect(Collectors.toList());

        printList(employees);
    }

    @Test
    public void getMaxSalary() {
        Employee maxSalary =
            workers.stream()
                .max(Comparator.comparing(person -> person.getSalary()))
                .orElse(null);

        double max = maxSalary.getSalary();
        System.out.println("Max:" + max);
    }

    @Test
    public void getMinSalary() {
        Employee minSalary =
            workers.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);

        double min = minSalary.getSalary();
        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
        double averageMale =
            workers.stream()
                .filter(person -> person.getGender() == "Male")
                .mapToDouble(person -> person.getSalary())
                .average()
                .getAsDouble();

        double averageFemale =
            workers.stream()
                .filter(person -> person.getGender() == "Female")
                .mapToDouble(person -> person.getSalary())
                .average()
                .getAsDouble();

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest =
            workers.stream()
                .reduce((person1, person2) -> person1.getSalary() > person2.getSalary() ? person1 : person2)
                .get();

        System.out.println("Highest Paid: " + highest);
    }
}

