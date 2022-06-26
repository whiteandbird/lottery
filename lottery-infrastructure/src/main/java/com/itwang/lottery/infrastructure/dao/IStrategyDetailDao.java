package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailDao {
    List<StrategyDetail> queryStrategyDetail(Long strategyId);
}
