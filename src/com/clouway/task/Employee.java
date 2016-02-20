package com.clouway.task;

import java.util.List;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class Employee {
    private String firstName;
    private String lastName;
    private String position;
    private int age;
    private List<Employer> employers;
    private List<Address> addresses;

    public Employee(String firstName, String lastName, String position, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
    }

    public Employee() {
    }

    public int getAge() {
        return age;
    }

    public int employerListSize() {
        return employers.size();
    }

    public int addressesListSize() {
        return addresses.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }
}
