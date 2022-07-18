package com.itwang.lottery.domain.activity.service.partake;

import com.itwang.lottery.domain.activity.model.req.PartakeReq;
import com.itwang.lottery.domain.activity.model.res.PartakeResult;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:07:16  16:46
 */
public interface IActivityPartake {
    /**
     * 参与活动 谁何时何地参与哪个活动
     * @param req
     * @return
     */
    PartakeResult doPartake(PartakeReq req);
}

