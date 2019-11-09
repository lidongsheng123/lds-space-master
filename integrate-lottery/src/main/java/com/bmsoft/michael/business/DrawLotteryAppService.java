package com.bmsoft.michael.business;

import com.bmsoft.michael.business.draw.domain.valobj.AwardItem;
import com.bmsoft.michael.business.draw.domain.valobj.DrawLotteryContext;
import com.bmsoft.michael.business.draw.service.DrawLotteryService;
import com.bmsoft.michael.business.facade.Request;
import com.bmsoft.michael.business.facade.Response;
import com.bmsoft.michael.business.prize.service.LotteryPrizeService;
import com.bmsoft.michael.business.qualification.service.LotteryQualifyService;
import com.bmsoft.michael.business.risk.service.LotteryRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawLotteryAppService {

    //风控上下文
    @Autowired
    private LotteryRiskService lotteryRiskService;

    //抽奖资格上下文
    @Autowired
    private LotteryQualifyService lotteryQualifyService;

    //抽奖核心上下文
    @Autowired
    private DrawLotteryService drawLotteryService;

    //奖品发放上下文
    @Autowired
    private LotteryPrizeService lotteryPrizeService;


    public Response doDrawLottery(Request request) {
        Response response = new Response();
        validate(request);

        DrawLotteryContext context = buildContext(request);

        //校验风控规则
        lotteryRiskService.accquireAccess(context);

        //校验抽奖资格
        lotteryQualifyService.checkLotteryCondition(context);

        //抽奖
        AwardItem awardItem = drawLotteryService.drawLottery(context);

        //发放奖品
        lotteryPrizeService.sendAwardPrize(awardItem, context);

        return response;

    }

    private DrawLotteryContext buildContext(Request request) {
        DrawLotteryContext context = new DrawLotteryContext();
        //TODO, request转化为context值对象
        return context;
    }

    private void validate(Request request) {
        //TODO
    }

}
