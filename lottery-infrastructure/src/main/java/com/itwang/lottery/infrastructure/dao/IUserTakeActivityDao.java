package com.itwang.lottery.infrastructure.dao;

import com.itwang.lottery.infrastructure.po.UserTakeActivity;
import com.wang.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserTakeActivityDao {

    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);
}
