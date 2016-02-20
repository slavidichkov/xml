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

    public void printContentOfEmployersList() {
       for (int i=0;i<employers.size();i++){
           System.out.println(employers.get(i).getName());
//           System.out.println(employers.get(i).getStartDate());
//           System.out.println(employers.get(i).getEndDate());
           System.out.println();
       }
    }
    public void printContentOfAddressesList() {
        for (int i=0;i<addresses.size();i++){
            System.out.println(addresses.get(i).getStreet());
            System.out.println(addresses.get(i).getStreetNo());
            System.out.println(addresses.get(i).getSection());
            System.out.println(addresses.get(i).getCity());
            System.out.println();
        }
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
