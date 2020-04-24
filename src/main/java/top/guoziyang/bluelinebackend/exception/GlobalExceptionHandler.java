package top.guoziyang.bluelinebackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.model.ResultCode;

/**
 * 全局异常处理类，捕捉所有运行时未被捕获的异常，返回状态信息
 *
 * @author ziyang
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Override该方法使得状态码为200，错误码放在数据中
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(handlerException(ex), HttpStatus.OK);
    }

    /**
     * 直接处理Exception基类，封装错误信息到数据
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Throwable e) {
        Result result = new Result();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            result.setMessage("请求方式错误！");
        } else if (e instanceof BindException) {
            result.setMessage("数据绑定错误！");
        } else {
            result.setMessage("服务器错误！");
        }
        return result;
    }

}
