package com.util;

import java.util.Comparator;

import com.model.Borrow;

@SuppressWarnings("rawtypes")
public class MyComparator implements Comparator {
	
	 public int compare(Object o1, Object o2) {
		 Borrow b1 = (Borrow)o1;
		 Borrow b2 = (Borrow)o2;
		 return b1.getRenew()-(b2.getRenew());//从大到小排序
	 }
}
