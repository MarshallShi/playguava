package com.dianping.customer.report.biz.service;

import com.dianping.customer.report.biz.dto.RoleTreeNode;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zaza on 14-10-16.
 */
public interface PermissionService {

    public boolean isSalesManager(int loginId);

    public RoleTreeNode getRoleTreeNodeByLoginId(int loginId);

    public RoleTreeNode getRoleTreeNodeByRoleId(String roleId);

    public Map<Integer,RoleTreeNode> getRoleTreeNodeLoginIdMap();

    public Map<String,RoleTreeNode> getRoleTreeNodeRoleIdMap();

    public Set<RoleTreeNode> getRolesByCityIdAndLevel(Integer cityId,Integer Level);//Level,倒数的层级:0,叶子节点

    public boolean isChildNode(RoleTreeNode node,Integer loginId);//判断该LoginId是否在该Node子Node中
}
