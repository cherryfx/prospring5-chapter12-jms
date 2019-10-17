package com.cherry.prospring5.test;


import com.cherry.prospring5.ch12.Singers;
import com.cherry.prospring5.ch12.entities.Singer;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RestClientTest {
    final Logger logger =
            LoggerFactory.getLogger(RestClientTest.class);
    private static final String URL_GET_ALL_SINGERS =
            "http://localhost:8080/ch12jms-0.0.1-SNAPSHOT/singer/listdata";

    RestTemplate restTemplate;
    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testFindAll() {
        logger.info("--> Testing retrieve all singers");
        Singers singers =  restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertTrue(singers.getSingers().size() == 3);
        listSingers(singers.getSingers());
    }

    private void listSingers(List<Singer> singers){
        singers.forEach(s -> logger.info(s.toString()));
    }
}
