package com.chw.spb.system.dao;

import com.chw.spb.system.entity.Dict;

public interface DictMapper {
    int insert(Dict record);

    int insertSelective(Dict record);
}