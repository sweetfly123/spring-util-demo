package com.example.rabbitmq.controller;

import com.example.rabbitmq.dto.DefaultResult;
import com.example.rabbitmq.dto.RabbitMqMsgDto;
import com.example.rabbitmq.rabbitmq.MsgProducer;
import com.example.rabbitmq.rabbitmq.RabbitMqInit;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 *功能描述
 * @author Barret
 * @date 10/14/2020
 * @return
 */
@RestController
@RequestMapping("/ignore/mq")
@Api("测试mq接口")
@Slf4j
public class RabbitMqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "发送mq消息")
    @PostMapping(value = "/sendMq")
    public DefaultResult<RabbitMqMsgDto> sendMq(String key) {
        RabbitMqMsgDto rabbitMqMsgDto = new RabbitMqMsgDto();
        rabbitMqMsgDto.setMsgBody(key);
        try {
            //发送消息到 directExchange
            rabbitTemplate.convertAndSend( RabbitMqInit.DIRECT_EXCHANGE_CLOUD, RabbitMqInit.ROUTINGKEY_DIRECT_CLOUD, rabbitMqMsgDto);
            System.out.println("发送消息到 directExchange");
            //发送消息到 fanoutExchange
            CorrelationData correlationData = new CorrelationData();
            String id = UUID.randomUUID().toString();
            correlationData.setId(id);
//            rabbitTemplate.convertAndSend(RabbitMqInit.FANOUT_EXCHANGE_CLOUD, "", rabbitMqMsgDto, correlationData);
            System.out.println("发送消息到 fanoutExchange id:" + id);
            return DefaultResult.success(rabbitMqMsgDto);
        } catch (AmqpException e) {//到了重连次数了，还是没连上怎么办呢？造成这种情况通常是服务器宕机等环境问题，这时候会报AmqpException，我们可以捕获这个异常，然后把消息存入缓存中。等环境正常后，做消息补发。
            // 存缓存操作
            System.out.println(e.getMessage() + "发送失败:原因重连10次都没连上。");
            return DefaultResult.fail();
        }
    }

    @ApiOperation(value = "发送mq消息")
    @PostMapping(value = "/sendMqPrototype")
    public DefaultResult<RabbitMqMsgDto> sendMqPrototype(String data) {
        try {
            RabbitMqMsgDto rabbitMqMsgDto = new RabbitMqMsgDto();
            rabbitMqMsgDto.setMsgBody(data);
            msgProducer.sendMsg(rabbitMqMsgDto);
            return DefaultResult.success(rabbitMqMsgDto);
        } catch (AmqpException e) {
            //到了重连次数了，还是没连上怎么办呢？造成这种情况通常是服务器宕机等环境问题，这时候会报AmqpException，我们可以捕获这个异常，然后把消息存入缓存中。等环境正常后，做消息补发。
            // 存缓存操作
            System.out.println(e.getMessage() + "发送失败:原因重连10次都没连上。");
            return DefaultResult.fail();
        }
    }

}
