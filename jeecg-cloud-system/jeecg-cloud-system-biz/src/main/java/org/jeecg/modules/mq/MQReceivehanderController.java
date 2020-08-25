package org.jeecg.modules.mq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.TestOrderProduct;
import org.jeecg.modules.mq.client.MqServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* 使用Processor，则当前服务必须同时实现生产者和消费者，不然会报错
* MQ推送消息
*/

@Slf4j
@EnableBinding(Sink.class)
@Component
public class MQReceivehanderController {
    @Autowired
    MqServiceClient mqServiceClient;
    SimpleDateFormat sa = new SimpleDateFormat("yyyy年MM月dd日 E HH:mm:ss");
    int anInt = 0;
    @StreamListener(Processor.INPUT)
    public void process(String testOrderProduct){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
//        TestOrderProduct sdCardResponse = gson.fromJson(testOrderProduct,TestOrderProduct.class);
        mqServiceClient.addTestOrderProduct(testOrderProduct);
        System.out.println("开始"+sa.format(new Date()));
        System.out.println(testOrderProduct);
        anInt++;
        System.out.println(anInt);
        System.out.println("结束"+sa.format(new Date()));

    }

}