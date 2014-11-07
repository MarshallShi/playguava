package com.dianping.customer.report.web.action.report;

import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.utils.ConfigUtils;
import com.dianping.customer.report.web.action.base.AjaxBase;
import com.dianping.customer.report.web.translator.CommonDataTranslator;
import com.dianping.customer.report.biz.service.PermissionService;
import com.dianping.customer.report.biz.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizationAction extends AjaxBase {
    private String roleId;

    @Autowired
    private PermissionService permissionService;

    public String getParentOrganization() {
        int loginId = LoginUtils.getLoginId();
        if(ConfigUtils.getSuperAdminList().contains(loginId)) {
            loginId = ConfigUtils.getSuperAdminLoginId();
        }

        RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByLoginId(loginId);
        setResponseData(CommonDataTranslator.fromRoleTreeNodeByParent(loginRoleTreeNode));
        return SUCCESS;
    }

    public String getSubOrganization() {
        RoleTreeNode loginRoleTreeNode = permissionService.getRoleTreeNodeByRoleId(roleId);
        if(loginRoleTreeNode.isGroupLeader()) {
            setResponseData(CommonDataTranslator.fromRoleTreeNodeByChildren(loginRoleTreeNode));
        }
        return SUCCESS;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
