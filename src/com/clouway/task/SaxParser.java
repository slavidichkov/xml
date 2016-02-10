package com.clouway.task;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class SaxParser<T> implements XmlParser<T> {

    @Override
    public List<T> parse(Class<T> aClass, InputStream inputStream) {
        List<T> list=new ArrayList();
        SAXParserFactory spf=SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new SAXHandler(list, aClass));
            InputSource sours=new InputSource(inputStream);
            xmlReader.parse(sours);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
