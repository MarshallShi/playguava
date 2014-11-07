package com.dianping.customer.report.biz.utils;

import com.google.common.base.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenyoujun on 14-9-25.
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    public static <E> List<E> fetchAll(List<E> source,Predicate<E> predicate)
    {
        List<E> resultList = new ArrayList<E>();
        for(E e :source)
            if (predicate.apply(e))
                resultList.add(e);
        return resultList;
    }

    public static <E> boolean has(List<E> source,Predicate<E> predicate)
    {
        List<E> resultList = new ArrayList<E>();
        for(E e :source)
            if (predicate.apply(e))
                return true;
        return false;
    }
}
