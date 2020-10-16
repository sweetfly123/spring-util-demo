package com.example.rabbitmq;

import com.example.rabbitmq.dto.RabbitMqMsgDto;
import com.example.rabbitmq.rabbitmq.RabbitMqInit;
import com.example.rabbitmq.utils.Json;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class RabbitmqApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("sdf");
    }

}
