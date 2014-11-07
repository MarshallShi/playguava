package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.combiz.entity.City;
import com.dianping.combiz.entity.Region;
import com.dianping.combiz.enums.TuanGouCityFlag;
import com.dianping.combiz.service.CityService;
import com.dianping.combiz.service.RegionService;
import com.dianping.combiz.service.filter.CityFilter;
import com.dianping.combiz.util.CodeConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wenjie.cao on 14-9-25.
 */
@Service
public class CityServiceAgentImpl implements com.dianping.customer.report.biz.serviceagent.CityServiceAgent {

    @Autowired
    private CityService cityService;
    @Autowired
    private RegionService regionService;

    @Override
    public City getCityById(int cityId) {
        return cityService.loadCity(cityId);
    }
    /**
     * 获取指定城市下，所有地区
     * @param cityId
     * @return
     */
    @Override
    public List<Region> findRegionListByCityId(int cityId){
        return regionService.findRegionListByCityId(cityId, CodeConstants.RegionType.District);
    }

    /**
     * 获取指定城市下，所有商区
     * @param cityId
     * @return
     */
    @Override
    public List<Region> findBusinessDistrictListByCityId(int cityId){
        return regionService.findRegionListByCityId(cityId, CodeConstants.RegionType.BusinessDistrict);
    }

    @Override
    public List<City> fuzzySearchCitiesByName(final String cityName) {
        CityFilter filter = new CityFilter() {
            @Override
            public boolean accept(City city) {

                return (StringUtils.isNotEmpty(city.getCityName()) && StringUtils.isNotEmpty(cityName) &&
                        city.getCityName().contains(cityName)) || (
                        StringUtils.isNotEmpty(city.getCityEnName()) && StringUtils.isNotEmpty(cityName) &&
                                city.getCityEnName().contains(cityName)
                );
            }
        };
        Comparator<City> sort = new

                Comparator<City>() {
                    @Override
                    public int compare(City city, City city2) {

                        return city.getCityOrderID() - city2.getCityOrderID();
                    }
                };
        return cityService.findCityList(filter, sort);
    }

    @Override
    public Region getRegionById(int regionId) {
        return  regionService.loadRegion(regionId);
    }

    @Override
    public List<City> findByIds(List<Integer> cityIDs) {
        if(CollectionUtils.isEmpty(cityIDs))
            return new ArrayList<City>();
        final Set<Integer> set  = new HashSet<Integer>(cityIDs);
        CityFilter filter = new CityFilter() {
            @Override
            public boolean accept(City city) {
                return set.contains(city.getCityID());
            }
        };
        Comparator<City> sort = new
                Comparator<City>() {
                    @Override
                    public int compare(City city, City city2) {
                        return city.getCityOrderID() - city2.getCityOrderID();
                    }
                };
        return cityService.findCityList(filter, sort);
    }

    @Override
    public List<City> findCities() {
        List<City> result = cityService.findCities();
        return result;
    }

    @Override
    public List<City> getTuanGouCityListInProcess() {
        return cityService.getTuanGouCity(TuanGouCityFlag.Inprogress);
    }
}
