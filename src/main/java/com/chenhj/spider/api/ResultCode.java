package com.chenhj.spider.api;

/**
 * 常用API返回对象
 * @author chenhj
 */
public enum ResultCode implements IErrorCode{
    /**
     * 200
     */
    SUCCESS(200, "操作成功"),

    /**
     * 500
     */
    FAILED(500, "操作失败"),

    /**
     * 404
     */
    VALIDATE_FAILED(404, "参数验证失败"),

    /**
     * 401
     */
    UNAUTHORIZED(401, "暂未登陆或token已经过期"),

    /**
     * 403
     */
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
