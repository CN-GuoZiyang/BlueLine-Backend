package top.guoziyang.bluelinebackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.guoziyang.bluelinebackend.entity.User;
import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.repository.UserRepository;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

@RestController
@Api(tags = "用户认证相关接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    public Result registerUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword((bCryptPasswordEncoder.encode(password)));
        user.setRole("ROLE_USER");
        user.setEnable(1);
        User save = userRepository.save(user);
        return ResultUtils.genSuccessResult(save);
    }

}
