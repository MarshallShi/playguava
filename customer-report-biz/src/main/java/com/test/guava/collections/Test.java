package com.test.guava.collections;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Iterable<Integer> evenNumbers = Iterables.filter(numbers,
				new Predicate<Integer>() {
					@Override
					public boolean apply(Integer number) {
						return (number % 2) == 0;
					}
				});

	    for (int number : evenNumbers) {
	        System.out.print(number + ",");
	    }
	}

}
