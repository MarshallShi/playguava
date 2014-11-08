package com.test.guava.collections;

import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FunctionTest {
	
	public static void main(String[] args){
		
		Map<String, Integer> map = ImmutableMap.of("a", 4, "b", 9);
		Map<String, Double> transformed = Maps.transformValues(map,
				new Function<Integer, Double>() {
					public Double apply(Integer in) {
						return Math.sqrt((int) in);
					}
				});
		System.out.println(transformed);

		List<String> list = Lists.newArrayList("1", "2", "3");
		List<Integer> integers = Lists.transform(list,
				new Function<String, Integer>() {
					@Override
					public Integer apply(String arg0) {
						return Integer.valueOf(arg0);
					}
				});
		System.out.println(integers);
	}

}
