package com.office.consume.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.office.provide.service.DemoService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
@Component
public class SayService {


    @Reference
    private DemoService demoService;

    public  String  sayHello(String name){
        /*DefaultListableBeanFactory*/
        return demoService.sayHello(name);
    }

    public static void main(String[] args) {
        int maxValue = Integer.MAX_VALUE;
        System.out.println(maxValue);
    }

}
