package com.bmsoft.michael.business.prize.service;

import com.bmsoft.michael.business.draw.domain.valobj.AwardItem;
import com.bmsoft.michael.business.draw.domain.valobj.AwardPrize;
import com.bmsoft.michael.business.draw.domain.valobj.DrawLotteryContext;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public interface LotteryPrizeService {

    /**
     * 发放奖品
     * @param awardItem
     * @param context
     * @return
     */
    AwardPrize sendAwardPrize(AwardItem awardItem, DrawLotteryContext context);
}
