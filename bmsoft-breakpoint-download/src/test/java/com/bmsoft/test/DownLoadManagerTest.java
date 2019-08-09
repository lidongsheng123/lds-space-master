package com.bmsoft.test;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: lds-space-master
 * @description: 断点下载测试类
 * @author: 李东升
 * @create: 2019-05-07 11:28
 **/
public class DownLoadManagerTest {


    ThreadPoolExecutor taskExecutor;

    /**
     * 线程池的基本大小
     */
    static int corePoolSize = 10;

    /**
     * 线程池最大数量
     */
    static int maximumPoolSizeSize = 1000;

    /**
     * 线程活动保持时间
     */
    static long keepAliveTime = 1;
    /**
     * 任务队列
     */
    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);

    {
        taskExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSizeSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue);
    }

    /**
     * 每个线程下载的字节数
     */
    private long unitSize = 1000 * 1024;

    private CloseableHttpClient httpClient;

    public DownLoadManagerTest() {


        System.out.println("初始化测试类....");
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    @Test
    public void doDownload() throws IOException {

        String remoteFileUrl = "http://localhost:8080/video/02.mp4";

        String localPath = "E://test//";

        String fileName = new URL(remoteFileUrl).getFile();

        System.out.println("远程文件名称：" + fileName);
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1,
                fileName.length()).replace("%20", " ");
        System.out.println("本地文件名称：" + fileName);
        long fileSize = this.getRemoteFileSize(remoteFileUrl);

        this.createFile(localPath + System.currentTimeMillis() + fileName, fileSize);

        Long threadCount = (fileSize / unitSize) + (fileSize % unitSize != 0 ? 1 : 0);
        long offset = 0;

        CountDownLatch end = new CountDownLatch(threadCount.intValue());

        if (fileSize <= unitSize) {// 如果远程文件尺寸小于等于unitSize

            DownloadThread downloadThread = new DownloadThread(remoteFileUrl,

                    localPath + fileName, offset, fileSize, end, httpClient);

            taskExecutor.execute(downloadThread);

        } else {// 如果远程文件尺寸大于unitSize

            for (int i = 1; i < threadCount; i++) {

                DownloadThread downloadThread = new DownloadThread(

                        remoteFileUrl, localPath + fileName, offset, unitSize, end, httpClient);

                taskExecutor.execute(downloadThread);

                offset = offset + unitSize;

            }

            if (fileSize % unitSize != 0) {// 如果不能整除，则需要再创建一个线程下载剩余字节

                DownloadThread downloadThread = new DownloadThread(remoteFileUrl, localPath + fileName, offset, fileSize - unitSize * (threadCount - 1), end, httpClient);
                taskExecutor.execute(downloadThread);
            }

        }
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取远程文件尺寸
     */

    private long getRemoteFileSize(String remoteFileUrl) throws IOException {
        long fileSize = 0;
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(
                remoteFileUrl).openConnection();
        httpConnection.setRequestMethod("HEAD");
        int responseCode = httpConnection.getResponseCode();
        if (responseCode >= 400) {
            System.out.println("Web服务器响应错误!");
            return 0;
        }
        String sHeader;
        for (int i = 1; ; i++) {
            sHeader = httpConnection.getHeaderFieldKey(i);
            if (sHeader != null & sHeader.equals("Content-Length")) {
                System.out.println("文件大小ContentLength:"
                        + httpConnection.getContentLength());
                fileSize = Long.parseLong(httpConnection
                        .getHeaderField(sHeader));
                break;
            }
        }
        return fileSize;
    }

    /**
     * 创建指定大小的文件
     */

    private void createFile(String fileName, long fileSize) throws IOException {

        File newFile = new File(fileName);

        RandomAccessFile raf = new RandomAccessFile(newFile, "rw");

        raf.setLength(fileSize);

        raf.close();
    }


}
