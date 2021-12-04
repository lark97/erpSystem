package cn.lark.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: erp-manage
 * @description: 启动类
 * @author: lark
 * @create: 2021-12-02 17:04
 **/
@SpringBootApplication
@MapperScan("cn.lark.admin.mapper")
public class erpAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(erpAdminApplication.class,args);
    }
}
