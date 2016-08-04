package com.test.controller;

import java.net.URL;
import com.test.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class HelloControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL _url;
    private RestTemplate _restTemplate;

    @Before
    public void setUp() throws Exception{
        _url = new URL("http://localhost:"+port+"/");
        _restTemplate = new TestRestTemplate();
    }

    @Test
    public void getIndex() throws Exception{
        ResponseEntity<String> response =  _restTemplate.getForEntity(_url.toString(),String.class);
        assertThat(response.getBody(),equalTo("Prueba de SpringBoot"));
    }
}
