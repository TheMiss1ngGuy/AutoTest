package com.course.httpclient.cookies;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Stan on 2017/08/02
 */
public class MyCookiesForPost {


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

    @Test
    public void testPostMethod() throws IOException {

        String uri = resourceBundle.getString("test.postWithCookies");
        //拼接最终的测试地址
        String testUrl = url + uri;

        //声明一个Client对象，用来惊醒方法的执行
        DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，这个方法就好似post方法
        HttpPost post = new HttpPost(testUrl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("age", "20");

        //设置请求头信息 设置Header
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取到结果值
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("success", success);
        Assert.assertEquals("1", status);
    }

}
