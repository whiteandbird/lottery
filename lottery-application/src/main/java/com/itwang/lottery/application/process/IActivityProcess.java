package com.itwang.lottery.application.process;

import com.itwang.lottery.application.process.req.DrawProcessReq;
import com.itwang.lottery.application.process.res.DrawProcessResult;

public interface IActivityProcess {


    DrawProcessResult doDrawProcess(DrawProcessReq req);
}
