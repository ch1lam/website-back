package cn.chilam.websiteback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.chilam.websiteback.mapper")
@SpringBootApplication
public class WebsiteBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteBackApplication.class, args);
    }

}
