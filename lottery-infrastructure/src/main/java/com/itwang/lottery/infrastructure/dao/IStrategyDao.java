package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {
    Strategy queryStrategy(Long strategyId);

    void insert(Strategy req);
}
