package com.saga.order.service;

import com.saga.order.configuration.ProcessProperties;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZeebeService {
    private final ProcessProperties processProperties;

    private final ZeebeClient zeebeClient = ZeebeClient.newClientBuilder().usePlaintext().build();
    public void start(String payload) {
        zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId(processProperties.getProcess())
                .latestVersion()
                .variables(payload)
                .withResult()
                .send()
                .join();
    }
}
