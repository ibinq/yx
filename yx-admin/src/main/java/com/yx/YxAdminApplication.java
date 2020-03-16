package com.yx;

import com.yx.config.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;

@SpringBootApplication
@Slf4j
public class YxAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxAdminApplication.class, args);
        printProjectConfigs();
    }

    private static void printProjectConfigs() {
        ServerProperties serverProperties = SpringContextHolder.getApplicationContext().getBean(ServerProperties.class);
        DataSourceProperties dataSourceProperties = SpringContextHolder.getApplicationContext()
                .getBean(DataSourceProperties.class);
        log.info("接口文档地址：http://127.0.0.1:{}", serverProperties.getPort() + "/doc.html");
        log.info("接口地址     http://localhost:{}   ", serverProperties.getPort());
    }

}
