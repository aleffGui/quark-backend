package com.guilherme.quarkapi.schedulers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PingScheduler {

    private static final Logger logger = LoggerFactory.getLogger(PingScheduler.class);

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String prefix = "/actuator/health";

    public PingScheduler(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl + prefix;
    }

    @Scheduled(fixedRate = 600000)
    public void pingApi() {
        String response = restTemplate.getForObject(apiUrl, String.class);
        logger.info(response);
    }
}
