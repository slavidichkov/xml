package com.clouway.task;

import java.io.InputStream;
import java.util.List;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public interface XmlParser<T> {
    List<T> parse(Class<T> aClass, InputStream inputStream);
}
