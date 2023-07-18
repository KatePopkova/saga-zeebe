package com.saga.inventory.service;

import com.saga.inventory.enumerated.InventoryStatus;
import com.saga.inventory.exception.BusinessException;
import com.saga.inventory.repository.entity.Product;
import com.saga.inventory.worker.dto.ZeebeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final OrderService orderService;
    private final ProductService productService;

    public ZeebeRequest bookProduct(ZeebeRequest zeebeRequest) throws BusinessException {
        Integer requestedProductCount = zeebeRequest.getProductCount();
        try {
            Product product = productService.findProductByProductId(zeebeRequest.getProductId()).get();
            if(product.getProductCount() > requestedProductCount) {
                productService.decreaseProductCount(product, requestedProductCount);
                orderService.updateOrderStatus(zeebeRequest.getId(), InventoryStatus.RESERVED);
                zeebeRequest.setOrderStatus(InventoryStatus.RESERVED.toString());
            } else {
                throw new BusinessException("Not enough products are available for ordering.");
            }
            return zeebeRequest;
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("There is no order or product with provided ID. \n");
        }
    }

    public ZeebeRequest cancelProductBooking(ZeebeRequest zeebeRequest) {
        String orderStatus = zeebeRequest.getOrderStatus();

        if (orderStatus.equals(InventoryStatus.REJECTED.toString())) {
            Product product = productService.findProductByProductId(zeebeRequest.getProductId()).get();
            productService.increaseProductCount(product, zeebeRequest.getProductCount());
            updateOrderStatus(zeebeRequest, InventoryStatus.REJECTED);
        }

        if (orderStatus.equals(InventoryStatus.CREATED.toString())) {
            updateOrderStatus(zeebeRequest, InventoryStatus.REJECTED);
        }
        return zeebeRequest;
    }

    private void updateOrderStatus(ZeebeRequest zeebeRequest, InventoryStatus status) {
        orderService.updateOrderStatus(zeebeRequest.getId(), status);
        zeebeRequest.setOrderStatus(status.toString());
    }
}
