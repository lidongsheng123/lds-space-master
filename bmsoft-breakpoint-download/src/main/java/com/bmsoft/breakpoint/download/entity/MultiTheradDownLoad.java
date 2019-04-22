package com.bmsoft.breakpoint.download.entity;

import com.bmsoft.breakpoint.download.thread.DownLoadThread;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @program: lds-space-master
 * @description: 多线程下载类信息
 * @author: 李东升
 * @create: 2019-04-18 09:59
 **/
@Data
public class MultiTheradDownLoad {


    /**
     * 文件URL
     */
    private String urlStr;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件临时名称
     */
    private String tmpFileName;

    /**
     * 线程数
     */
    private int threadNum;

    /**
     * 设置一个计数器，代码内主要用来完成对缓存文件的删除
     */
    private CountDownLatch latch;
    /**
     * 文件大小
     */
    private long fileLength;

    private long threadLength;
    /**
     * 保留每个线程下载数据的起始位置。
     */
    private long[] startPos;
    /**
     * 保留每个线程下载数据的截止位置。
     */
    private long[] endPos;

    private boolean bool = false;

    public boolean isBool() {
        return bool;
    }

    private URL url = null;


    //有参构造函数，先构造需要的数据
    public MultiTheradDownLoad(String urlStr, int threadNum) {
        this.urlStr = urlStr;
        this.threadNum = threadNum;
        startPos = new long[this.threadNum];
        endPos = new long[this.threadNum];
        latch = new CountDownLatch(this.threadNum);
    }

    /**
     * 断点下载功能
     */
    public void doDownloadPart() {
        //目标文件
        File file = null;
        //临时文件
        File tmpFile = null;

        //设置HTTP网络访问代理
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", "newslib.xinhua.io");
        System.setProperty("http.proxyPort", "8080");

        //从文件链接中获取文件名，
        fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1, urlStr
                .contains("?") ? urlStr.lastIndexOf('?') : urlStr.length());

        tmpFileName = fileName + "_tmp";

        try {

            url = new URL(urlStr);
            //打开下载链接，并且得到一个HttpURLConnection的一个对象httpcon
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setRequestMethod("GET");
            //获取请求资源的总长度。
            fileLength = httpcon.getContentLengthLong();

            //下载文件和临时文件
            file = new File(fileName);
            tmpFile = new File(tmpFileName);

            //每个线程需下载的资源大小；由于文件大小不确定，为避免数据丢失
            threadLength = fileLength % threadNum == 0 ? fileLength / threadNum : fileLength / threadNum + 1;

            if (file.exists() && file.length() == fileLength) {
                return;
            } else {
                setBreakPoint(startPos, endPos, tmpFile);
                ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 5000,
                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
                for (int i = 0; i < threadNum; i++) {
                    executor.execute(new DownLoadThread(startPos[i], endPos[i],
                            this, i, tmpFile, latch));
                }
                latch.await();
                executor.shutdown();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: 断点设置方法，当有临时文件时，直接在临时文件中读取上次下载中断时的断点位置
     * 没有临时文件，即第一次下载时，重新设置断点。
     *
     * @param: startPos  起始位置
     * @param: endPos    终止位置
     * @param: tmpFile
     * @return: void
     * @auther: 李东升
     * @date: 2019/4/18 11:34
     */
    private void setBreakPoint(long[] startPos, long[] endPos, File tmpFile) {
        RandomAccessFile rantmpfile = null;
        try {
            if (tmpFile.exists()) {
                System.out.println("继续下载!!");
                rantmpfile = new RandomAccessFile(tmpFile, "rw");
                for (int i = 0; i < threadNum; i++) {
                    rantmpfile.seek(8 * i + 8);
                    startPos[i] = rantmpfile.readLong();

                    rantmpfile.seek(8 * (i + 1000) + 16);
                    endPos[i] = rantmpfile.readLong();
//
                    System.out.println("the Array content in the exit file: ");
                    System.out.println("thre thread" + (i + 1) + " startPos:"
                            + startPos[i] + ", endPos: " + endPos[i]);
                }
            } else {
                System.out.println("the tmpfile is not available!!");
                rantmpfile = new RandomAccessFile(tmpFile, "rw");

                //最后一个线程的截止位置大小为请求资源的大小
                for (int i = 0; i < threadNum; i++) {
                    startPos[i] = threadLength * i;
                    if (i == threadNum - 1) {
                        endPos[i] = fileLength;
                    } else {
                        endPos[i] = threadLength * (i + 1) - 1;
                    }

                    rantmpfile.seek(8 * i + 8);
                    rantmpfile.writeLong(startPos[i]);

                    rantmpfile.seek(8 * (i + 1000) + 16);
                    rantmpfile.writeLong(endPos[i]);

                    System.out.println("the Array content: ");
                    System.out.println("thre thread" + (i + 1) + " startPos:"
                            + startPos[i] + ", endPos: " + endPos[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rantmpfile != null) {
                    rantmpfile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
