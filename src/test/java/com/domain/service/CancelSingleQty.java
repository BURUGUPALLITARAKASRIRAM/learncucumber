package com.domain.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.OrderMLMU;
import com.domain.StatusName;
public class CancelSingleQty {
	public void cancelSingleQty(OrderMLMU order,int primeLineNo, 
			int qty) 
	{
		OrderLineMLMU existingLine=order.getOrderLines().getListOrderLines()
		.stream().filter(s ->(s.getPrimeLineNo()== primeLineNo))
		.findFirst().get();
		existingLine.setQty(existingLine.getQty()-qty);
		
		existingLine.getOrderLineStatus().stream()
		.filter(s-> s.getStatusName()==StatusName.CREATED)
		.forEach(s -> s.setQty(s.getQty()-qty));
		
		OrderLineStatus newCancelStatus= new OrderLineStatus(StatusName.CANCELLED,qty,LocalDate.now());
		existingLine.getOrderLineStatus().add(newCancelStatus);
			
	}
}
