package com.clouway.task;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class DomParser<T> implements XmlParser<T> {
    private InputStream inputStream;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder = null;
    private Document doc = null;

    public DomParser(){
        dbFactory = DocumentBuilderFactory.newInstance();
    }

    public void setValidating(boolean validating){
        dbFactory.setValidating(validating);
    }

    @Override
    public List<T> parse(Class<T> aClass, InputStream inputStream) {
        this.inputStream = inputStream;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeList nList = doc.getElementsByTagName(aClass.getSimpleName());

        return (List<T>) parse(nList, aClass);
    }

    private List<Object> parse(NodeList childNodes, Class aClass) {
        List<Object> instanceList = new ArrayList();
        for (int j = 0; j < childNodes.getLength(); j++) {
            Node nNode = childNodes.item(j);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Object instance = null;
               instance=createObject(aClass,eElement,j);
                instanceList.add(instance);
            }
        }

        return instanceList;
    }

    private void setField(Field field, Object instance, Element element, int parentsNumber) {
        field.setAccessible(true);
        try {
            if (field.getType().equals(String.class)) {
                field.set(instance, getValue(field, element));
            }else if (field.getType().equals(int.class)) {
                field.setInt(instance, new Integer(getValue(field, element)));
            }else if (field.getType().equals(Date.class)) {
                Date date = parseDate(getValue(field, element));
                field.set(instance, date);
            } else if (Collection.class.isAssignableFrom(field.getType())) {
                NodeList nodeList = doc.getElementsByTagName(field.getName());
                Class clazz=getCollectionGenericType(field);
                field.set(instance, createChildren(nodeList, clazz, parentsNumber));
            }else{
                field.set(instance, createObject(field.getType(),element,parentsNumber));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Class getCollectionGenericType(Field field){
        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        Class clazz = null;
        try {
            clazz = Class.forName(stringListClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private Object createObject(Class aClass, Element element,int parentsNumber){
        Field[] fields=aClass.getDeclaredFields();
        Object instance=null;
        try {
            instance=aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fields.length; i++) {
            setField(fields[i], instance, element, parentsNumber);
        }
        return instance;
    }

    private List<Object> createChildren(NodeList nodeList, Class aClass, int parentsNumber) {
        List<Object> instanceList = new ArrayList();
        Node nNode = nodeList.item(parentsNumber);
        Node node = nNode.getFirstChild();
        Object instance = null;
        while (node != null) {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                instance=createObject(aClass,eElement,parentsNumber);
                instanceList.add(instance);
            }
            node = node.getNextSibling();
        }
        return instanceList;
    }

    private String getValue(Field field, Element element) {
        return element.getElementsByTagName(field.getName()).item(0).getTextContent();
    }

    public Date parseDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            simpleDateFormat.applyPattern("dd-MM-yyyy");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
