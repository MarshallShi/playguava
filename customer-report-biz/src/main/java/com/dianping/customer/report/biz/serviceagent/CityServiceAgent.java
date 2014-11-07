package com.dianping.customer.report.biz.serviceagent;

import com.dianping.combiz.entity.City;
import com.dianping.combiz.entity.Region;

import java.util.List;

/**
 * Created by wenjie.cao on 14-9-25.
 */
public interface CityServiceAgent {
    /**
     * 根据cityId 查询城市
     * @param cityId
     * @return
     */
    City getCityById(int cityId);

    /**
     * 获取指定城市下，所有地区列表
     * @param cityId
     * @return
     */
    List<Region> findRegionListByCityId(int cityId);

    /**
     * 获取指定城市下，所有商区列表
     * @param cityId
     * @return
     */
    List<Region> findBusinessDistrictListByCityId(int cityId);

    /**
     * 根据城市名称模糊查找城市列表
     * @param cityName
     * @return
     */
    List<City> fuzzySearchCitiesByName(String cityName);

    /**
     * 根据区域 id 查询区域
     * @param regionId
     * @return
     */
    Region getRegionById(int regionId);

    /**
     * 根据 cityId 批量查询城市列表
     * @param cityIDs
     * @return
     */
    List<City> findByIds(List<Integer> cityIDs);

    /**
     * 查询所有城市列表
     * @return
     */
    List<City> findCities();

    /**
     * 查询团购城市
     * @return
     */
    List<City> getTuanGouCityListInProcess();
}
