package com.springboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.demo.model.SourceInfo;
import com.springboot.demo.model.UnfinishedSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/4/26 14:49
 */
@RestController
public class WebController {


    @Value("${sensitive.pattern}")
    private String sensitivePattern;


    @Value("#{'${system.source}'.split(',')}")
    private List<String> sources;


    @RequestMapping(value = "/findInfo}", method = RequestMethod.GET)
    public Map<String, Object> findInfoById() {
        List<UnfinishedSource> upSourceList = new ArrayList<>();

        UnfinishedSource unfinishedSource = new UnfinishedSource();
        unfinishedSource.setHeadline("中国，您***************amin");
        upSourceList.add(unfinishedSource);

        UnfinishedSource unfinishedSource1 = new UnfinishedSource();
        unfinishedSource1.setHeadline("你好");
        upSourceList.add(unfinishedSource1);



        assert  upSourceList!=null;
        ArrayList<UnfinishedSource> newupSourceList = new ArrayList<>();
        for (int i = 0; i < upSourceList.size(); i++) {
            Pattern p = Pattern.compile(sensitivePattern);
            Matcher m = p.matcher(upSourceList.get(i).getHeadline());
            if (m.find()){
                newupSourceList.add(upSourceList.get(i));
            }

        }

        System.out.println(newupSourceList.size());//会根据remove减少，数组元素往前移，后面的会用null填补
        return null;
    }

    @RequestMapping(value = "/getSourceInfo}", method = RequestMethod.GET)
    public Map<String, Object> getSourceInfo() {
        List<SourceInfo> sourceLists = new ArrayList<>(15);
        try {
            assert sources!=null;
            sources.forEach(a->{
                String[] split = a.split(":");
                SourceInfo sourceInfo = new SourceInfo();
                sourceInfo.setSysCode(split[0]);
                sourceInfo.setSysName(split[1]);
                sourceLists.add(sourceInfo);
            });
        } catch (Exception e) {

           
        }
        String s = JSONObject.toJSONString(sourceLists);
        System.out.println(s);
       return null;
    }
}
