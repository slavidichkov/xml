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

    public Employee() {
    }

    public Employee(String firstName, String lastName, String position, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public int getAge() {
        return age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;
        if (employers != null ? !employers.equals(employee.employers) : employee.employers != null) return false;
        return !(addresses != null ? !addresses.equals(employee.addresses) : employee.addresses != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (employers != null ? employers.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        return result;
    }
}
