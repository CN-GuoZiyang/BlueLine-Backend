package top.guoziyang.bluelinebackend.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@ToString
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "parent_article")
    private Integer parentArticle;

    @Column(name = "parent_comment")
    private Integer parentComment;

    @Column(name = "author")
    private Integer author;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "content")
    private String content;

}
