package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActivityDao {
    void insert(Activity req);

    Activity queryActivityById(Long activiryId);
}
