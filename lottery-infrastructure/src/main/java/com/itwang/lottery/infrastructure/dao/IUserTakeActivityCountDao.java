package com.itwang.lottery.infrastructure.dao;


import com.itwang.lottery.infrastructure.po.UserTakeActivityCount;
import com.wang.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserTakeActivityCountDao {

//    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount req);

    void insert(UserTakeActivityCount userTakeActivityCount);

    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}
