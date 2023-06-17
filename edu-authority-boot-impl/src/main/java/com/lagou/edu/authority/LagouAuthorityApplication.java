package com.lagou.edu.authority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * LagouAuthorityApplication
 *
 * @author xianhongle
 * @data 2022/4/18 11:23 下午
 **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lagou.edu.authority.mapper")
public class LagouAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LagouAuthorityApplication.class,args);
    }

}
