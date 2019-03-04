package com.course.testng;

import org.testng.annotations.Test;

/**
 * 依赖测试：@Test(dependsOnMethods = {"被依赖的方法"})
 * 1、若被依赖的方法执行成功，则都执行成功；
 * 2、若被依赖的方法抛异常了，则本方法会被ignore（忽略），不会执行
 * Created by Stan on 2017/08/02
 */

public class DependTest {

    @Test
    public void test1(){
        System.out.println("test1 run");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        System.out.println("test2 run");
    }
}
