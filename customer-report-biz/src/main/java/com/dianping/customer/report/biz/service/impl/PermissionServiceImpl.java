package com.dianping.customer.report.biz.service.impl;

import com.dianping.customer.report.biz.hr.dao.CRMRolesDao;
import com.dianping.customer.report.biz.hr.dao.SFUserDao;
import com.dianping.customer.report.biz.dto.Node;
import com.dianping.customer.report.biz.dto.RoleTree;
import com.dianping.customer.report.biz.dto.RoleTreeNode;
import com.dianping.customer.report.biz.entity.CRMRoles;
import com.dianping.customer.report.biz.entity.SFUser;
import com.dianping.customer.report.biz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by zaza on 14-10-16.
 */
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private CRMRolesDao cRMRolesDao;

    @Autowired
    private SFUserDao sFUserDao;

    private String managerSFProfileId;

    private String salesSFProfileId;

    @Override
    public boolean isSalesManager(int loginId){
        HashSet<String> mgrProfileIds = getManagerProfileIds();
        List<SFUser> users = sFUserDao.getSFUserByLoginId(loginId);
        if(users!=null && users.size()>0){
            if(mgrProfileIds.contains(users.get(0).getProfileId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public RoleTreeNode getRoleTreeNodeByLoginId(int loginId){
        Map<Integer,RoleTreeNode> loginId2Node = getRoleTreeNodeLoginIdMap();
        return loginId2Node.get(loginId);
    }

    @Override
    public RoleTreeNode getRoleTreeNodeByRoleId(String roleId){
        Map<String,RoleTreeNode> roleId2Node = getRoleTreeNodeRoleIdMap();
        return roleId2Node.get(roleId);
    }

    @Override
    public Map<Integer,RoleTreeNode> getRoleTreeNodeLoginIdMap(){
        RoleTree tree = createPermissionRoleTree();
        Map<Integer,RoleTreeNode> loginId2Node = new HashMap<Integer,RoleTreeNode>();
        tree.iteratorRoleTreeForLoginIdMapping(tree.getRoot(),loginId2Node);
        return loginId2Node;
    }

    @Override
    public Map<String,RoleTreeNode> getRoleTreeNodeRoleIdMap(){
        RoleTree tree = createPermissionRoleTree();
        Map<String,RoleTreeNode> roleId2Node = new HashMap<String,RoleTreeNode>();
        tree.iteratorRoleTreeForRoleIdMapping(tree.getRoot(),roleId2Node);
        return roleId2Node;
    }

    @Override
    public Set<RoleTreeNode> getRolesByCityIdAndLevel(Integer cityId,Integer level){
        Set<RoleTreeNode> nodes = new HashSet<RoleTreeNode>();
        RoleTree tree = createPermissionRoleTree();
        Set<String> roleIds = getSalesRoleId();
        List<RoleTreeNode> leafs = new ArrayList<RoleTreeNode>();
        tree.iteratorRoleTreeForLeafs(tree.getRoot(),leafs);
        for(RoleTreeNode leaf:leafs){
            if(leaf.getData().getCityIdList().contains(cityId) && roleIds.contains(leaf.getData().getRoleId())
                    && leaf.getData().getRoleName().contains("销售代表")
                    && (!leaf.getData().getRoleName().contains("预订"))
                    && (!leaf.getData().getRoleName().contains("推广"))
                    && (!leaf.getData().getRoleName().contains("外卖"))
                    && (!leaf.getData().getRoleName().contains("结婚"))
                    && (!leaf.getData().getRoleName().contains("无业绩"))){
                nodes.add(leaf.getParent());
            }
        }
        return nodes;
    }

    @Override
    public boolean isChildNode(RoleTreeNode node,Integer loginId){
        BoolObject b = new BoolObject();
        recursionInChildNode(node,loginId,b);
        return b.getTag();
    }

    private void recursionInChildNode(RoleTreeNode node,Integer loginId,BoolObject b){
        if(node.getChildList()!=null && node.getChildList().size()>0){
            for(RoleTreeNode childNode:node.getChildList()){
                if(childNode.getData().getLoginIdList().contains(loginId)){
                    b.setTag(true);
                }
                else{
                    recursionInChildNode(childNode,loginId,b);
                }
            }
        }
    }

    private HashSet<String> getManagerProfileIds(){
        if(managerSFProfileId!=null){
            return new HashSet<String>(Arrays.asList(managerSFProfileId.split(";")));
        }
        return new HashSet<String>();
    }

    private HashSet<String> getSalesProfileIds(){
        if(salesSFProfileId!=null){
            return new HashSet<String>(Arrays.asList(salesSFProfileId.split(";")));
        }
        return new HashSet<String>();
    }

    //构造角色树
    private RoleTree createPermissionRoleTree(){
        List<CRMRoles> roles = cRMRolesDao.getAllCRMRoles();
        List<SFUser> users = sFUserDao.getAllSFUsers();
        CRMRoles rootRole = cRMRolesDao.getRootNode();
        Map<String,Set<Integer>> role2LoginId = new HashMap<String,Set<Integer>>();
        Map<Integer,Integer> loginId2CityId = new HashMap<Integer,Integer>();
        prepareData(users,role2LoginId,loginId2CityId);
        RoleTreeNode root = new RoleTreeNode(null,new Node(role2LoginId.get(rootRole.getCrmRoleId()),
                rootRole.getCrmRoleId(),new HashSet<Integer>(),rootRole.getName()),new ArrayList<RoleTreeNode>());
        RoleTree roleTree = new RoleTree(root);
        return roleTree.createRoleTree(root,roles,role2LoginId,loginId2CityId);
    }

    private void prepareData(List<SFUser> users,Map<String,Set<Integer>> role2LoginId,Map<Integer,Integer> loginId2CityId){
        for(SFUser user:users){
            if(role2LoginId.containsKey(user.getRoleId())){
                role2LoginId.get(user.getRoleId()).add(user.getLoginId());
            }else{
                Set<Integer> loginIds = new HashSet<Integer>();
                loginIds.add(user.getLoginId());
                role2LoginId.put(user.getRoleId(),loginIds);
            }
            loginId2CityId.put(user.getLoginId(),user.getCityId());
        }
    }

    private Set<String> getSalesRoleId(){
        Set<String> roleIds = new HashSet<String>();
        List<SFUser> users = sFUserDao.getAllSFUsers();
        for(SFUser usr:users){
            roleIds.add(usr.getRoleId());
        }
        return roleIds;
    }

    public void setManagerSFProfileId(String managerSFProfileId){
        this.managerSFProfileId = managerSFProfileId;
    }

    public void setSalesSFProfileId(String salesSFProfileId){
        this.salesSFProfileId = salesSFProfileId;
    }

    private class BoolObject{
        private boolean tag;
        public BoolObject(){
            tag=false;
        }
        public boolean getTag(){
            return this.tag;
        }

        public void setTag(boolean tag){
            this.tag = tag;
        }
    }
}
