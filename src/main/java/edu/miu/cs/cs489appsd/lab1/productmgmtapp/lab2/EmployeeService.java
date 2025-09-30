package edu.miu.cs.cs489appsd.lab1.productmgmtapp.lab2;

import edu.miu.cs.cs489appsd.lab1.productmgmtapp.lab2.model.*;

import java.math.*;
import java.time.*;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        Employee emp1 = new Employee(new BigInteger("12345567"), "Daniel", "Agar", LocalDate.of(2023,1,17), new BigDecimal("105945.50"), new PensionPlan("EX1089", null, new BigDecimal("100.00")));
        Employee emp2 = new Employee(new BigInteger("12345568"), "Benard", "Shaw", LocalDate.of(2022,9,3), new BigDecimal("197750.00"), new PensionPlan(null, LocalDate.of(2025,9,3), null));
        Employee emp3 = new Employee(new BigInteger("12345569"), "Carly", "Agar", LocalDate.of(2025,10,16), new BigDecimal("842000.75"), new PensionPlan("SM2307", LocalDate.of(2025,12,17), new BigDecimal("1555.50")));
        Employee emp4 = new Employee(new BigInteger("12345570"), "Wesley", "Schneider", LocalDate.of(2023,7,21), new BigDecimal("74500.00"), null);
        Employee emp5 = new Employee(new BigInteger("12345571"), "Anna", "Wiltord", LocalDate.of(2023,3,15), new BigDecimal("85750.00"), null);
        Employee emp6 = new Employee(new BigInteger("12345572"), "Yosef", "Tesfalem", LocalDate.of(2024,10,31), new BigDecimal("100000.00"), null);
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);
    }

    public List<Employee> getAllEmployeesSorted() {
        return employees.stream().sorted(Comparator.comparing(Employee::getYearlySalary).reversed().thenComparing(Employee::getLastName)).collect(Collectors.toList());
    }

    public List<Employee> getUpcomingEnrollees() {
        LocalDate today = LocalDate.now();
        int currentQuarter = today.get(IsoFields.QUARTER_OF_YEAR);
        int nextQuarter = (currentQuarter % 4) + 1;
        int yearOfNextQuarter = (currentQuarter == 4) ? today.getYear() + 1 : today.getYear();

        Month firstMonthOfNextQuarter = Month.of((nextQuarter-1) * 3 + 1);

        LocalDate firstDayOfNextQuarter = LocalDate.of(yearOfNextQuarter, firstMonthOfNextQuarter, 1);
        LocalDate lastDayOfNextQuarter = firstDayOfNextQuarter.plusMonths(3).minusDays(1);
        System.out.println("Checking for enrollees between: " + firstDayOfNextQuarter + " and " + lastDayOfNextQuarter + "\n");
        return employees.stream()
                .filter(e->e.getPensionPlan() == null)
                .filter(e->{
                    LocalDate anniversary = e.getEmploymentDate().plusYears(5);
                    return !anniversary.isBefore(firstDayOfNextQuarter) && !anniversary.isAfter(lastDayOfNextQuarter);
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());
    }
}
