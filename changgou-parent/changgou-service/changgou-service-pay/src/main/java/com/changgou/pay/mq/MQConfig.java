package com.changgou.pay.mq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Dylan Guo
 * @date 10/16/2020 12:04
 * @description TODO
 */
@Configuration
public class MQConfig {

    /**
     * 读取配置文件中的对象
     */
    @Autowired
    private Environment env;

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(env.getProperty("mq.pay.queue.order"));
    }

    /**
     * 创建交换机
     *
     * @return
     */
    public Exchange orderExchange() {
        return new DirectExchange(env.getProperty("mq.pay.exchange.order"), true, false);
    }

    /**
     * 队列绑定交换机
     *
     * @param orderQueue
     * @param orderExchange
     * @return
     */
    public Binding orderQueueExchange(Queue orderQueue, Exchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(env.getProperty("mq.pay.routing.order")).noargs();
    }
}
