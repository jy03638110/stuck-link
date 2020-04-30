package top.stuck.link.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = "top.stuck.link")
@MapperScan(basePackages = "top.stuck.link")
public class StuckLinkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuckLinkAdminApplication.class, args);
    }

}
