package org.jeecg.modules.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.test.entity.TestOrderProduct;

import java.util.List;

/**
 * @Description: 订单产品明细
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
public interface TestOrderProductMapper extends BaseMapper<TestOrderProduct> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TestOrderProduct> selectByMainId(@Param("mainId") String mainId);
}
