package top.guoziyang.bluelinebackend.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@ToString
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private Integer author;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "title_img")
    private String titleImg;

    @Column(name = "summary")
    private String summary;

    @Column(name = "content")
    private String content;

    @Column(name = "enable")
    private Boolean enable;

}
