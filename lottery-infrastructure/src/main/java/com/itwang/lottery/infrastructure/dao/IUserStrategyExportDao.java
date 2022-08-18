package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.UserStrategyExport;
import com.wang.middleware.db.router.annotation.DBRouter;
import com.wang.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    @DBRouter(key = "uId ")
    UserStrategyExport queryUserStrategyExportByUId(String uId);
}
