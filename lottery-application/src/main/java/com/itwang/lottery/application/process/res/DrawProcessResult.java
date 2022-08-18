package com.itwang.lottery.application.process.res;

import com.itwang.lottery.domain.strategy.model.vo.DrawAwardInfo;
import it.comwang.lottery.common.Result;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:18  23:35
 */
public class DrawProcessResult extends Result {
    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }


    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}
