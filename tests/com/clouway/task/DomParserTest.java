package com.clouway.task;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class DomParserTest {
    InputStream  inputStream=null;
    String xmlFile="<employees>" +
            "    <Employee>" +
            "        <firstName>Ivan</firstName>" +
            "        <lastName>Ivanov</lastName>" +
            "        <age>23</age>" +
            "        <position>mechanic</position>" +
            "        <employers>" +
            "            <Employer>" +
            "                <name>Stefan</name>" +
            "                <startDate>21-10-2010</startDate>" +
            "                <endDate>23-4-2012</endDate>" +
            "            </Employer>" +
            "            <Employer>" +
            "                <name>Ivan</name>" +
            "                <startDate>21-10-2010</startDate>" +
            "                <endDate>23-4-2012</endDate>" +
            "            </Employer>" +
            "        </employers>" +
            "        <addresses>" +
            "            <Address>" +
            "                <street>Gabrovski</street>" +
            "                <streetNo>43</streetNo>" +
            "                <section>A</section>" +
            "                <city>Veliko Tyrnovo</city>" +
            "            </Address>" +
            "        </addresses>" +
            "    </Employee>" +
            "    <Employee>" +
            "        <firstName>Kristiqn</firstName>" +
            "        <lastName>Petkov</lastName>" +
            "        <age>24</age>" +
            "        <position>programmer</position>" +
            "        <employers>" +
            "            <Employer>" +
            "                <name>Nikolai</name>" +
            "                <startDate>5-4-2015</startDate>" +
            "                <endDate>13-1-2016</endDate>" +
            "            </Employer>" +
            "            <Employer>" +
            "                <name>Ivailo</name>" +
            "                <startDate>5-4-2015</startDate>" +
            "                <endDate>13-1-2016</endDate>" +
            "            </Employer>" +
            "        </employers>" +
            "        <addresses>" +
            "            <Address>" +
            "                <street>pultava</street>" +
            "                <streetNo>12</streetNo>" +
            "                <section>B</section>" +
            "                <city>Veliko Tyrnovo</city>" +
            "            </Address>" +
            "            <Address>" +
            "                <street>pultava</street>" +
            "                <streetNo>12</streetNo>" +
            "                <section>B</section>" +
            "                <city>Veliko Tyrnovo</city>" +
            "            </Address>" +
            "        </addresses>" +
            "    </Employee>" +
            "</employees>";

    @Before
    public void setUp(){
        inputStream=new ByteArrayInputStream(xmlFile.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void pretendThatParsedInstancesAreTheSame() {
        DomParser employeeDomParser = new DomParser();
        List<Employee> employees = employeeDomParser.parse(Employee.class, inputStream);
        assertThat(employees.get(0).getLastName(),is(equalTo("Ivanov")));
        assertThat(employees.get(0).getPosition(),is(equalTo("mechanic")));
        assertThat(employees.get(0).getAge(),is(equalTo(23)));

        List<Employer> employers = new ArrayList();
        employers.add(new Employer("Stefan", getDate("2010-10-21"),getDate("2012-4-23")));
        employers.add(new Employer("Ivan",  getDate("2010-10-21"),getDate("2012-4-23")));
        assertThat(employees.get(0).getEmployers(),is(equalTo(employers)));

        List<Address> addresses = new ArrayList();
        addresses.add(new Address("Gabrovski",43,"A","Veliko Tyrnovo"));
        assertThat(employees.get(0).getAddresses(),is(equalTo(addresses)));
    }

    private Date getDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
