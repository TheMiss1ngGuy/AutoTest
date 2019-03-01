package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * 对象参数化测试
 * Created by Stan on 2017/08/02
 */
public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age){
        System.out.println("name = " + name + ", age = " + age);
    }

//    数据提供方法，属性命名都一样才会找到（如：data）
    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan", 12},
                {"lisi", 18},
                {"wangwu", 24}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age){
        System.out.println("test111方法：name = " + name + ", age = " + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age){
        System.out.println("test222方法：name = " + name + ", age = " + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;

        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"张三", 24},
                    {"李四", 28}
            };
        }else if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"王五", 32}
            };
        }
        return result;
    }
}
