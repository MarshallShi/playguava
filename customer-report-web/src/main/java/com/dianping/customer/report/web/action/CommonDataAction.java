package com.dianping.customer.report.web.action;

import com.dianping.combiz.entity.City;
import com.dianping.combiz.entity.Region;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.PageResult;
import com.dianping.customer.report.biz.enums.CustomerStatusEnum;
import com.dianping.customer.report.biz.enums.ReportEnum;
import com.dianping.customer.report.biz.enums.TimeCondition;
import com.dianping.customer.report.biz.enums.VisitStatisticEnum;
import com.dianping.customer.report.web.action.base.AjaxBase;
import com.dianping.customer.report.web.translator.CommonDataTranslator;
import com.dianping.customer.report.biz.service.PageFrameSetService;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.serviceagent.CityServiceAgent;
import com.dianping.customer.report.biz.serviceagent.ReportServiceAgent;
import com.dianping.customer.report.biz.utils.ConfigUtils;
import com.dianping.customer.report.biz.utils.DateUtils;
import com.dianping.customer.report.biz.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CommonDataAction extends AjaxBase {
    @Autowired
    private CityServiceAgent cityServiceAgent;
    @Autowired
    private ReportServiceAgent reportServiceAgent;
    @Autowired
    private PageFrameSetService pageFrameSetService;
    @Autowired
    private PermissionService permissionService;

    private int cityId;
    private String cityName;
    private String month;

    public String getEnum() {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("cityList", getTuanGouCityListInProcess());
        msg.put("categoryList", getCategory());
        msg.put("monthOfCurrentYear", getMonthOfCurrentYear());
        msg.put("customerStatus", CustomerStatusEnum.toList());
        msg.put("pageFrameSet", pageFrameSetService.getPageFrameSetList(LoginUtils.getLoginId()));

        if (ConfigUtils.getSuperAdminList().contains(LoginUtils.getLoginId())) {
            msg.put("personCityList", getTuanGouCityListInProcess());
        } else {
            msg.put("personCityList", getPersonCityList());
        }
        msg.put("timeDimension", TimeCondition.toList());
        msg.put("statisticDimension", VisitStatisticEnum.toList());

        setResponseData(msg);
        return SUCCESS;
    }

    private List<Map<String, Object>> getPersonCityList() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByLoginId(LoginUtils.getLoginId());
        if (loginRoleTreeNode != null) {
            Set<Integer> cityIdList = loginRoleTreeNode.getData().getCityIdList();
            List<City> cityList = cityServiceAgent.findByIds(new ArrayList(cityIdList));
            result = CommonDataTranslator.fromCityList(cityList);
        }

        return result;
    }

    public String fuzzySearchByCityName() {
        List<City> cities = cityServiceAgent.fuzzySearchCitiesByName(cityName);
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        if (cities != null) {
            for (City city : cities) {
                Map<String, Object> cityMap = new HashMap<String, Object>();
                cityMap.put("value", city.getCityID());
                cityMap.put("text", city.getCityName());
                res.add(cityMap);
            }
        }
        setResponseData(res);
        return SUCCESS;
    }

    public String getCurrentMonth() {
        String dateTime = DateUtils.format(DateUtils.getCurrentMonth(), DateUtils.monthFormat);
        setResponseData(dateTime);
        return SUCCESS;
    }

    public String getWeekEnumByMonth() {
        Date monthBegin = DateUtils.parse(month, DateUtils.monthFormat);
        Date begin = getSpecifyDay(monthBegin, -1, 1);
        Date end = getSpecifyDay(DateUtils.addMonths(monthBegin, 1), 1, 1);
        Date weekBegin = monthBegin;
        Date temp;
        List list = new ArrayList();
        Map map;
        int i = 0;
        for (temp = begin; temp.before(end); temp = DateUtils.addDays(temp, 1)) {
            if (temp.getDay() == 0) {
                map = new HashMap();
                map.put("value", DateUtils.format(temp, DateUtils.simpleDateFormat));
                i++;
                map.put("text", String.format("第%d周（%d月%d日-%d月%d日）", i, weekBegin.getMonth() + 1, weekBegin.getDate(), temp.getMonth() + 1, temp.getDate()));
                list.add(map);
            }
            if (temp.getDay() == 1) {
                weekBegin = temp;
            }
        }
        setResponseData(list);
        return SUCCESS;
    }


    private Date getSpecifyDay(Date date, int count, int day) {
        while (date.getDay() != day) {
            date = DateUtils.addDays(date, count);
        }
        return date;
    }

    private List<Map<String, Object>> getTuanGouCityListInProcess() {
        List<City> cityList = cityServiceAgent.getTuanGouCityListInProcess();
        return CommonDataTranslator.fromCityList(cityList);
    }

    private List<Map<String, Object>> getCategory() {
        List<Map<String, Object>> categoryList = new LinkedList<Map<String, Object>>();
        PageResult pageResult = reportServiceAgent.getReport(ReportEnum.Category, null);
        if (pageResult != null) {
            categoryList = pageResult.getItems();
        }
        return CommonDataTranslator.fromCategoryList(categoryList);
    }

    private List<Map<String, Object>> getMonthOfCurrentYear() {
        Calendar cal = Calendar.getInstance();
        List<Date> monthOfCurrentYear = DateUtils.getAllMonthOfYear(cal.get(Calendar.YEAR));
        return CommonDataTranslator.fromMonthOfYear(monthOfCurrentYear);
    }

    //获取地区
    public String getRegionByCityId() {
        List<Region> regionList = cityServiceAgent.findRegionListByCityId(cityId);
        setResponseData(CommonDataTranslator.fromRegionList(regionList));
        return SUCCESS;
    }

    //获取商区
    public String getDistrictByCityId() {
        List<Region> districtList = cityServiceAgent.findBusinessDistrictListByCityId(cityId);
        setResponseData(CommonDataTranslator.fromRegionList(districtList));
        return SUCCESS;
    }

    public String getGroupByCityId() {
        List<RoleTreeNode> groupList = new ArrayList<RoleTreeNode>(permissionService.getRolesByCityIdAndLevel(cityId, 2));

        Collections.sort(groupList, new Comparator<RoleTreeNode>() {
            @Override
            public int compare(RoleTreeNode node1, RoleTreeNode node2) {
                return node1.getData().getRoleName().compareTo(node2.getData().getRoleName());
            }
        });

        setResponseData(CommonDataTranslator.fromGroupByCityId(groupList));
        return SUCCESS;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
