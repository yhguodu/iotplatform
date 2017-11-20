package org.yhguodu.iot.portal.controller;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yhguodu.iot.portal.hystrix.CommandHelloWorld;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yhguodu on 2017/11/14.
 */

@RestController
@RequestMapping("/portal")
public class PortController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        String[]  array = "hello, this is my iot portal".split(" ");
        final StringBuilder sb = new StringBuilder() ;
        Observable.from(array)
                .filter((s)->"iot".equals(s))
                .subscribe((s)-> sb.append(s)
                );

        CommandHelloWorld helloWorld = new CommandHelloWorld(sb.toString());
        helloWorld.toObservable().subscribe((s)->System.out.println(s));
        return sb.toString();
    }
//    //补充：假定支付方式可用性咨询接口统一为
//    ConsultResult  isEnabled(String paymentType) {return null;}
//
//    //返回结果
//    public class ConsultResult {
//
//        /** 咨询结果是否可用*/
//        private boolean isEnable;
//
//        /** 错误码 */
//        private String errorCode;
//
//    }
//
//
//    /**
//     * 过滤不可用支付方式类型
//     * @param paymentTypeList 原始支付方式类型列表
//     * @return 可用支付方式类型列表
//     */
//    ExecutorService executorService;
//    ExecutorCompletionService<String> completionService;
//    AtomicBoolean isStarted = new AtomicBoolean(false);
//    AtomicLong startTime = new AtomicLong(System.currentTimeMillis());
//    Future<List<String>> enabledPaymentList;
//
//    public void init() {
//        //get the configuration
//        int corePoolSize = dynamicProperties.getIntProperty("corePoolSize",10);
//        int maxPoolSize = dynamicProperties.getIntProperty("maxPoolSize",15);
//        int keepAliveTime = dynamicProperties.getIntProperty("keepAliveTime",60);
//        int queueSize = dynamicProperties.getIntProperty("queueSize",20);
//        int givenTime = dynamicProperties.getIntProperty("givenTime",60);
//
//        //create the thread pool
//        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueSize), new ThreadFactory() {
//            AtomicInteger index = new AtomicInteger(0);
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread("thread_index_" + index.getAndIncrement());
//            }
//        });
//        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
//    }
//
//    public void getEnablePaymentsInGivingTime(final List<String> allPaymentList) {
//        enabledPaymentList = executorService.submit(()->{
//            //submit the task
//            for (final String payType : allPaymentList) {
//                completionService.submit(()-> {
//                    ConsultResult result =isEnabled(payType);
//                    if(result.isEnable)
//                        return payType;
//                    else {
//                        //TODO
//                        return null;
//                    }
//                });
//            }
//            //get all the finishing tasks in the given time
//            int givenTime = dynamicProperties.getIntProperty("givenTime",60);
//            List<String> result = new LinkedList<>();
//            try {
//                Thread.sleep(givenTime*1000);
//                while(true) {
//                    Future<String> f = completionService.poll();
//                    if (f == null)
//                        break;
//                    if (f != null && f.isDone()) {
//                        try {
//                            if (f.get() != null)
//                                result.add(f.get());
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return result;
//        });
//    }
//
//
//    public List<String> filterDisablePayment(List<String> allPaymentList) {
//        //: TODO 完成此处的代码
//        //check whether the getEnabledPayment Procedure is starting
//        long expiredTime = dynamicProperties.getLongProperty("expiredTime",600000); //default value 10min
//        if(isStarted.get()) {
//            //check whether the result is expired
//            if(!isResultExpired(expiredTime)) {
//                try {
//                    return enabledPaymentList.get();
//                } catch (ExecutionException | InterruptedException e) {
//                    e.printStackTrace();
//                    return new ArrayList<>();
//                }
//            }
//            else {
//                if(isStarted.compareAndSet(true, false))
//                    enabledPaymentList = null;
//            }
//        }
//        if(isStarted.compareAndSet(false,true)) {
//            startTime.set(System.currentTimeMillis());
//            getEnablePaymentsInGivingTime(allPaymentList);
//        }
//        try {
//            while(enabledPaymentList == null) {}
//            return enabledPaymentList.get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    public boolean isResultExpired(long expiredTime) {
//        return  System.currentTimeMillis()-startTime.get() > expiredTime;
//    }

}
