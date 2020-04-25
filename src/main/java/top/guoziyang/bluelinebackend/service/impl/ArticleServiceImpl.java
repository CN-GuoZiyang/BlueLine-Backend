package top.guoziyang.bluelinebackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.guoziyang.bluelinebackend.entity.Article;
import top.guoziyang.bluelinebackend.entity.Comment;
import top.guoziyang.bluelinebackend.entity.User;
import top.guoziyang.bluelinebackend.model.ArticleDto;
import top.guoziyang.bluelinebackend.model.CommentDto;
import top.guoziyang.bluelinebackend.model.PreviousAndNextArticleDto;
import top.guoziyang.bluelinebackend.repository.ArticleRepository;
import top.guoziyang.bluelinebackend.repository.CommentRepository;
import top.guoziyang.bluelinebackend.repository.UserRepository;
import top.guoziyang.bluelinebackend.service.ArticleService;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public ArticleDto getArticleDto(int articleId) {
        Article article = articleRepository.getArticleById(articleId);
        if(article == null) return null;
        User author = userRepository.findUserById(article.getAuthor());
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .authorId(article.getAuthor())
                .authorName(author.getUsername())
                .time(article.getTime())
                .titleImg(article.getTitleImg())
                .content(article.getContent())
                .build();
    }

    @Override
    public List<CommentDto> getCommentTree(int articleId) {
        List<Comment> commentList = commentRepository.getCommentsByParentArticle(articleId);
        List<CommentDto> commentTree = new ArrayList<>();
        Map<Integer, CommentDto> id2CommentDto = new HashMap<>();
        Set<Integer> done = new HashSet<>();
        for(Comment c : commentList) {
            CommentDto commentDto = CommentDto.builder()
                    .id(c.getId())
                    .parentComment(c.getParentComment())
                    .authorId(c.getAuthor())
                    .authorName(userRepository.findUserById(c.getAuthor()).getUsername())
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
    public PreviousAndNextArticleDto getPreviousArticle(int articleId) {
        return articleRepository.getPreviousArticle(articleId);
    }

    @Override
    public PreviousAndNextArticleDto getNextArticle(int articleId) {
        return articleRepository.getNextArticle(articleId);
    }

}
