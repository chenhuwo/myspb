package com.chw.spb.system.dao;

import com.chw.spb.system.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    void deleteUserRoleByUserId(@Param("userId")String userId);

    void addUserRoleByUserId(@Param("userId") String userId, @Param("list") List<Integer> newRoleIdList);
}