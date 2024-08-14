package com.domain.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.domain.Discount;
import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.OrderLinesMLMU;
import com.domain.OrderMLMU;
import com.domain.StatusName;
import com.domain.Tax;
public class CreateMLMUOLService{
    public OrderMLMU createOrderMLMUQtys(){
	
	List<OrderLineStatus> orderLineStatuses1 =new ArrayList<OrderLineStatus>();
	OrderLineStatus ol1 =new OrderLineStatus(StatusName.CREATED, 3
			,LocalDate.parse("2024-07-04"));
	orderLineStatuses1.add(ol1);
	
	List<OrderLineStatus> orderLineStatuses2 =new ArrayList<OrderLineStatus>();
	OrderLineStatus ol2 =new OrderLineStatus(StatusName.CREATED, 2
			,LocalDate.parse("2024-07-05"));
    orderLineStatuses2.add(ol2);
    
    Tax tax1 = new Tax("Sales tax",BigDecimal.valueOf(9.23));
	Discount discount1 = new Discount("offer", "product",BigDecimal.valueOf(12.3)); 
	OrderLineMLMU orderLine1 = new OrderLineMLMU(1,1000,100, "Pens"
			,BigDecimal.valueOf(100.0),3,discount1,tax1, orderLineStatuses1);

	
	Tax tax2 = new Tax("Sales tax",BigDecimal.valueOf(7.65));
	Discount discount2 = new Discount("offer", "product",BigDecimal.valueOf(9.3));
	OrderLineMLMU orderLine2 = new OrderLineMLMU(2,1001,101,"Markers"
			,BigDecimal.valueOf(1000.0),2,discount2,tax2,orderLineStatuses2);

	
	List<OrderLineMLMU> ol = new ArrayList<>();
	ol.add(orderLine1);
	ol.add(orderLine2);
	
	OrderLinesMLMU orderLines =new OrderLinesMLMU(ol);
	OrderMLMU order = new OrderMLMU(123, orderLines);
	
	return order;
	}
}