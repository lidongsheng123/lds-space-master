package com.thread.demo.callable;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author ：Mr.Li
 * @date ：Created in 2020/6/16 14:04
 */
public class BatchQueryTask implements Callable<List<User>> {

    private final UserServiceimpl userServiceimpl;

    public BatchQueryTask(UserServiceimpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
    }


    @Override
    public List<User> call() throws Exception {
        return (List<User>) userServiceimpl.findUserInfo();
    }
}
