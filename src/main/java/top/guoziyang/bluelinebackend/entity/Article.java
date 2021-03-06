package top.guoziyang.bluelinebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Integer id;

    private String title;

    private Integer author;

    private Timestamp time;

    private String titleImg;

    private String summary;

    private String content;

    private Boolean enable;

}
