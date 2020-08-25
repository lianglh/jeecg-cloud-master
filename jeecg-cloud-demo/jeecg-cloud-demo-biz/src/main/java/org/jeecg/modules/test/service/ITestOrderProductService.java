package org.jeecg.modules.test.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.test.entity.TestOrderProduct;

import java.util.List;

/**
 * @Description: 订单产品明细
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
public interface ITestOrderProductService extends IService<TestOrderProduct> {

	public List<TestOrderProduct> selectByMainId(String mainId);
}
