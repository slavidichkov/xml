package com.clouway.task;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class Address {
    public   String street;
    public   int streetNo;
    public   String section;
    public   String city;

    public Address() {
    }

    public Address(String street, int streetNo, String section, String city) {
        this.street = street;
        this.streetNo = streetNo;
        this.section = section;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public String getSection() {
        return section;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (streetNo != address.streetNo) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (section != null ? !section.equals(address.section) : address.section != null) return false;
        return !(city != null ? !city.equals(address.city) : address.city != null);

    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + streetNo;
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
