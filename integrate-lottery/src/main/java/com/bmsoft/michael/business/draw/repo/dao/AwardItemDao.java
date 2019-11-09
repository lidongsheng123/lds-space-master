package com.bmsoft.michael.business.draw.repo.dao;

import com.bmsoft.michael.business.draw.repo.dao.pojo.AwardItemPO;

import java.util.List;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public interface AwardItemDao {

    List<AwardItemPO> getAwardItemByAwardPoolId(int awardPoolId);
}
