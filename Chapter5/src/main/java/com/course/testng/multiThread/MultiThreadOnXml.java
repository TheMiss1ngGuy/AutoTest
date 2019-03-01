package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * Created by Stan on 2017/08/02
 */
public class MultiThreadOnXml {

    @Test
    public void test1(){
        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
    }
}
