package top.guoziyang.bluelinebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("top.guoziyang.bluelinebackend.mapper")
public class BluelineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluelineBackendApplication.class, args);
    }

}
