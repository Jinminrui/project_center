package com.jmr.projectcenter.service.rocketmq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.jmr.projectcenter.rocketmq.RocketMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RocketMQProducerService {
    @Value("${rocketmq.msgTopic}")
    private String msgTopic;

    private final RocketMQProducer rocketMQProducer;

    public String tag = "*";

    /**
     * 同步发送实体对象消息
     * 可靠同步发送：同步发送是指消息发送方发出数据后，会在收到接收方发回响应之后才发下一个数据包的通讯方式；
     * 特点：速度快；有结果反馈；数据可靠；
     * 应用场景：应用场景非常广泛，例如重要通知邮件、报名短信通知、营销短信系统等；
     */
    public void sendMsg(String msg, String key) {
        Long startTime = System.currentTimeMillis();
        Message message = new Message(msgTopic,tag, msg.getBytes());
        message.setKey(key);
        try {
            SendResult sendResult = rocketMQProducer.getProducer().send(message);
            if (sendResult != null) {
                log.info(new Date() + " Send mq message success. Topic is:" + message.getTopic());
            } else {
                log.warn(".sendResult is null.........");
            }
        } catch (ONSClientException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("单次生产耗时：" + (endTime - startTime) / 1000);
    }

    /**
     * 异步发送消息
     * 可靠异步发送：发送方发出数据后，不等接收方发回响应，接着发送下个数据包的通讯方式；
     * 特点：速度快；有结果反馈；数据可靠；
     * 应用场景：异步发送一般用于链路耗时较长,对 rt响应时间较为敏感的业务场景,例如用户视频上传后通知启动转码服务,转码完成后通知推送转码结果等；
     *
     * @param msg
     * @return
     */
    public boolean sendMsgAsy(String msg,String key) {
        Long startTime = System.currentTimeMillis();
        Message message = new Message(msgTopic, tag, msg.getBytes());
        message.setKey(key);
        rocketMQProducer.getProducer().sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                ///消息发送成功
                log.info("send message success. topic=" + sendResult.getMessageId());
            }

            @Override
            public void onException(OnExceptionContext context) {
                //消息发送失败
                log.warn("send message failed. execption=" + context.getException());
            }
        });
        Long endTime = System.currentTimeMillis();
        log.info("单次生产耗时：" + (endTime - startTime) / 1000);
        return true;
    }

    /**
     * 单向发送
     * 单向发送：只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答；此方式发送消息的过程耗时非常短，一般在微秒级别；
     * 特点：速度最快，耗时非常短，毫秒级别；无结果反馈；数据不可靠，可能会丢失；
     * 应用场景：适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集；
     *
     * @return
     */
    public boolean sendMsgOneway(String msg) {
        Long startTime = System.currentTimeMillis();
        Message message = new Message(msgTopic, tag, msg.getBytes());
        rocketMQProducer.getProducer().sendOneway(message);
        Long endTime = System.currentTimeMillis();
        log.info("单次生产耗时：" + (endTime - startTime) / 1000);
        return true;
    }
}
