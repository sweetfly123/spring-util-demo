package com.example.rabbitmq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 初始化队列，交换机，然后绑定队列到指定交换机上
 *
 * @author Barret
 * @date 5/20/2020
 * @return
 */
@Configuration
public class RabbitMqInit {
    /**
     * 队列名称
     */
    public static final String QUEUE_DIRECT_CLOUD = "queue.direct_cloud";
    public static final String QUEUE_FANOUT_CLOUD = "queue.fanout_cloud";
    /**
     * 死信队列
     */
    public static final String QUEUE_DEAD_CLOUD = "queue.dead_cloud";

    /**
     * 设置路由key
     */
    public static final String ROUTINGKEY_DIRECT_CLOUD = "rk.direct_cloud";
    /**
     * 死信队列路由key
     */
    public static final String ROUTINGKEY_DEAD_CLOUD = "rk.dead_cloud";

    /**
     * 交换空间名称(点对点)
     */
    public static final String DIRECT_EXCHANGE_CLOUD = "exchange.direct";
    /**
     * 交换空间名称(广播)
     */
    public static final String FANOUT_EXCHANGE_CLOUD = "exchange.fanout";

    /**
     * 交换空间名称(话题)
     */
    public static final String TOPIC_EXCHANGE_CLOUD = "exchange.topic";

    /**
     * direct 形式的 死信路由
     */
    public static final String DIRECT_EXCHANGE_DEAD_CLOUD = "exchange.dead.direct";


    /**
     * 点对点模式 交换机
     */
    @Bean
    public DirectExchange directExchange() {
        //交换器名称、是否持久化、是否自动删除
        return new DirectExchange(DIRECT_EXCHANGE_CLOUD, true, false);
    }

    /**
     * 广播模式  交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_CLOUD, true, false);
    }

    /**
     * 点对点模式 死信路由 交换机
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_DEAD_CLOUD, true, false);
    }

    /**
     * 动态路游 交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_CLOUD, true, false);
    }

    /**
     * 声明队列名称
     */
    @Bean(name = "direct")
    public Queue direct() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", DIRECT_EXCHANGE_DEAD_CLOUD);
        //direct队列消息处理失败后，就发送到死信队列中处理，该处绑定死信队列
        args.put("x-dead-letter-routing-key", ROUTINGKEY_DEAD_CLOUD);
        return new Queue(QUEUE_DIRECT_CLOUD, true, false, false, args);
    }

    @Bean(name = "fanout")
    public Queue fanout() {
        return new Queue(QUEUE_FANOUT_CLOUD);
    }

    @Bean(name = "dead")
    public Queue dead() {
        return new Queue(QUEUE_DEAD_CLOUD);
    }

    /**
     * 将队列通过路由key到绑定交互机上DirectExchange
     */
    @Bean
    public Binding bindingDirectExchangeQueue(DirectExchange directExchange, @Qualifier("direct") Queue direct) {
        return BindingBuilder.bind(direct).to(directExchange).with(ROUTINGKEY_DIRECT_CLOUD);
    }

    /**
     * 将队列通过路由key到绑定交互机上FanoutExchange
     */
    @Bean
    public Binding bindingFanoutExchangeQueue(FanoutExchange fanoutExchange, @Qualifier("fanout") Queue fanout) {
        return BindingBuilder.bind(fanout).to(fanoutExchange);
    }

    /**
     * 将队列通过路由key到绑定交互机上deadExchange
     */
    @Bean
    public Binding bindingDeadExchangeQueue(@Qualifier("deadExchange") DirectExchange deadExchange, @Qualifier("dead") Queue dead) {
        return BindingBuilder.bind(dead).to(deadExchange).with(ROUTINGKEY_DEAD_CLOUD);
    }
}
