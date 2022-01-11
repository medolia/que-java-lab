package com.medolia.demo.jvm;

import com.medolia.demo.util.JsonSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class ComparatorTest {

    List<Employee> employeeList;

    @BeforeEach
    public void beforeAll() {
        String listJson = "[\n" +
                "  {\n" +
                "    \"name\": \"John\",\n" +
                "    \"age\": 25,\n" +
                "    \"salary\": 3000.0,\n" +
                "    \"mobile\": 992200\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Ace\",\n" +
                "    \"age\": 22,\n" +
                "    \"salary\": 2000.0,\n" +
                "    \"mobile\": 592400\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Keith\",\n" +
                "    \"age\": 35,\n" +
                "    \"salary\": 4000.0,\n" +
                "    \"mobile\": 392440\n" +
                "  }\n" +
                "]";
        employeeList = JsonSerializer.deserializeToList(listJson, Employee.class);
    }

    @Test
    void testComparatorChain() {
        Comparator<Employee> employeeComparator = Comparator
                .comparing(Employee::getAge)
                .thenComparing(Employee::getSalary)
                .thenComparing(Employee::getName)
                .thenComparing(Employee::getMobile);

        employeeList.stream().sorted(Comparator.nullsLast(employeeComparator)).forEach(System.out::println);
    }

    static class Employee {
        String name;
        int age;
        double salary;
        long mobile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        @Override
        public String toString() {
            return JsonSerializer.serialize(this);
        }
    }
}
