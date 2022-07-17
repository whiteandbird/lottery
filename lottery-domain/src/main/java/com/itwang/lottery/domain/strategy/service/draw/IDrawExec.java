package com.itwang.lottery.domain.strategy.service.draw;

import com.itwang.lottery.domain.strategy.model.req.DrawReq;
import com.itwang.lottery.domain.strategy.model.res.DrawResult;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:01
 */
public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);
}
