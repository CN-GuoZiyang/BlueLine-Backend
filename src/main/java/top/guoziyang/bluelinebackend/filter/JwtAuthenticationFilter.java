package top.guoziyang.bluelinebackend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.guoziyang.bluelinebackend.configuartion.SpringContextHolder;
import top.guoziyang.bluelinebackend.model.*;
import top.guoziyang.bluelinebackend.utils.JwtUtils;
import top.guoziyang.bluelinebackend.utils.RedisUtils;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Spring Security认证过滤器
 *
 * @author ziyang
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final RedisUtils redisUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.redisUtils = SpringContextHolder.getBean(RedisUtils.class);
        super.setFilterProcessesUrl("/auth/login");
    }

    /*
        尝试认证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        认证成功后的操作，包括签发jwt token并将其存在redis中
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser: " + jwtUser.toString());
        String role = "";
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }
        // 签发Token
        String token = JwtUtils.createToken(jwtUser.getUsername(), role);
        // 将Token存储到Redis
        redisUtils.set(jwtUser.getUsername(), token, JwtUtils.EXPIRATION * 2);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("token", JwtUtils.TOKEN_PREFIX + token);
        Result result = ResultUtils.genSuccessResult(new LoginData(jwtUser.getId(), role));
        response.getWriter().write(result.toString());
    }

    /*
        认证失败时的逻辑
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Result result = ResultUtils.genFailResult("登陆失败！" + failed.getMessage());
        result.setCode(ResultCode.FAIL);
        response.getWriter().write(result.toString());
    }
}
