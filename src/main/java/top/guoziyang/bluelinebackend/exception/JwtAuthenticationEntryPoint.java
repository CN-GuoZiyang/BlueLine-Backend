package top.guoziyang.bluelinebackend.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.model.ResultCode;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败的异常处理类，向前端返回错误信息
 *
 * @author ziyang
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String reason = "用户未登陆！";
        Result result = ResultUtils.genFailResult(reason);
        result.setCode(ResultCode.UNAUTHORIZED);
        response.getWriter().write(result.toString());
    }
}
