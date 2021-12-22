package com.medolia.demo.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lbli
 * @date 2021/12/22
 */
public class ComparatorTest {


    private Employee[] someMoreEmployees;

    @BeforeEach
    void beforeAll() {
        someMoreEmployees = new Employee[]{
                new Employee("Jake", 25, 3000.0, 9922001L),
                new Employee("Jake", 25, 3000.0, 5924001L),
                new Employee("Ace", 22, 3000.0, 642300L),
                new Employee("Keith", 35, 4000.0, 924401L),
        };
    }

    @Test
    public void whenThenComparing_thenSortedByAgeName(){
        Comparator<Employee> employee_Age_Name_Comparator
                = Comparator.comparing(Employee::getAge)
                .thenComparing(Employee::getName)
                .thenComparingLong(Employee::getMobile);

        Arrays.sort(someMoreEmployees, employee_Age_Name_Comparator);
        System.out.println(List.of(someMoreEmployees));
        // assertTrue(Arrays.equals(someMoreEmployees, sortedEmployeesByAgeName));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Employee {
        String name;
        int age;
        double salary;
        long mobile;

        // constructors, getters & setters
    }
}
