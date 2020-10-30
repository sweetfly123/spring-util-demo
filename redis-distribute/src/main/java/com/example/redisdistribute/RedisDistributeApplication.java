package com.example.redisdistribute;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisDistributeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDistributeApplication.class, args);
    }

    @Bean
    public Redisson getRedission(){
        //单机redis模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.15.60.205:7001").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

    @Bean
    public RedissonClient getRedissionClient(){
        //单机redis模式
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress("redis://172.15.60.206:7003")
                .setPassword("18Mhero");
//        ,"172.15.60.206:7003","172.15.60.207:7005");
        RedissonClient redissonClient = Redisson.create(config);
//        config.useSingleServer().setAddress("172.15.60.205:7001").setDatabase(0);
        return redissonClient;
    }
}
