package com.jmr.projectcenter.rocketmq;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.jmr.projectcenter.service.rocketmq.AddActivityListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class RocketMQConsumer {
    @Value("${rocketmq.consumerId}")
    private String consumerId;

    @Value("${rocketmq.accessKey}")
    private String accessKey;

    @Value("${rocketmq.secretKey}")
    private String secretKey;

    @Value("${rocketmq.onsAddr}")
    private String ONSAddr;

    @Value("${rocketmq.msgTopic}")
    private String msgTopic;

    private static Consumer consumer;

    @PostConstruct
    public void init(){
        System.out.println("初始化启动消费者！");
        // consumer 实例配置初始化
        Properties properties = new Properties();
        //您在控制台创建的consumer ID
        properties.setProperty(PropertyKeyConst.GROUP_ID, consumerId);
        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        //设置发送超时时间，单位毫秒
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        // 设置 TCP 接入域名(此处以公共云生产环境为例)，设置 TCP 接入域名，进入 MQ 控制台的消费者管理页面，在左侧操作栏单击获取接入点获取
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, ONSAddr);
        consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(msgTopic, "*", new AddActivityListener());//监听第一个topic，new对应的监听器
        // 在发送消息前，必须调用start方法来启动consumer，只需调用一次即可，当项目关闭时，自动shutdown
        consumer.start();

    }

    /**
     * 初始化消费者
     * @return
     */
    public Consumer getconsumer(){
        return consumer;
    }
}
