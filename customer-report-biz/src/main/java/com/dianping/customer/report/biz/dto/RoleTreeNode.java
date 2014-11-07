package com.dianping.customer.report.biz.dto;

import com.dianping.customer.report.biz.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaza on 14-10-16.
 */
public class RoleTreeNode {
    private RoleTreeNode parent;
    private Node data;
    private List<RoleTreeNode> childList;

    public RoleTreeNode(){
        this.parent = new RoleTreeNode();
        this.data = new Node();
        this.childList = new ArrayList<RoleTreeNode>();
    }

    public RoleTreeNode(RoleTreeNode parent,Node data,List<RoleTreeNode> childList){
        this.parent = parent;
        this.data = data;
        this.childList = childList;
    }

    public RoleTreeNode getParent() {
        return parent;
    }

    public void setParent(RoleTreeNode parent) {
        this.parent = parent;
    }

    public Node getData() {
        return data;
    }

    public void setData(Node data) {
        this.data = data;
    }

    public List<RoleTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<RoleTreeNode> childList) {
        this.childList = childList;
    }

    public boolean isGroupLeader() {
        if(CollectionUtils.isNotEmpty(childList)) {
            for(RoleTreeNode child : childList) {
                if(CollectionUtils.isNotEmpty(child.getChildList())) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isItemLeafNode(){
        if(CollectionUtils.isNotEmpty(childList)) {
            return false;
        }
        return true;
    }
}
