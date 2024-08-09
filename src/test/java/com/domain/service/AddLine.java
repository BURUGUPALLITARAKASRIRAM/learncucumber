package com.domain.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.domain.Discount;
import com.domain.Order;
import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.OrderLinesMLMU;
import com.domain.OrderMLMU;
import com.domain.StatusName;
import com.domain.Tax;
public class AddLine {
	public OrderMLMU addLine(OrderMLMU ordermlmu) {
		CreateMLMUOLService cl= new CreateMLMUOLService();
		OrderMLMU order=cl.createOrderMLMUQtys();
		List<OrderLineStatus> orderLineStatuses =new ArrayList<OrderLineStatus>();
		OrderLineStatus oLS =new OrderLineStatus(StatusName.CREATED, 1
				,LocalDate.parse("2024-08-07"));
		orderLineStatuses.add(oLS);
		Tax tax = new Tax("Sales tax",BigDecimal.valueOf(7.12));
		Discount discount = new Discount("offer", "product",BigDecimal.valueOf(2.3));
		OrderLineMLMU oLMLMU=
		new OrderLineMLMU(3,1002,102,"Charger",
				BigDecimal.valueOf(449.0),1,discount,tax,orderLineStatuses);
		order.getOrderLines().getListOrderLines().add(oLMLMU);
		return order;
	}
}