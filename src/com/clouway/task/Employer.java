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
}
