package top.guoziyang.bluelinebackend.utils;

import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.model.ResultCode;

/**
 * 创建通用返回结果的工具类
 *
 * @author ziyang
 */
public class ResultUtils {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}
