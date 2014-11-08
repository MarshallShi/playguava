package com.dianping.customer.report.biz.utils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenyoujun on 14-9-25.
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    public static <E> List<E> fetchAll(List<E> source,Predicate<E> predicate){
        List<E> resultList = new ArrayList<E>();
        for(E e :source)
            if (predicate.apply(e))
                resultList.add(e);
        return resultList;
    }
    
    public static <E> List<E> fetchAllWithFilter(List<E> source,Predicate<E> predicate){
    	return Lists.newArrayList(Iterables.filter(source,predicate));
    }    

    public static <E> boolean has(List<E> source,Predicate<E> predicate){
        for(E e :source)
            if (predicate.apply(e))
                return true;
        return false;
    }
    
    public static <E> boolean hasWithFilter(List<E> source,Predicate<E> predicate){
    	int matched = Lists.newArrayList(Iterables.filter(source,predicate)).size();
    	return matched > 0;
    }
}
