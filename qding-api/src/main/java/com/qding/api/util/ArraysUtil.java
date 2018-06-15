package com.qding.api.util;


import com.google.common.collect.Lists;
import com.qding.brick.pojo.biz.Project;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author lichao
 *
 */
public class ArraysUtil {

	public static <T> Map<String, T> mergerList(T[] srcList, String mergerBy,
			Object... args) {
		return mergerList(Arrays.asList(srcList), mergerBy, args);
	}

	public static <T> Map<String, T> mergerList(List<T> srcList,
			String mergerBy, Object... args) {
		Map<String, T> map = new HashMap<String, T>(0);
		try {
			for (T t : srcList) {
				PropertyDescriptor descriptor = new PropertyDescriptor(
						mergerBy, t.getClass());
				Method readMethod = descriptor.getReadMethod();
				Object src = readMethod.invoke(t, args);
				map.put(src.toString(), t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	

	public static <T> Map<String, List<T>> mergerLists(List<T> srcList,
			String mergerBy, Object... args) {
		Map<String, List<T>> map = new HashMap<String, List<T>>(0);
		try {
			for (T t : srcList) {
				PropertyDescriptor descriptor = new PropertyDescriptor(
						mergerBy, t.getClass());
				Method readMethod = descriptor.getReadMethod();
				Object src = readMethod.invoke(t, args);
				List<T> list = map.get(src.toString());
				if(list == null) {
					list = new ArrayList<>();
					map.put(src.toString(), list);
				} 
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 分组List并排序
	 * @param list
	 * @param propertyName
	 * @return
	 */
	public static <T> List<Entry<String, List<T>>> mergerListsAsList(List<T> list, String mergerBy, Object...args) {
		
		Map<String, List<T>> map = mergerLists(list, mergerBy, args);
		
		List<Map.Entry<String,List<T>>> result = new ArrayList<Map.Entry<String,List<T>>>(map.entrySet());
		
		Collections.sort(result, new Comparator<Map.Entry<String,List<T>>>(){

			@Override
			public int compare(
					Entry<String, List<T>> o1,
					Entry<String, List<T>> o2) {
				
				return o1.getKey().compareTo(o2.getKey()); 
		
			} 
		}); 
		
		return result;
	}
	
	public static <T> List<T> splitList(T[] list, String splitBy,
			Object spliyValue, Object... args) {
		return splitList(Arrays.asList(list), splitBy, spliyValue, args);
	}

	public static <T> List<T> splitList(List<T> list, String splitBy,
			Object spliyValue, Object... args) {
		List<T> tl = new ArrayList<T>(0);
		try {
			for (T t : list) {
				PropertyDescriptor descriptor = new PropertyDescriptor(splitBy,
						t.getClass());
				Method readMethod = descriptor.getReadMethod();
				Object invoke = readMethod.invoke(t, args);
				if (equals(invoke, spliyValue)) {
					tl.add(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tl;
	}

	private static boolean equals(Object value1, Object value2) {
		boolean isEquals = false;
		if (value1 instanceof String) {
			isEquals = value2.equals(value1);
		} else if (value1 instanceof Long) {
			Long lv = (Long) value1;
			Long lv2 = (Long) value2;
			isEquals = lv.longValue() == lv2.longValue();
		} else if (value1 instanceof Byte) {
			Byte bv = (Byte) value1;
			Byte bv2 = (Byte) value2;
			isEquals = bv.byteValue() == bv2.byteValue();
		} else if (value1 instanceof Integer) {
			Integer iv = (Integer) value1;
			Integer iv2 = (Integer) value2;
			isEquals = iv.intValue() == iv2.intValue();
		} else if (value1 instanceof Float) {
			Float fv = (Float) value1;
			Float fv2 = (Float) value2;
			isEquals = fv.floatValue() == fv2.floatValue();
		} else if (value1 instanceof Double) {
			Double dv = (Double) value1;
			Double dv2 = (Double) value2;
			isEquals = dv.doubleValue() == dv2.doubleValue();
		} else if (value1 instanceof Boolean) {
			Boolean bv = (Boolean) value1;
			Boolean bv2 = (Boolean) value2;
			isEquals = bv.booleanValue() == bv2.booleanValue();
		} else {
			isEquals = false;
		}
		return isEquals;
	}

	public static String listToString(List list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ",");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}
}
