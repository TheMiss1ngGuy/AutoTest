package com.course.testng;

import org.testng.annotations.Test;

/**
 * 忽略测试：@Test(enabled = false)
 * false代表忽略，true或这不设置属性都代表执行
 * Created by Stan on 2017/08/02
 */
public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ingore1 执行！");
    }

    @Test(enabled = false)
    public void ingore2(){
        System.out.println("ingore2 执行！");
    }

    @Test(enabled = true)
    public void ingore3(){
        System.out.println("ingore3 执行！");
    }
}
