package com.chw.spb.system.service;

import com.chw.spb.system.dao.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据userId更新该用户的角色信息
     * @param userId
     * @param roleIds
     */
    public void updateUserRoleForUser(String userId, List<Integer> roleIds) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        userRoleMapper.addUserRoleByUserId(userId, roleIds);
    }
}
