package com.chw.spb.system.dao;


import com.chw.spb.common.EasyPage;
import com.chw.spb.system.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    User findUserByUsernameAndPwd(@Param("username") String username, @Param("password") String password);

    User findUserByUsername(@Param("username") String username);

    User findUserByUserId(@Param("id")String userId);

    List<User> listUser();

    List<User> listUserByPage(EasyPage easyPage);

    int deleteUserById(@Param("id") String id);

    List<Integer> listUserRoleByUserId(@Param("id") String id);

    int updateByUserIdSelective(User user);

    int updateByUserId(User user);


}