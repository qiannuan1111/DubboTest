package com.example.shirotest.firstShiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.sql.Driver;

public class JbdcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();
    {
        //com.mysql.jdbc.Driver
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

    }
    @Test
    public void testShiro(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        String sql = "select ";
        jdbcRealm.setUserRolesQuery(sql);
        jdbcRealm.setPermissionsQuery(sql);
        jdbcRealm.setAuthenticationQuery(sql);
        //创建SecurityManager---这里使用默认的
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置SecurityManager环境,添加验证信息
        securityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);

        //根据Shiro提供的工具类SecurityUtils获取主题
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("Mark", "111");
        //提交认证,进行登录(参数是需要认证的数据)
        subject.login(passwordToken);
        System.out.println(subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:update");

    }
}
