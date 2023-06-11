package com.saga.inventory.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.inventory.exception.BusinessException;
import com.saga.inventory.service.InventoryService;
import com.saga.inventory.worker.dto.ZeebeRequest;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryWorker {
    private final static String NOT_ENOUGH_PRODUCT_ERROR = "not-enough-product-error";

    private final InventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @ZeebeWorker(type = "BookProduct")
    public void bookProduct(JobClient client, ActivatedJob job) throws JsonProcessingException {
        ZeebeRequest zeebeRequest = objectMapper.readValue(job.getVariables(), ZeebeRequest.class);

        try {
            zeebeRequest = inventoryService.bookProduct(zeebeRequest);
            client.newCompleteCommand(job.getKey())
                    .variables(zeebeRequest)
                    .send()
                    .join();
        } catch (BusinessException ex) {
            client.newThrowErrorCommand(job.getKey())
                    .errorCode(NOT_ENOUGH_PRODUCT_ERROR)
                    .send()
                    .join();
        }
    }

    @ZeebeWorker(type = "UnbookProduct")
    public void cancelProductBooking(JobClient client, ActivatedJob job) throws JsonProcessingException {
        ZeebeRequest zeebeRequest = objectMapper.readValue(job.getVariables(), ZeebeRequest.class);
        zeebeRequest = inventoryService.cancelProductBooking(zeebeRequest);

        client.newCompleteCommand(job.getKey())
                .variables(zeebeRequest)
                .send()
                .join();
    }
}
