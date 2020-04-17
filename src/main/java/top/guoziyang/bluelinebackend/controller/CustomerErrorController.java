package top.guoziyang.bluelinebackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.model.ResultCode;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

@RestController
@Api(tags = "默认错误接口")
public class CustomerErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @ApiOperation("错误处理路径")
    @RequestMapping("/error")
    public Result errorPage() {
        Result result = ResultUtils.genFailResult("页面路径有误！");
        result.setCode(ResultCode.NOT_FOUND);
        return result;
    }

}
