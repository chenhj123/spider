package com.chenhj.spider.api;

/**
 * 常用API返回对象接口
 * @author chenhj
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
