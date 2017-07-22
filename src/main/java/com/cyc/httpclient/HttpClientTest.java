package com.cyc.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {
    /**
     * 登录和校验的例子
     *
     * @throws Exception
     */
    public static void login() throws Exception {
        // 设置上传参数
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("username", "xxx"));
        formparams.add(new BasicNameValuePair("passwd", "xxx"));
        // 设置编码
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        // 设置访问地址
        HttpPost httppost = new HttpPost("http://xxx");
        httppost.setEntity(entity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httppost); // 访问
        System.out.println(EntityUtils.toString(response.getEntity())); // 获取返回值
    }

    /**
     * 文件上传例子
     *
     * @throws Exception
     */
    public static void multipartSubmit(CloseableHttpResponse response) throws Exception {
        // 设置访问参数， 上传的文件
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        // 设置需要上传的文件
        multipartEntityBuilder.addBinaryBody("files", new File("xxx"), ContentType.create("*/*", "utf-8"),
                URLEncoder.encode(("xxx"), "utf-8"));
        // 创建 HttpEntity
        HttpEntity httpEntity = multipartEntityBuilder.build();
        HttpPost httppost = new HttpPost("http://xxx");
        // 设置cookie
        httppost.setHeader("Cookie", response.getHeaders("Set-Cookie")[0].getValue().split(";")[0]);
        httppost.setEntity(httpEntity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        System.out.println(EntityUtils.toString(httpclient.execute(httppost).getEntity()));
    }

    /**
     * ssl 连接：
     * 1. 如果网站的证书已经被ca机构认证通过了，那么用HttpClient来调用的话，会直接成功的
     * 2. 如果没有通过ca认证，则会失败 : PKIX
     *
     * @throws Exception
     */
    public static void ssl() throws Exception {
        HttpPost httppost = new HttpPost("https://www.baidu.com");
        // 设置cookie
        CloseableHttpClient httpclient = HttpClients.createDefault();
        System.out.println(EntityUtils.toString(httpclient.execute(httppost).getEntity()));
    }

    public static void main(String[] args) throws Exception {
        ssl();
    }

}
