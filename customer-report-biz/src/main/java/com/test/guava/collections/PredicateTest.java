package com.test.guava.collections;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class PredicateTest {
	
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
	    
		List<StudentEntity> studentList = Arrays.asList(
				StudentEntity.build(1, "s1", "female"),
				StudentEntity.build(2, "s2", "female"),
				StudentEntity.build(3, "s3", "female"),
				StudentEntity.build(4, "s4", "female"),
				StudentEntity.build(5, "s5", "male"),
				StudentEntity.build(6, "s6", "female"),
				StudentEntity.build(7, "s7", "female"),
				StudentEntity.build(8, "s8", "female"));
		
		
		Iterable<StudentEntity> maleStudent = Iterables.filter(studentList,
				new Predicate<StudentEntity>() {
					@Override
					public boolean apply(StudentEntity student) {
						return student.getGender().equals("male");
					}
				});
		
		System.out.println("");
		
	    for (StudentEntity se : maleStudent) {
	        System.out.print(se.getName() + "," + se.getGender());
	    }
	}

}
