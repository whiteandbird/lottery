package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.UserStrategyExport;
import com.itwang.lottery.infrastructure.po.UserTakeActivity;
import com.wang.middleware.db.router.annotation.DBRouter;
import com.wang.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DBRouterStrategy(splitTable =  true)
public interface IUserTakeActivityDao {

    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

    UserStrategyExport queryUserStrategyExportByUId(String uId);
}
