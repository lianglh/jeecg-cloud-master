package org.jeecg.modules.mq.client;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.ServiceNameConstants;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.TestOrderProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(contextId = "mqServiceClient", value =ServiceNameConstants.DEMO_SERVICE)
public interface MqServiceClient {

    @PostMapping("/order/testOrderMain/addTest")
    void addTestOrderProduct(@PathVariable("testOrderMainPage") String testOrderMainPage);
}
