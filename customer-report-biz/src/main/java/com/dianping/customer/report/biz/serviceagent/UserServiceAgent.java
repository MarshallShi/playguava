package com.dianping.customer.report.biz.serviceagent;

import com.dianping.ba.base.organizationalstructure.api.organization.dto.DepartmentDto;
import com.dianping.ba.base.organizationalstructure.api.user.dto.UserDto;

import java.util.List;

public interface UserServiceAgent {
    UserDto queryUserByLoginID(int loginId);

    List<DepartmentDto> getDepartmentTree();

    DepartmentDto getDepartment(int departmentId);

    DepartmentDto getDepartmentByLoginId(int loginId);

    List<DepartmentDto> getChildrenDepartments(int departmentId);
}
