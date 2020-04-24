package top.guoziyang.bluelinebackend.model;

import com.alibaba.fastjson.JSON;

/**
 * 通用返回结果
 *
 * @param <T> 返回结果数据域类型
 * @author ziyang
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
