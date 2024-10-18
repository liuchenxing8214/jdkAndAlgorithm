package javaBase.jdkProxy;

import javaBase.cglibProxy.CglibProxyFactory;

public class Main {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("jdk动态代理");

        System.out.println("===============================");

        SmsService aliSmsService = (SmsService) CglibProxyFactory.getProxy(SmsServiceImpl.class);
        aliSmsService.send("cglib动态代理");
    }
}
