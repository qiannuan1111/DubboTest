package com.office.consume;

import com.office.consume.service.SayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ImportResource(value = {"classpath:dubbo-consumer.xml"})
public class ConsumeApplication {

    @Autowired
    private SayService sayService;

   @RequestMapping(value = "/hello")
    public String say(@RequestParam("name") String name) {
        return sayService.sayHello(name);
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumeApplication.class, args);
        /*SpringApplication.run(ConsumeApplication.class, args);*/

    }
}
