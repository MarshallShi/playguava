package com.dianping.customer.report.biz.hr.dao;

import com.dianping.avatar.dao.GenericDao;
import com.dianping.avatar.dao.annotation.DAOAction;
import com.dianping.avatar.dao.annotation.DAOActionType;
import com.dianping.avatar.dao.annotation.DAOParam;
import com.dianping.customer.report.biz.entity.SFUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaza on 14-10-16.
 */
public interface SFUserDao extends GenericDao {
    @DAOAction(action = DAOActionType.QUERY)
    List<SFUser> getSFUserByLoginId(@DAOParam("loginId") int loginId);

    @DAOAction(action = DAOActionType.QUERY)
    ArrayList<SFUser> getAllSFUsers();
}
