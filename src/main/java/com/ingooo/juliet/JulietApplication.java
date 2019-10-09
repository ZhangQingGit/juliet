package com.ingooo.juliet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = {
        "com.ingooo.juliet",
        "com.ingooo.websocket.util"
})
@MapperScan("com.ingooo.juliet.mapper")
@Configuration
public class JulietApplication {

    public static void main(String[] args) {
        SpringApplication.run(JulietApplication.class, args);
    }

}
