package com.clouway.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class Employer {
    private  String name;
    private  Date startDate;
    private  Date endDate;

    public Employer() {
    }

    public Employer(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(startDate);
    }

    public String getEndDate() {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employer employer = (Employer) o;

        if (name != null ? !name.equals(employer.name) : employer.name != null) return false;
        if (startDate != null ? !startDate.equals(employer.startDate) : employer.startDate != null) return false;
        return !(endDate != null ? !endDate.equals(employer.endDate) : employer.endDate != null);

    }

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
