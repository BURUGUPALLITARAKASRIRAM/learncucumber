package com.domain.service;
import java.time.LocalDate;
import java.util.List;

import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.OrderMLMU;
import com.domain.StatusName;
public class ChangeLineStatusService {
	public void changeLineStatus(OrderMLMU orderMLMU, OrderLineStatus fromStatus,
			OrderLineStatus toStatus, int primeLineNumber) {
// Find the order line with the given prime line number
	    for (OrderLineMLMU orderLine:orderMLMU.getOrderLines()
	    		.getListOrderLines()) {
	  if (orderLine.getPrimeLineNo()==primeLineNumber) {
// Remove qty from the fromStatus
	     for (OrderLineStatus status:orderLine.getOrderLineStatus()) {
	   if (status.getStatusName()== fromStatus.getStatusName()) {
	                    status.setQty(status.getQty()-fromStatus.getQty());
	                    if (status.getQty()<=0) {
	                        orderLine.getOrderLineStatus().remove(status);
	                    }
	                    break;
	                }
	            }
// Add or update qty to the toStatus
	       boolean statusExists = false;
	         for (OrderLineStatus status:orderLine.getOrderLineStatus()) {
	             if (status.getStatusName()== toStatus.getStatusName()) {
	                    status.setQty(status.getQty()+toStatus.getQty());
	                    statusExists = true;
	                    break;
	                }
	            }
	            if (!statusExists) {
	                orderLine.getOrderLineStatus()
	                .add(new OrderLineStatus(toStatus.getStatusName(), toStatus.getQty(), null));
	            }
	          break;
	        }
	    }
	}
	 public void changeLineStatusQty(OrderMLMU order, int qty, int primeLineNo) {
  List<OrderLineMLMU> orderLines = order.getOrderLines().getListOrderLines();
	for (OrderLineMLMU ol : orderLines) {
	if (ol.getPrimeLineNo() == primeLineNo) {	               
	  if (!ol.getOrderLineStatus().isEmpty()) {	                
	     OrderLineStatus status = ol.getOrderLineStatus().get(0);
	     status.setQty(qty);
	     status.setStatusName(StatusName.CHANGED); 
	 } else {
	    	OrderLineStatus newStatus = new OrderLineStatus(StatusName.CHANGED, qty, LocalDate.now());
	         ol.getOrderLineStatus().add(newStatus);
	                }
	     return;
	            }
	        }
	    }
	  public void cancelQuantity(OrderLineMLMU orderLine, int cancelQty) {
	        if (orderLine == null || cancelQty <= 0) {
	            throw new IllegalArgumentException
	            ("Invalid order line or cancellation quantity.");
	        }
	        int currentQty = orderLine.getQty();
	        if (cancelQty > currentQty) {
	            throw new IllegalArgumentException
	            ("Cancellation quantity exceeds current quantity.");
	        }
	        int newQty = currentQty - cancelQty;
	        orderLine.setQty(newQty);

	        OrderLineStatus status = orderLine.getOrderLineStatus().isEmpty() ?
	            new OrderLineStatus(StatusName.CANCELLED, cancelQty, LocalDate.now()) :
	            orderLine.getOrderLineStatus().get(0);

	        status.setQty(status.getQty() + cancelQty); // update status quantity to reflect total canceled
	        status.setStatusName(StatusName.CANCELLED); // set status to cancelled
	    }
	}