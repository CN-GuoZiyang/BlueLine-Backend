package top.guoziyang.bluelinebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PreviousAndNextArticleDto implements Serializable {

    private Integer id;
    private String title;

}
