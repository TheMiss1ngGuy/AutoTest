package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * beforeTest()方法：
 *      1、用来读取配置文件参数的（通过key来获取value值）;
 *      2、获取配置变量的操作，是在@Test执行之前来执行的，所以要用 @BeforeTest
 *
 * Created by Stan on 2017/08/02
 */
public class MyCookieseForGet {

    private String url;
    private ResourceBundle resourceBundle;
//    用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){

        //通过配置文件的文件名，来读取配置文件的参数
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //从配置文件中读取url
        url = resourceBundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {

        String result;
        String uri = resourceBundle.getString("getCookies.uri");
        String testUrl = url + uri;
        HttpGet get = new HttpGet(testUrl);
//        HttpClient client = new DefaultHttpClient();
//        HttpClient是不能获取cookie信息的，需要用到DefaultHttpClient
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

//        获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
//        遍历cookieList内容
        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie: name = " + name + ", value = " + value);
        }

    }

    @Test(dependsOnMethods = {" "})
    public void testGetWithCookies() throws IOException {

        String uri = resourceBundle.getString("test.getWithCookies");
        String testUrl = url + uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);

        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);

        if (statusCode == 200){
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }

}
