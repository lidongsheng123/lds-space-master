package com.thread.demo.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/6/16 14:04
 */
public class BatchQueryTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ArrayList<User> result = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            UserServiceimpl userServiceimpl = new UserServiceimpl();
            BatchQueryTask task = new BatchQueryTask(userServiceimpl);
            Future<List<User>> submit = executorService.submit(task);
            List<User> users = submit.get();
            result.addAll(users);
        }
    }

}
