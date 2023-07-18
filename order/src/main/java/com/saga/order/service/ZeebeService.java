package com.saga.order.service;

import com.saga.order.configuration.ProcessProperties;
import com.saga.order.repository.entity.Order;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZeebeService {
    private final ProcessProperties processProperties;

    private final ZeebeClient zeebeClient = ZeebeClient.newClientBuilder().usePlaintext().build();
    public ProcessInstanceResult start(Order payload) {

        return zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId(processProperties.getProcess())
                .latestVersion()
                .variables(payload)
                .withResult()
                .send()
                .join();
    }
}
