### 关于消息确认机制
* channel.basicAck(deliveryTag, false);
    ``` 
    deliveryTag:该消息的index
    multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。
  ```

* channel.basicNack(deliveryTag, false, true);
    ```
    deliveryTag:该消息的index
    multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
    requeue：被拒绝的是否重新入队列
  ```


* channel.basicReject(deliveryTag:, false);
    ```
    deliveryTag:该消息的index
    requeue：被拒绝的是否重新入队列
    ```

### 多个消费者，并发削峰

* 工厂类
```text
    /**
     * 多个消费者
     * 如果消息属性中没有指定content_type，则接收消息的处理方法接收类型是byte[],如果消息属性中指定content_type为text，则接收消息的处理方法
     * 的参数类型是String类型。不管有没有指定content_type，处理消息方法的参数类型是Message都不会报错。
     * factory.setMessageConverter(new Jackson2JsonMessageConverter()); 发送的消息将会使用它来序列化
     * factory.setAcknowledgeMode(AcknowledgeMode.MANUAL); 必须开启手动确认模式
     * factory.setConcurrentConsumers(10); 和 factory.setMaxConcurrentConsumers(15); 这个表示消费者的数量, 也就是消费多线程运行的线程数目.
     * factory.setPrefetchCount(5); 每次取的消息的数目, 数目大效率高, 但是顺序越得不到保证
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 多消费者进行手工确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 默认消费者数量
        factory.setConcurrentConsumers(10);
        // 最大消费者数量
        factory.setMaxConcurrentConsumers(15);
        // 最大投递数
        factory.setPrefetchCount(5);
        return factory;
    }
```

* 消费者使用该工厂类
```text
@RabbitListener(queues = "testQueue" containerFactory = "multiListenerContainer")
    public void recievedString(Message msg, Channel channel) throws Exception {
        try {
            log.info("来源事务落地通用消费者{}收到消息", channel.getChannelNumber());
            PaTransactionTask task = JSON.parseObject(msg.getBody(), PaTransactionTask.class);
            log.info("来源事务落地通用消费者{}解析消息:{}", channel.getChannelNumber(), task.getPlanControlId());
            // 这里最好添加重复执行判断
            // 处理来源事务
            runner.excuteTransaction(task);
        } catch (Exception e) {
            log.info("来源事务落地通用消费者{}出错", channel.getChannelNumber());
            e.printStackTrace();
        }
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
        log.info("来源事务落地通用消费者{}确认消", channel.getChannelNumber());
    }
```