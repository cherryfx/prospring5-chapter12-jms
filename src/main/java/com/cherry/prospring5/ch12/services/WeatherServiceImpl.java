package com.cherry.prospring5.ch12.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl {

    private static Logger logger =
            LoggerFactory.getLogger(WeatherServiceImpl.class);

    @RabbitListener(queues = "forecasts")
    @RabbitHandler
    public void getForecast(String stateCode) {
        if ("FL".equals(stateCode)) {
            logger.info("Hot");
        } else if ("MA".equals(stateCode)) {
            logger.info("Cold");
        } else {
            logger.info("Not available at this time");
        }
    }

}
