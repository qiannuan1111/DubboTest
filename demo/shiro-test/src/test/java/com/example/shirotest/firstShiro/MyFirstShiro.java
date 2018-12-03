package com.example.shirotest.firstShiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class MyFirstShiro {

    //指定Realm进行验证
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    //在realm中添加一个用户
    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("Mark","111","admin,user");
    }

   @Test
    public void testShiro(){
        //创建SecurityManager---这里使用默认的
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置SecurityManager环境,添加验证信息
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);

        //根据Shiro提供的工具类SecurityUtils获取主题
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("Mark", "111");
        //提交认证,进行登录(参数是需要认证的数据)
        subject.login(passwordToken);
        //判断是否认证
       System.out.println(subject.isAuthenticated());
       //subject.checkRole("admin");
       subject.checkRoles("admin,user");


    }

}
