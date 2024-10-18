package javaBase.jdkProxy;

public class SmsServiceImpl implements SmsService{
    @Override
    public void send(String content) {
        System.out.println("发送短信"+content);
    }
}
