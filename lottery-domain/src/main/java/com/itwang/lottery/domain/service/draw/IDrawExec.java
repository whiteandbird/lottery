package com.itwang.lottery.domain.service.draw;

import com.itwang.lottery.domain.model.req.DrawReq;
import com.itwang.lottery.domain.model.res.DrawResult;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:26  15:01
 */
public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);
}
