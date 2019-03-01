package com.course.testng;

import org.testng.annotations.Test;

/**
 * 超时测试
 * Created by Stan on 2017/08/02
 */
public class TimeOutTest {

    @Test(timeOut = 3000)//单位为毫秒值
    public void testSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000)//单位为毫秒值
    public void testFailed() throws InterruptedException{
        Thread.sleep(3000);
    }
}
