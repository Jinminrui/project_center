package com.jmr.projectcenter.service.rocketmq;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.jmr.projectcenter.dao.activity.ActivityMapper;
import com.jmr.projectcenter.domain.entity.activity.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddActivityListener implements MessageListener {
    /**
     * 由于消费者是多线程的，所以对象要用static+set注入，把对象的级别提升到进程，
     * 这样多个线程就可以共用，但是无法调用父类的方法和变量
     */
	protected static ActivityMapper activityMapper;

    @Resource
    public void setTestDao(ActivityMapper activityMapper){
    	AddActivityListener.activityMapper = activityMapper;
    }

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        try {
            byte[] body = message.getBody();
            String msg = new String(body);//获取到接收的消息，由于接收到的是byte数组，所以需要转换成字符串

            //TODO 业务逻辑，自行设计
            log.info(msg);
            Activity activity = JSON.toJavaObject(JSON.parseObject(msg), Activity.class);
            activityMapper.insertSelective(activity);

        } catch (Exception e) {
           e.printStackTrace();
        }
        //如果想测试消息重投的功能,可以将Action.CommitMessage 替换成Action.ReconsumeLater
        return Action.CommitMessage;
    }
}
