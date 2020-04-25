package top.guoziyang.bluelinebackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guoziyang.bluelinebackend.model.ArticleDto;
import top.guoziyang.bluelinebackend.model.ArticlePageVo;
import top.guoziyang.bluelinebackend.model.Result;
import top.guoziyang.bluelinebackend.model.ResultCode;
import top.guoziyang.bluelinebackend.service.ArticleService;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{articleId}")
    @ApiOperation("获取文章详情页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    })
    public Result getOneArticle(@PathVariable("articleId") Integer articleId) {
        ArticleDto articleDto = articleService.getArticleDto(articleId);
        if(articleDto == null) {
            Result result = ResultUtils.genFailResult("无此ID的文章！id=" + articleId);
            result.setCode(ResultCode.NOT_FOUND);
            return result;
        }
        ArticlePageVo articlePageVo = ArticlePageVo.builder()
                .article(articleDto)
                .comments(articleService.getCommentTree(articleId))
                .previousArticle(articleService.getPreviousArticle(articleId))
                .nextArticle(articleService.getNextArticle(articleId)).build();
        return ResultUtils.genSuccessResult(articlePageVo);
    }

}
