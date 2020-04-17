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

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(handlerException(ex), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public Result handlerException(Throwable e) {
        Result result = new Result();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        if(e instanceof HttpRequestMethodNotSupportedException) {
            result.setMessage("请求方式错误！");
        } else if(e instanceof BindException) {
            result.setMessage("数据绑定错误！");
        } else {
            result.setMessage("未知错误！");
        }
        return result;
    }

}
