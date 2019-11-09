package com.bmsoft.design.chain;

/**
 * @program: lds-space-master
 * @description: 责任链模式demo
 * @author: 李东升
 * @create: 2019-09-03 11:36
 **/
public class ChainTest {


    PrintProcessor printProcessor;

    public ChainTest() {
        SaveProcessor saveProcessor=new SaveProcessor();
        saveProcessor.start();
        printProcessor=new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    public static void main(String[] args) {
        Request request=new Request();
        request.setName("mic");
        new ChainTest().doTest(request);
    }

    public void doTest(Request request){
        printProcessor.processorRequest(request);

    }
}
