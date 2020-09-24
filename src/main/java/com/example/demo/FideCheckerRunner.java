package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.util.StringUtil;

import java.net.InetAddress;

@Slf4j
@Component
@RequiredArgsConstructor
public class FideCheckerRunner implements ApplicationRunner {

    @Value("${server.port:8080}")
    private int port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    private final ConfigurableApplicationContext context;

    @Qualifier("mysqlJdbcTemplate")
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        boolean flag = false;
        try {
            jdbcTemplate.execute("SELECT 1");
            flag = true;
        } catch (Exception e) {
            log.error("系统启动失败", e);
        } finally {
            if (!flag) {
                System.out.println("\n"
                                                  + "┌─┐┬┌┬┐┌─┐  ┌─┐┌┬┐┌─┐┌─┐┌─┐┌┬┐\n"
                                                  + "├┤ │ ││├┤   └─┐ │ │ │├─┘├┤  ││\n"
                                                  + "└  ┴─┴┘└─┘  └─┘ ┴ └─┘┴  └─┘─┴┘\n");
                System.out.println("");

                // 优雅停机
                context.close();
            }
        }

        if (flag) {
            System.out.println("\n"
                                             + "┌─┐┬┌┬┐┌─┐  ┌─┐┌┬┐┌─┐┬─┐┌┬┐┌─┐┌┬┐\n"
                                             + "├┤ │ ││├┤   └─┐ │ ├─┤├┬┘ │ ├┤  ││\n"
                                             + "└  ┴─┴┘└─┘  └─┘ ┴ ┴ ┴┴└─ ┴ └─┘─┴┘\n");

            InetAddress address = InetAddress.getLocalHost();
            StringBuilder url = new StringBuilder();
            url.append(String.format("http://%s:%s", address.getHostAddress(), port));
            if (context.isActive()) {
                if (StringUtil.isNotEmpty(contextPath)) {
                    url.append(contextPath);
                }
            }

            System.out.println("系统启动成功，访问地址：" + url);
            System.out.println("");
        }
    }

}

