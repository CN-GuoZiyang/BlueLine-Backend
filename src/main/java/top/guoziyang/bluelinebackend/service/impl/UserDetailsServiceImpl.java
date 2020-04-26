package top.guoziyang.bluelinebackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.guoziyang.bluelinebackend.entity.User;
import top.guoziyang.bluelinebackend.mapper.UserMapper;
import top.guoziyang.bluelinebackend.model.JwtUser;

/**
 * 登陆时使用的service
 *
 * @author ziyang
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userMapper.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("无此用户名！");
        }
        return new JwtUser(user);
    }
}
