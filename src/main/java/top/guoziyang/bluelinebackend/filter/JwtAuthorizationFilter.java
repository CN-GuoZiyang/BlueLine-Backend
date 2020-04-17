package top.guoziyang.bluelinebackend.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import top.guoziyang.bluelinebackend.configuartion.SpringContextHolder;
import top.guoziyang.bluelinebackend.exception.TokenIsExpiredException;
import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.model.ResultCode;
import top.guoziyang.bluelinebackend.utils.JwtUtils;
import top.guoziyang.bluelinebackend.utils.RedisUtils;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private RedisUtils redisUtils;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.redisUtils = SpringContextHolder.getBean(RedisUtils.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtUtils.TOKEN_HEADER);
        if(tokenHeader == null || !tokenHeader.startsWith(JwtUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            TmpUsernamePasswordAuthenticationToken tmp = getAuthentication(tokenHeader);
            if(tmp.refresh) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setHeader("token", JwtUtils.TOKEN_PREFIX + tmp.token);
            }
            SecurityContextHolder.getContext().setAuthentication(tmp.usernamePasswordAuthenticationToken);
        } catch (TokenIsExpiredException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            Result result = ResultUtils.genFailResult(e.getMessage());
            result.setCode(ResultCode.UNAUTHORIZED);
            response.getWriter().write(result.toString());
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    private TmpUsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtUtils.isExpiration(token);
        String username = JwtUtils.getUsername(token);
        String role = JwtUtils.getUserRole(token);
        if(expiration) {
            // 检查是否在Redis中
            if(!redisUtils.hasKey(username)) {
                throw new TokenIsExpiredException("Token过期，请重新登陆");
            } else if(!redisUtils.get(username).equals(token)){
                redisUtils.del(username);
                throw new TokenIsExpiredException("Token非法，请重新登陆");
            } else {
                // Token合法过期，自动续期
                token = JwtUtils.createToken(username, role);
                redisUtils.set(username, token, JwtUtils.EXPIRATION * 2);
                TmpUsernamePasswordAuthenticationToken token1 = new TmpUsernamePasswordAuthenticationToken();
                token1.usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
                token1.refresh = true;
                token1.token = token;
                return token1;
            }
        } else {
            if(username != null) {
                TmpUsernamePasswordAuthenticationToken token1 = new TmpUsernamePasswordAuthenticationToken();
                token1.usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
                token1.refresh = false;
                return token1;
            }
        }
        return null;
    }

    static class TmpUsernamePasswordAuthenticationToken {
        boolean refresh;
        String token;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
    }
}
