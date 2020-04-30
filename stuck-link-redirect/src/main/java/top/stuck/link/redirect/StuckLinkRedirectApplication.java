package top.stuck.link.redirect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "top.stuck.link")
@MapperScan("top.stuck.link.*.mapper")
public class StuckLinkRedirectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StuckLinkRedirectApplication.class, args);
    }

}
