package top.guoziyang.bluelinebackend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.guoziyang.bluelinebackend.entity.*;
import top.guoziyang.bluelinebackend.mapper.ArticleMapper;
import top.guoziyang.bluelinebackend.mapper.CommentMapper;
import top.guoziyang.bluelinebackend.model.*;
import top.guoziyang.bluelinebackend.service.ArticleService;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ArticlePo getArticleDto(int articleId) {
        return articleMapper.getArticleById(articleId);
    }

    @Override
    public List<CommentDto> getCommentTree(int articleId) {
        List<CommentPo> commentList = commentMapper.getCommentsByParentArticle(articleId);
        List<CommentDto> commentTree = new ArrayList<>();
        Map<Integer, CommentDto> id2CommentDto = new HashMap<>();
        Set<Integer> done = new HashSet<>();
        for(CommentPo c : commentList) {
            CommentDto commentDto = CommentDto.builder()
                    .id(c.getId())
                    .parentComment(c.getParentComment())
                    .authorId(c.getAuthorId())
                    .authorName(c.getAuthorName())
                    .time(c.getTime())
                    .content(c.getContent())
                    .childrenComments(new ArrayList<>())
                    .build();
            id2CommentDto.put(c.getId(), commentDto);
            if(commentDto.getParentComment() == null) {
                commentTree.add(commentDto);
                done.add(commentDto.getId());
            }
        }
        for(CommentDto commentDto : id2CommentDto.values()) {
            if(done.contains(commentDto.getId())) continue;
            id2CommentDto.get(commentDto.getParentComment()).getChildrenComments().add(commentDto);
        }
        commentTree.sort(Comparator.comparing(CommentDto::getTime));
        return commentTree;
    }

    @Override
    public PreviousAndNextArticlePo getPreviousArticle(int articleId) {
        return articleMapper.getPreviousArticle(articleId);
    }

    @Override
    public PreviousAndNextArticlePo getNextArticle(int articleId) {
        return articleMapper.getNextArticle(articleId);
    }

    @Override
    public Page<ArticleSummaryPo> getArticleList(int page, int size) {
        Page<ArticleSummaryPo> pageInfo = PageHelper.startPage(page, size);
        articleMapper.getSummaryByPage();
        return pageInfo;
    }

}
