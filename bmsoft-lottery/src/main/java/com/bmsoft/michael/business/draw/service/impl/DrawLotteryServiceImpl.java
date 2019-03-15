package com.bmsoft.michael.business.draw.service.impl;

import com.bmsoft.michael.business.draw.domain.aggregate.DrawLottery;
import com.bmsoft.michael.business.draw.domain.valobj.AwardItem;
import com.bmsoft.michael.business.draw.domain.valobj.AwardPool;
import com.bmsoft.michael.business.draw.domain.valobj.DrawLotteryContext;
import com.bmsoft.michael.business.draw.repo.repository.DrawLotteryRepository;
import com.bmsoft.michael.business.draw.service.DrawLotteryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class DrawLotteryServiceImpl implements DrawLotteryService {

    //资源库
    @Autowired
    DrawLotteryRepository drawLotteryRepository;

    @Override
    public AwardItem drawLottery(DrawLotteryContext context) {
        DrawLottery drawLottery=drawLotteryRepository.getDrawLotteryById(context.getLotteryId());
        AwardPool awardPool=drawLottery.chooseAwardPool(context);
        return awardPool.doDraw();
    }
}
