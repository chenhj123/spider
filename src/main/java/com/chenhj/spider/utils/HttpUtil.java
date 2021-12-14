package com.chenhj.spider.utils;

import okhttp3.*;

import java.io.IOException;

/**
 * http工具
 * @author chenhj
 */
public class HttpUtil {

    public static String doGet(String path){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(path).get().build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body()==null?"":response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
