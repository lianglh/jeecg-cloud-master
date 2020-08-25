package org.jeecg.modules.test.tets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceShutdown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadpool = Executors.newFixedThreadPool(4);
        //将4个任务提交到有4个线程的线程池
        threadpool.submit(new ShortTask());
        threadpool.submit(new ShortTask());
        threadpool.submit(new LongTask());
        threadpool.submit(new ShortTask());

        //关闭线程池
        threadpool.shutdown();

        boolean isShutdown = threadpool.isShutdown();
        System.out.println("线程池是否已经关闭：" + isShutdown);

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (!threadpool.awaitTermination(1L, TimeUnit.SECONDS)) {
            System.out.println("线程池中还有任务在执行，当前时间：" + sdf.format(new Date()));
        }

        System.out.println("线程池中已经没有在执行的任务，线程池已完全关闭！");
    }
}
class LongTask implements Runnable {


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("complete a long task");
    }

}
class ShortTask implements Runnable {

    @Override
    public void run() {
        System.out.println("complete a short task...");
    }

}