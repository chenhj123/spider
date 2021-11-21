package com.chenhj.spider.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * http工具
 * @author chenhj
 */
public class HttpUtil {

    public static String doGet(String path){
        InputStream in = null;
        String data = "";
        try {
            URL url = new URL(path);
            in = url.openConnection().getInputStream();
            data = IOUtils.toString(in);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
