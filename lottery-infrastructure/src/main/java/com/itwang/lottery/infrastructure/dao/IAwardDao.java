package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAwardDao {
    Award queryAwardInfo(String awardId);

    void insertList(List<Award> awardList);
}
