package com.cherry.prospring5.ch12;

import com.cherry.prospring5.ch12.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JmsHornetQSample {
    public static void main(String... args) throws Exception {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MessageSender messageSender =
                ctx.getBean("messageSender", MessageSender.class);
        for (int i = 0; i < 10; ++i) {
            messageSender.sendMessage("Test message: " + i);
        }
        System.in.read();
        ctx.close();
    }
}
