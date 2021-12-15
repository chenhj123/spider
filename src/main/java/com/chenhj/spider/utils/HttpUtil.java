package com.chenhj.spider.utils;

import okhttp3.*;
import okhttp3.internal.http2.Header;

import java.io.IOException;
import java.util.*;

/**
 * http工具
 * @author chenhj
 */
public class HttpUtil {

    static OkHttpClient client = new OkHttpClient();

    /**
     * get请求
     * @param url 地址
     * @return 数据
     */
    public static String doGet(String url){
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * post请求
     * @param url      地址
     * @param paramMap 参数Map
     * @return 数据
     */
    public static String doPost(String url, Map<String, String> paramMap){
        List<String> nameList = new ArrayList<>(paramMap.keySet());
        List<String> valueList = new ArrayList<>(paramMap.values());
        RequestBody formBody = new FormBody(nameList, valueList);
        Request request = new Request.Builder().url(url).post(formBody).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
