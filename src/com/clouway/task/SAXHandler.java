package com.clouway.task;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
public class SAXHandler<T> extends DefaultHandler {

    private final List<T> tObjectsList;
    private final Class<T> aClass;
    private Object instance;
    private String value;
    private Field field;
    private List<Object> objectsListOfT;
    private Class subColectionClass;

    public SAXHandler(List<T> objectsList, Class<T> aClass) {
        this.tObjectsList = objectsList;
        this.aClass = aClass;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (aClass.getSimpleName().equals(qName)) {
            try {
                instance = aClass.newInstance();
                tObjectsList.add((T) instance);
                subColectionClass=aClass;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //iterate sub collections
        if (subColectionClass!=null && subColectionClass.getSimpleName().equals(qName)&& subColectionClass!=aClass){
            try {
                instance = subColectionClass.newInstance();
                objectsListOfT.add(instance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (subColectionClass!=null){
            try {
                field = subColectionClass.getDeclaredField(qName);
                if (Collection.class.isAssignableFrom(field.getType())) {
                    ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                    Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
                    try {
                        objectsListOfT=new ArrayList();
                        subColectionClass = Class.forName(stringListClass.getName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchFieldException e) {
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Field field = null;
        try {
            field = aClass.getDeclaredField(qName);
            setField(field);
        } catch (NoSuchFieldException e) {
            if (this.field.getName().equals(qName)) {
                setField(this.field);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = new String(ch, start, length);
    }


    private void setField(Field field) {
        try {
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                field.set(instance, value);
            }
            if (field.getType().equals(int.class)) {
                field.setInt(instance, new Integer(value));
            }
            if (field.getType().equals(Date.class)) {
                Date date = parseDate(value);
                field.set(instance, date);
            }
            if (Collection.class.isAssignableFrom(field.getType())) {
                field.set(tObjectsList.get(tObjectsList.size() - 1), objectsListOfT);
                subColectionClass=aClass;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String date) {
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
