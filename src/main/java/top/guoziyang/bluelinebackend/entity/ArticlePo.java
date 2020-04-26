package top.guoziyang.bluelinebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePo {

    private Integer id;
    private String title;
    private String authorName;
    private Integer authorId;
    private Timestamp time;
    private String content;

}
