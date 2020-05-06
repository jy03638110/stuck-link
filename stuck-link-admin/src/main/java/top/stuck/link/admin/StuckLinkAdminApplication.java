package top.stuck.link.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.stuck.link")
@MapperScan("top.stuck.link.*.mapper")
public class StuckLinkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuckLinkAdminApplication.class, args);
    }

}
