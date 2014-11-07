package com.dianping.customer.report.biz.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zaza on 14-10-16.
 */
public class Node {
    private Set<Integer> loginIdList;
    private String roleId;
    private String roleName;
    private Set<Integer> cityIdList;

    public Node(){
        loginIdList = new HashSet<Integer>();
        cityIdList = new HashSet<Integer>();
    }

    public Node(String roleId){
        this.roleId = roleId;
        cityIdList = new HashSet<Integer>();
        loginIdList = new HashSet<Integer>();
    }

    public Node(Set<Integer> loginIdList,String roleId,Set<Integer> cityList,String roleName){
        this.loginIdList = loginIdList;
        this.roleId = roleId;
        this.cityIdList = cityList;
        this.roleName = roleName;
    }

    public Set<Integer> getLoginIdList() {
        return loginIdList;
    }

    public void setLoginIdList(Set<Integer> loginIdList) {
        this.loginIdList = loginIdList;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Integer> getCityIdList() {
        return cityIdList;
    }

    public void setCityIdList(Set<Integer> cityIdList) {
        this.cityIdList = cityIdList;
    }
}
