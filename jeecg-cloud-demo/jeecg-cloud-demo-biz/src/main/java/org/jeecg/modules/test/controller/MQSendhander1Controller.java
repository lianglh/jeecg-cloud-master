package org.jeecg.modules.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.test.entity.TestOrderProduct;
import org.jeecg.modules.test.service.ITestOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
* 使用Processor，则当前服务必须同时实现生产者和消费者，不然会报错
* MQ推送消息
*/

@Slf4j
/*@EnableBinding(Sink.class)*/
@Component
public class MQSendhander1Controller {
   /* @StreamListener(Processor.INPUT)*/
    public void process(String testOrderProduct){
        HttpServletRequest request1 = SpringContextUtils.getHttpServletRequest();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


    }

}