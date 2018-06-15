
package com.qding.api.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MockerConf {

    public static final Set<Class<?>> BASIC_CLASS = new HashSet<>();
    
    public static final Set<Class<?>> MAP_CLASS = new HashSet<>();
    public static final Set<Class<?>> LIST_CLASS = new HashSet<>();
    public static final Set<Class<?>> SET_CLASS = new HashSet<>();

    static {
        
        MAP_CLASS.add(Map.class);
        MAP_CLASS.add(HashMap.class);
        MAP_CLASS.add(Hashtable.class);
        MAP_CLASS.add(LinkedHashMap.class);
        MAP_CLASS.add(TreeMap.class);
        
        LIST_CLASS.add(List.class);
        LIST_CLASS.add(LinkedList.class);
        LIST_CLASS.add(ArrayList.class);
        
        SET_CLASS.add(Set.class);
        SET_CLASS.add(HashSet.class);
        SET_CLASS.add(TreeSet.class);
        SET_CLASS.add(LinkedHashSet.class);
        
        BASIC_CLASS.add(int.class);
        BASIC_CLASS.add(long.class);
        BASIC_CLASS.add(float.class);
        BASIC_CLASS.add(double.class);
        BASIC_CLASS.add(char.class);
        BASIC_CLASS.add(boolean.class);
        BASIC_CLASS.add(byte.class);
        BASIC_CLASS.add(short.class);
        BASIC_CLASS.add(Short.class);
        BASIC_CLASS.add(Byte.class);
        BASIC_CLASS.add(Boolean.class);
        BASIC_CLASS.add(Character.class);
        BASIC_CLASS.add(Double.class);
        BASIC_CLASS.add(Float.class);
        BASIC_CLASS.add(Long.class);
        BASIC_CLASS.add(Integer.class);
        BASIC_CLASS.add(String.class);
        BASIC_CLASS.add(BigDecimal.class);

    }
}
