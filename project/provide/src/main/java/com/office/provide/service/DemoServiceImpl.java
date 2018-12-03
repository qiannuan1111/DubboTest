package com.office.provide.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {



    @Override
    public String sayHello(String name) {
        return "hello"+name;
    }
}
