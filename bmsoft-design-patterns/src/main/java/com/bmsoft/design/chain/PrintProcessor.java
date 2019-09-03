package com.bmsoft.design.chain;

import java.util.concurrent.LinkedBlockingQueue;


public class PrintProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue();

    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = linkedBlockingQueue.take();
                System.out.println("print data:" + request);
                nextProcessor.processorRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processorRequest(Request request) {
        //处理业务
        linkedBlockingQueue.add(request);
    }
}
