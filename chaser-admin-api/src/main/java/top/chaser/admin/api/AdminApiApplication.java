package top.chaser.admin.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.chaser.framework.common.base.util.JSONUtil;

import java.util.Properties;

@SpringBootApplication
@Slf4j
public class AdminApiApplication {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        log.error(JSONUtil.toPrettyString(properties));
        SpringApplication.run(AdminApiApplication.class, args);
    }
}
