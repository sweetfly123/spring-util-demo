package com.example.redisdistribute;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Barret
 * @description
 * @date 10/29/2020
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Qualifier("getRedissionClient")
    @Autowired
    private RedissonClient redissonClient;

    //通过setnx设置分布式锁
    @GetMapping("/lock/test")
    public String test() {
        String lockKey = "lock01";
        try {
            //setIfAbsent封装的就是setnx，当设置成功，则返回true，失败则返回false
            Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, "001", 10, TimeUnit.SECONDS);
            //如果未设置成功，则代表当前线程
            if (!result) {
                return "error";
            }
            //从redis中获取数据
            int number = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("num")));
            if (number > 0) {
                int realnumber = number - 1;
                System.out.println("还有数量，为：" + number);

            } else {
                System.out.println("数量为0");
            }
        } finally {
            //删除该值，释放锁
            redisTemplate.delete(lockKey);
        }
        return "end";
    }

    //通过setnx设置分布式锁
    @GetMapping("/lock/redission/test")
    public String testRedission() {
        String lockKey = "lock02";
        //获得锁
        RLock lock = redissonClient.getLock(lockKey);
        try {
            //设置有效时间，redisson这里面会自动配置看门狗
            lock.lock(10, TimeUnit.SECONDS);
            int number = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get("num")));
            if (number > 0) {
                int realnumber = number - 1;
                System.out.println("还有数量，为：" + realnumber);
                redisTemplate.opsForValue().set("num", realnumber + "");
            } else {
                System.out.println("数量为0");
            }
        } finally {
            lock.unlock();
        }
        return "end";
    }
}
