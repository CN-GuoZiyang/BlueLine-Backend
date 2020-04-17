package top.guoziyang.bluelinebackend.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guoziyang.bluelinebackend.model.Result;

@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/{articleId}")
    public Result getOneArticle(@PathVariable Integer articleId) {
        return null;
    }

}
