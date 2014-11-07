package com.dianping.customer.report.biz.service;

import com.dianping.customer.report.biz.dto.RoleTreeNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Created by zaza on 14-10-17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/config/spring/common/appcontext-*.xml",
        "classpath*:/config/spring/local/appcontext-*.xml"})
public class PermissionServiceTest {

    @Autowired
    PermissionService permissionService;

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void clean() throws Exception{

    }

    @Test
    public void isSalesManagerTest(){
        boolean result = permissionService.isSalesManager(-13098);
        System.out.println(result);
    }

    @Test
    public void getRoleTreeNodeByLoginIdTest(){
        RoleTreeNode node1 = permissionService.getRoleTreeNodeByLoginId(-13098);
        System.out.println(node1);
        RoleTreeNode node2 = permissionService.getRoleTreeNodeByRoleId("00E90000000GOfdEAG");
        System.out.println(node2);
    }

    @Test
    public void getRoleTreeNodeMapTest(){
        Map<Integer,RoleTreeNode> loginId2Node =  permissionService.getRoleTreeNodeLoginIdMap();
        System.out.println(loginId2Node);

    }

    @Test
    public void getRolesByCityIdAndLevelTest(){
        Set<RoleTreeNode> nodes = permissionService.getRolesByCityIdAndLevel(1, 0);
    }


    @Test
    public void isChildNodeTest(){
        RoleTreeNode node = permissionService.getRoleTreeNodeByRoleId("00E90000000EOCeEAO");
        boolean isChild = permissionService.isChildNode(node,-1111);
        System.out.println(isChild);
    }


}
