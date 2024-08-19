package com.domain.service;
import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.StatusName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class SplitLineService {
    // Method to split an order line and adjust quantities
    public void splitLine(OrderLineMLMU orderLine, int primeLineNo, int splitQty) {
        if (orderLine == null || splitQty <= 0 || splitQty >= orderLine.getQty()) {
            throw new IllegalArgumentException("Invalid order line or split quantity.");
        }
        // Adjust the original line quantity
        int remainingQty = orderLine.getQty() - splitQty;
        orderLine.setQty(remainingQty);
        // Create a new order line for the split quantity
        OrderLineMLMU splitOrderLine = new OrderLineMLMU(
            primeLineNo, // This should be a new or same prime line number for identification
            orderLine.getItemId(),
            orderLine.getOrderNumber(),
            orderLine.getItemDescription(),
            orderLine.getUnitPrice(),
            splitQty,
            orderLine.getDiscount(),
            orderLine.getTax(),
            new ArrayList<>()
        );
        OrderLineStatus splitStatus = new OrderLineStatus(StatusName.SPLIT, splitQty, LocalDate.now());
        splitOrderLine.getOrderLineStatus().add(splitStatus);
    }
}