package com.saga.payment.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.payment.exception.BusinessException;
import com.saga.payment.service.PaymentService;
import com.saga.payment.worker.dto.ZeebeRequest;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentWorker {
    private final static String NOT_ENOUGH_MONEY_ERROR = "not-enough-money-error";

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @ZeebeWorker(type = "PayForProduct")
    public void payForProduct(JobClient client, ActivatedJob job) throws JsonProcessingException {
        ZeebeRequest zeebeRequest = objectMapper.readValue(job.getVariables(), ZeebeRequest.class);

        try {
            zeebeRequest = paymentService.payProducts(zeebeRequest);
            client.newCompleteCommand(job.getKey())
                    .variables(zeebeRequest)
                    .send()
                    .join();
        } catch(BusinessException ex) {
            client.newThrowErrorCommand(job.getKey())
                    .errorCode(NOT_ENOUGH_MONEY_ERROR)
                    .variables(zeebeRequest)
                    .send()
                    .join();
        }
    }

    @ZeebeWorker(type = "CancelPayingForProduct")
    public void cancelPayingForProduct(JobClient client, ActivatedJob job) throws JsonProcessingException {
        ZeebeRequest zeebeRequest = objectMapper.readValue(job.getVariables(), ZeebeRequest.class);
        zeebeRequest = paymentService.cancelProductPaying(zeebeRequest);

        client.newCompleteCommand(job.getKey())
                .variables(zeebeRequest)
                .send()
                .join();
    }
}