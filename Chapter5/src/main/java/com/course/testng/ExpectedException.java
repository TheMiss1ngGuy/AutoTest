package com.course.testng;

import org.testng.annotations.Test;

/**
 * 什么时候会用到异常测试？
 *      在我们期望结果为某一个异常到时候
 *      比如：我们传入了某些不合法到参数，程序跑出了异常
 *      也就是说我们到预期结果就是这个异常
 * Created by Stan on 2017/08/02
 */
public class ExpectedException {

    //    这是一个测试结果会失败的异常测试

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    //    这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个失败的异常测试");
        throw new RuntimeException();

    }

}
