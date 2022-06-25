package com.itwang.lottery;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:06:25  14:02
 */
@SpringBootApplication
@EnableDubbo
@Configurable
public class LotteryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotteryApplication.class, args);
    }
}
