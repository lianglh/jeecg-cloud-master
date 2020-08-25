package org.jeecg.modules.test.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.modules.test.entity.TestOrderMain;
import org.jeecg.modules.test.entity.TestOrderProduct;
import org.jeecg.modules.test.service.ITestOrderMainService;
import org.jeecg.modules.test.service.ITestOrderProductService;
import org.jeecg.modules.test.vo.TestOrderMainPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 测试订单主表
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
@Api(tags="测试订单主表")
@RestController
@RequestMapping("/order/testOrderMain")
@Slf4j
@EnableBinding(Source.class)
public class TestOrderMainController {
	@Autowired
	private ITestOrderMainService testOrderMainService;
	@Autowired
	private ITestOrderProductService testOrderProductService;


	 @Autowired
     @Output(Source.OUTPUT)
     private MessageChannel channel;
	/**
	 * 分页列表查询
	 *
	 * @param testOrderMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测试订单主表-分页列表查询")
	@ApiOperation(value="测试订单主表-分页列表查询", notes="测试订单主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestOrderMain testOrderMain,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestOrderMain> queryWrapper = QueryGenerator.initQueryWrapper(testOrderMain, req.getParameterMap());
		Page<TestOrderMain> page = new Page<TestOrderMain>(pageNo, pageSize);
		IPage<TestOrderMain> pageList = testOrderMainService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 /**
	  *   添加
	  *
	  * @param testOrderMainPage
	  * @return
	  */
	 @PostMapping(value = "/addTest")
	 public Result<?> addTest(@RequestParam String testOrderMainPage,HttpServletRequest request) throws InterruptedException, ExecutionException {
		 System.out.println(testOrderMainPage);
		 System.out.println("11111111111111111");
		 return Result.ok();
	 }
		 /**
          *   添加
          *
          * @param testOrderMainPage
          * @return
          */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestOrderMainPage testOrderMainPage,HttpServletRequest request) throws InterruptedException, ExecutionException {
		/**
		 *
		 */
        ExecutorService executorService = Executors.newFixedThreadPool(100);

		/**
		 * 平滑地关闭线程池，已经提交到线程池中的任务会继续执行完。
		 * executorService.shutdown();
		 */
		/**
		 * 立即关闭线程池，返回还没有开始执行的任务列表。
		 * 会尝试中断正在执行的任务（每个线程调用 interruput方法），但这个行为不一定会成功。
		 * shutdownNow();
		 */
		/**
		 * 判断线程池是否已经关闭
		 * isShutdown();
		 */
		/**
		 * 判断线程池的任务是否已经执行完毕。
		 * 注意此方法调用之前需要先调用shutdown()方法或者shutdownNow()方法，否则总是会返回false
		 * isTerminated();
		 */
		/**
		 * 判断线程池的任务是否都执行完。
		 * 如果没有任务没有执行完毕则阻塞，直至任务完成或者达到了指定的timeout时间就会返回
		 * awaitTermination(long timeout, TimeUnit unit)
		 */
		/**
		 * 提交带有一个返回值的任务到线程池中去执行（回调），返回的 Future 表示任务的待定结果。
		 * 当任务成功完成后，通过 Future 实例的 get() 方法可以获取该任务的结果。
		 * Future 的 get() 方法是会阻塞的。
		 * submit(Callable<T> task);
		 */
		/**
		 *提交一个Runnable的任务，当任务完成后，可以通过Future.get()获取的是提交时传递的参数T result
		 *submit(Runnable task, T result);
		 */
		/**
		 * 提交一个Runnable的人无语，它的Future.get()得不到任何内容，它返回值总是Null。
		 * 为什么有这个方法？为什么不直接设计成void submit(Runnable task)这种方式？
		 * 这是因为Future除了get这种获取任务信息外，还可以控制任务，
		 具体体现在 Future的这个方法上：boolean cancel(boolean mayInterruptIfRunning)
		 这个方法能够去取消提交的Rannable任务。
		 submit(Runnable task);
		 */
		/**
		 * 执行一组给定的Callable任务，返回对应的Future列表。列表中每一个Future都将持有该任务的结果和状态。
		 * 当所有任务执行完毕后，方法返回，此时并且每一个Future的isDone()方法都是true。
		 * 完成的任务可能是正常结束，也可以是异常结束
		 * 如果当任务执行过程中，tasks集合被修改了，那么方法的返回结果将是不确定的，
		 即不能确定执行的是修改前的任务，还是修改后的任务
		 invokeAll(Collection<? extends Callable<T>> tasks)
		 */
		/**
		 * 执行一组给定的Callable任务，返回对应的Future列表。列表中每一个Future都将持有该任务的结果和状态。
		 * 当所有任务执行完毕后或者超时后，方法将返回，此时并且每一个Future的isDone()方法都是true。
		 * 一旦方法返回，未执行完成的任务被取消，而完成的任务可能正常结束或者异常结束，
		 * 完成的任务可以是正常结束，也可以是异常结束
		 * 如果当任务执行过程中，tasks集合被修改了，那么方法的返回结果将是不确定的
		 * invokeAll(Collection<? extends Callable<T>> tasks,
		 *                                   long timeout, TimeUnit unit)
		 */
		/**
		 * 执行一组给定的Callable任务，当成功执行完（没抛异常）一个任务后此方法便返回，返回的是该任务的结果
		 * 一旦此正常返回或者异常结束，未执行的任务都会被取消。
		 * 如果当任务执行过程中，tasks集合被修改了，那么方法的返回结果将是不确定的
		 * invokeAny(Collection<? extends Callable<T>> tasks)
		 */
		/**
		 * 执行一组给定的Callable任务，当在timeout（超时）之前成功执行完（没抛异常）一个任务后此方法便返回，返回的是该任务的结果
		 * 一旦此正常返回或者异常结束，未执行的任务都会被取消。
		 * 如果当任务执行过程中，tasks集合被修改了，那么方法的返回结果将是不确定的
		 * invokeAny(Collection<? extends Callable<T>> tasks,
		 *                     long timeout, TimeUnit unit)
		 */

		for (int i = 0; i < Integer.parseInt(testOrderMainPage.getOrderCode()); i++) {


		//	pipe.output().send(MessageBuilder.withPayload(testOrderProduct).setHeader("request",request).build());

		}
       Set<Callable<Object>> callables = new HashSet<Callable<Object>>();
		for (int i = 0; i < Integer.parseInt(testOrderMainPage.getOrderCode()); i++) {
			int finalI = i;
			callables.add(new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					TestOrderProduct testOrderProduct=new TestOrderProduct();
					testOrderProduct.setNum(finalI);

					channel.send(MessageBuilder.withPayload(testOrderProduct).build());
					return null;
				}
			});
		}
		//执行并返回多线程结果
        List<Future<Object>> futureList = executorService.invokeAll(callables);
        //关闭线程池
        executorService.shutdown();
        //查看各线程执行情况
		/**
		 * Future提供了三种功能：
		 * 判断任务是否完成； isDone
		 * 能够中断任务； cancel方法 isCancelled方法：表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
		 * 能够获取任务执行结果 get()方法：用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
		 */
        for (Future<Object> future : futureList) {
        	System.out.println(future.get());
        }
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testOrderMainPage
	 * @return
	 */
	@AutoLog(value = "测试订单主表-编辑")
	@ApiOperation(value="测试订单主表-编辑", notes="测试订单主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestOrderMainPage testOrderMainPage) {
		TestOrderMain testOrderMain = new TestOrderMain();
		BeanUtils.copyProperties(testOrderMainPage, testOrderMain);
		TestOrderMain testOrderMainEntity = testOrderMainService.getById(testOrderMain.getId());
		if(testOrderMainEntity==null) {
			return Result.error("未找到对应数据");
		}
		testOrderMainService.updateMain(testOrderMain, testOrderMainPage.getTestOrderProductList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试订单主表-通过id删除")
	@ApiOperation(value="测试订单主表-通过id删除", notes="测试订单主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testOrderMainService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试订单主表-批量删除")
	@ApiOperation(value="测试订单主表-批量删除", notes="测试订单主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testOrderMainService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试订单主表-通过id查询")
	@ApiOperation(value="测试订单主表-通过id查询", notes="测试订单主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestOrderMain testOrderMain = testOrderMainService.getById(id);
		if(testOrderMain==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(testOrderMain);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单产品明细通过主表ID查询")
	@ApiOperation(value="订单产品明细主表ID查询", notes="订单产品明细-通主表ID查询")
	@GetMapping(value = "/queryTestOrderProductByMainId")
	public Result<?> queryTestOrderProductListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestOrderProduct> testOrderProductList = testOrderProductService.selectByMainId(id);
		return Result.ok(testOrderProductList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testOrderMain
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestOrderMain testOrderMain) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TestOrderMain> queryWrapper = QueryGenerator.initQueryWrapper(testOrderMain, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TestOrderMain> queryList = testOrderMainService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TestOrderMain> testOrderMainList = new ArrayList<TestOrderMain>();
      if(oConvertUtils.isEmpty(selections)) {
          testOrderMainList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          testOrderMainList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TestOrderMainPage> pageList = new ArrayList<TestOrderMainPage>();
      for (TestOrderMain main : testOrderMainList) {
          TestOrderMainPage vo = new TestOrderMainPage();
          BeanUtils.copyProperties(main, vo);
          List<TestOrderProduct> testOrderProductList = testOrderProductService.selectByMainId(main.getId());
          vo.setTestOrderProductList(testOrderProductList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "测试订单主表列表");
      mv.addObject(NormalExcelConstants.CLASS, TestOrderMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("测试订单主表数据", "导出人:"+sysUser.getRealname(), "测试订单主表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<TestOrderMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestOrderMainPage.class, params);
              for (TestOrderMainPage page : list) {
                  TestOrderMain po = new TestOrderMain();
                  BeanUtils.copyProperties(page, po);
                  testOrderMainService.saveMain(po, page.getTestOrderProductList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
