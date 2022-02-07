package com.medolia.spring.demo.nullsafe;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class Employee {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.id = null;
    }

    @NotNull
    String id;
    String name;
    LocalDate joiningDate;
    String pastEmployment;

    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPastEmployment() {
        return pastEmployment;
    }

    public void setPastEmployment(String pastEmployment) {
        this.pastEmployment = pastEmployment;
    }
}
