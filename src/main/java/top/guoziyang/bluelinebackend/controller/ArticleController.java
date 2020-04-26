package top.guoziyang.bluelinebackend.controller;

import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.guoziyang.bluelinebackend.entity.ArticlePo;
import top.guoziyang.bluelinebackend.entity.ArticleSummaryPo;
import top.guoziyang.bluelinebackend.model.*;
import top.guoziyang.bluelinebackend.service.ArticleService;
import top.guoziyang.bluelinebackend.utils.ResultUtils;

@RestController
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    @ApiOperation("获取文章列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页页数，默认为1"),
            @ApiImplicitParam(name = "count", value = "每一页的数量，默认为10")
    })
    public Result getArticleList(Integer page, Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<ArticleSummaryPo> articleListPage = articleService.getArticleList(page, size);
        ArticleSummaryVo articleSummaryVo = ArticleSummaryVo.builder()
                .articles(articleListPage.getResult())
                .currentPage(articleListPage.getPageNum())
                .totalPage(articleListPage.getPages())
                .pageSize(articleListPage.getPageSize())
                .build();
        return ResultUtils.genSuccessResult(articleSummaryVo);
    }

    @GetMapping("/{articleId}")
    @ApiOperation("获取文章详情页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    })
    public Result getOneArticle(@PathVariable("articleId") Integer articleId) {
        ArticlePo articlePo = articleService.getArticleDto(articleId);
        if(articlePo == null) {
            Result result = ResultUtils.genFailResult("无此ID的文章！id=" + articleId);
            result.setCode(ResultCode.NOT_FOUND);
            return result;
        }
        ArticlePageVo articlePageVo = ArticlePageVo.builder()
                .article(articlePo)
                .comments(articleService.getCommentTree(articleId))
                .previousArticle(articleService.getPreviousArticle(articleId))
                .nextArticle(articleService.getNextArticle(articleId)).build();
        return ResultUtils.genSuccessResult(articlePageVo);
    }



}
