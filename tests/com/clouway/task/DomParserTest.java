package com.clouway.task;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void pretendSameFirstNameOfInstance() {
        DomParser<Employee> employeeDomParser = new DomParser();
        List<Employee> employees = employeeDomParser.parse(Employee.class, inputStream);
        assertThat(employees.get(0).getFirstName(),is(equalTo("Ivan")));
    }

    @Test
    public void pretendSameNumberOfInstancesInListField() {
        DomParser<Employee> employeeDomParser = new DomParser();
        List<Employee> employees = employeeDomParser.parse(Employee.class, inputStream);
        assertThat(employees.get(0).employerListSize(), is(equalTo(2)));
    }

    @Test
    public void pretendSameNumberOfInstances() {
        DomParser<Employee> employeeDomParser = new DomParser();
        List<Employee> employees = employeeDomParser.parse(Employee.class, inputStream);
        assertThat(employees.size(), is(equalTo(2)));
    }

}
