package com.dianping.customer.report.biz.serviceagent.impl;

import com.dianping.ba.base.organizationalstructure.api.organization.OrganizationService;
import com.dianping.ba.base.organizationalstructure.api.organization.dto.DepartmentDto;
import com.dianping.ba.base.organizationalstructure.api.user.UserService;
import com.dianping.ba.base.organizationalstructure.api.user.dto.UserDto;
import com.dianping.customer.report.biz.serviceagent.UserServiceAgent;
import com.dianping.customer.report.biz.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceAgentImpl implements UserServiceAgent {
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    @Override
    public UserDto queryUserByLoginID(int loginId) {
        UserDto result = null;
        if(loginId < 0) {
            return userService.queryUserByLoginID(loginId);
        }
        return result;
    }

    @Override
    public List<DepartmentDto> getDepartmentTree() {
        return organizationService.getDepartmentTree();
    }

    @Override
    public DepartmentDto getDepartment(int departmentId) {
        return organizationService.getDepartment(departmentId);
    }

    @Override
    public DepartmentDto getDepartmentByLoginId(int loginId) {
        List<DepartmentDto> departmentDtoList = organizationService.getDepartmentsByLeaderId(loginId);
        if(CollectionUtils.isNotEmpty(departmentDtoList)) {
            return departmentDtoList.get(0);
        } else {
            UserDto userDto = queryUserByLoginID(loginId);
            if(userDto != null) {
                return getDepartment(userDto.getDepartmentId());
            }
        }
        return null;
    }

    @Override
    public List<DepartmentDto> getChildrenDepartments(int departmentId) {
        return organizationService.getChildrenDepartments(departmentId);
    }
}
