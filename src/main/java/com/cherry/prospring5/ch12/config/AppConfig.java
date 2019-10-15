package com.cherry.prospring5.ch12.config;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJms
@ComponentScan(basePackages = {"com.cherry.prospring5.ch12"})
public class AppConfig {

    @Bean
    ActiveMQQueue prospring5() {
        return new ActiveMQQueue("prospring5");
    }

    @Bean
    ConnectionFactory connectionFactory() {
        Map<String, Object> connDetails = new HashMap<>();
        connDetails.put(TransportConstants.HOST_PROP_NAME, "127.0.0.1");
        connDetails.put(TransportConstants.PORT_PROP_NAME, "61616");
        TransportConfiguration transportConfiguration = new TransportConfiguration(
                NettyConnectorFactory.class.getName(), connDetails);
        return new ActiveMQJMSConnectionFactory(false, transportConfiguration);
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer>
    jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-5");
        return factory;
    }

    @Bean
    JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestination(prospring5());
        return jmsTemplate;
    }
}
