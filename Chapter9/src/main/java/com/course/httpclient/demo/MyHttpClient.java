package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 步骤：
 *
 *      1、先命名结果变量（用来存放结果）;
 *      2、设置方法Get or Post方法并配置Url;
 *      3、new一个httpClient对象（用来执行方法的）;
 *      4、执行client并获取response;
 *      5、将repsonse结果转化成String类型的;
 *      6、打印result
 *
 * Created by Stan on 2017/08/02
 */
public class MyHttpClient {

    @Test
    public void test1() throws IOException {

        //用来存放我们的结果
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        //这个是用来执行get方法的
        HttpClient client = new DefaultHttpClient();
        //执行url
        HttpResponse response = client.execute(get);
        //获取相应的全部内容(其中 utf-8，为了避免中文参数乱码)
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}
