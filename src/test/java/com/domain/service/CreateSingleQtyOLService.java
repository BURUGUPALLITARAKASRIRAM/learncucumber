package com.domain.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.domain.Discount;
import com.domain.Order;
import com.domain.OrderLine;
import com.domain.OrderLineStatus;
import com.domain.OrderLines;
import com.domain.StatusName;
import com.domain.Tax;
public class CreateSingleQtyOLService {
	public Order createOrderSingleQtys() {
		Tax tax = new Tax("Sales tax",BigDecimal.valueOf(9.23));
		Discount discount = new Discount("offer", "product",BigDecimal.valueOf(22.3));
		OrderLineStatus orderLineStatus =new OrderLineStatus(StatusName.CREATED, 1
				,LocalDate.parse("2024-07-02"));
		OrderLine orderLine = new OrderLine(1,1000,100, "Moblie"
				, BigDecimal.valueOf(1000.0), 1,discount,tax,orderLineStatus);
		orderLine.setTax(tax);
		orderLine.setDiscount(discount);
		OrderLine orderLine1 =new OrderLine(2,1000,101,"Ear Phones"
				,BigDecimal.valueOf(250.0),1,discount,tax,orderLineStatus); 
		Tax tax1 = new Tax("Sales tax",BigDecimal.valueOf(5.33));
		Discount discount1 = new Discount("offer", "product",BigDecimal.valueOf(25.3));
		orderLine1.setTax(tax1);
		orderLine1.setDiscount(discount1);
		
		OrderLine orderLine2 = new OrderLine(3,1000,102,"Mobile Case"
				,BigDecimal.valueOf(100.0),1,discount,tax,orderLineStatus);
		Tax tax2 = new Tax("Sales tax",BigDecimal.valueOf(9.19));
		Discount discount2 = new Discount("offer", "product",BigDecimal.valueOf(13.12));
		orderLine2.setTax(tax2);
		orderLine2.setDiscount(discount2);
		
		List<OrderLine> ol = new ArrayList<>();
		ol.add(orderLine);
		ol.add(orderLine1);
		ol.add(orderLine2);
		OrderLines orderLines =new OrderLines(ol);
		Order order1 = new Order(123, orderLines);
		return order1;
	}
}