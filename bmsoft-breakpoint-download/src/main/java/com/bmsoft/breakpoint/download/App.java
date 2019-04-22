package com.bmsoft.breakpoint.download;

import com.bmsoft.breakpoint.download.entity.MultiTheradDownLoad;

/**
 * @program: lds-space-master
 * @description: helloworld
 * @author: 李东升
 * @create: 2019-04-18 09:58
 **/
public class App {

    public static void main(String[] args) {
        int threadNum = 4;
        String filepath = "https://media.w3.org/2010/05/sintel/trailer.mp4";

        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,threadNum);
        load.doDownloadPart();

    }
}
