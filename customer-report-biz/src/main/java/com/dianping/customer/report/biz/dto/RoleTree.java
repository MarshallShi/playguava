package com.dianping.customer.report.biz.dto;

import com.dianping.customer.report.biz.entity.CRMRoles;

import java.util.*;

/**
 * Created by zaza on 14-10-16.
 */
public class RoleTree {

    private RoleTreeNode root;

    public RoleTree(RoleTreeNode root){
        this.root = root;
    }

    public RoleTree createRoleTree(RoleTreeNode root,List<CRMRoles> roles,Map<String,Set<Integer>> role2Login,Map<Integer,Integer>loginId2CityId){
        RoleTree tree = new RoleTree(root);
        Map<String,List<String>> parentId2roleIds = new HashMap<String,List<String>>();
        Map<String,String> roleId2roleName = new HashMap<String,String>();
        for(CRMRoles role:roles){
            roleId2roleName.put(role.getCrmRoleId(),role.getName());
            if(role.getParentId()==null) continue;
            if(parentId2roleIds.containsKey(role.getParentId())){
                parentId2roleIds.get(role.getParentId()).add(role.getCrmRoleId());
            }else{
                parentId2roleIds.put(role.getParentId(),new ArrayList<String>());
                parentId2roleIds.get(role.getParentId()).add(role.getCrmRoleId());
            }
        }
        addChild(root,parentId2roleIds,role2Login,roleId2roleName);
        List<RoleTreeNode> leafs = new ArrayList<RoleTreeNode>();
        iteratorRoleTreeForLeafs(tree.getRoot(),leafs);
        iteratorRoleTreeFromLeaf2Root(leafs,loginId2CityId);
        return tree;
    }

    public void addChild(RoleTreeNode treeNode,Map<String,List<String>> parentId2roleIds,Map<String,Set<Integer>> role2Login,Map<String,String> roleId2roleName){
        if(treeNode.getData()!=null && treeNode.getData().getRoleId()!=null && parentId2roleIds.get(treeNode.getData().getRoleId())!=null){
            for(String childId:parentId2roleIds.get(treeNode.getData().getRoleId())){
                Set<Integer> loginIds = new HashSet<Integer>();
                if(role2Login.get(childId)!=null){
                    loginIds.addAll(role2Login.get(childId));
                }
                treeNode.getChildList().add(new RoleTreeNode(treeNode,new Node(loginIds,childId,new HashSet<Integer>(),roleId2roleName.get(childId)),new ArrayList<RoleTreeNode>()));
            }
        }
        for(RoleTreeNode node : treeNode.getChildList()){
            addChild(node,parentId2roleIds,role2Login,roleId2roleName);
        }
    }

    public void iteratorRoleTreeForLoginIdMapping(RoleTreeNode root,Map<Integer,RoleTreeNode> loginId2TreeNode){
        for(Integer loginId:root.getData().getLoginIdList()){
            loginId2TreeNode.put(loginId,root);
        }
        if(root.getChildList().size()>0){
            for(RoleTreeNode child:root.getChildList()){
                iteratorRoleTreeForLoginIdMapping(child, loginId2TreeNode);
            }
        }
    }

    public void iteratorRoleTreeForRoleIdMapping(RoleTreeNode root,Map<String,RoleTreeNode> roleId2TreeNode){
        roleId2TreeNode.put(root.getData().getRoleId(),root);
        if(root.getChildList().size()>0){
            for(RoleTreeNode child:root.getChildList()){
                iteratorRoleTreeForRoleIdMapping(child, roleId2TreeNode);
            }
        }
    }

    public void iteratorRoleTreeForLeafs(RoleTreeNode root,List<RoleTreeNode> leafs){
        if(root.getChildList().size()>0){
            for(RoleTreeNode child:root.getChildList()){
                iteratorRoleTreeForLeafs(child,leafs);
            }
        }else{
            leafs.add(root);
        }
    }
    private void iteratorRoleTreeFromLeaf2Root(List<RoleTreeNode> leafs,Map<Integer,Integer> loginId2CityId){
        for(RoleTreeNode leaf:leafs){
            for(Integer loginId:leaf.getData().getLoginIdList()){
                leaf.getData().getCityIdList().add(loginId2CityId.get(loginId));
            }
            getCityListByLeafNode(leaf,loginId2CityId);
        }
    }

    private void getCityListByLeafNode(RoleTreeNode leaf,Map<Integer,Integer> loginId2City){
        if(leaf.getParent()!=null){
            leaf.getParent().getData().getCityIdList().addAll(leaf.getData().getCityIdList());
            getCityListByLeafNode(leaf.getParent(),loginId2City);
        }
    }

    public RoleTreeNode getRoot(){
        return root;
    }

    public void setRoot(RoleTreeNode root){
        this.root = root;
    }
}
