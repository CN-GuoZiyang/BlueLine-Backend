package top.guoziyang.bluelinebackend.model;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public class ArticleDto {

    private int id;
    private String title;
    private String authorName;
    private int authorId;
    private Timestamp time;
    private String titleImg;
    private String content;

}
