package com.domain;
import java.math.BigDecimal;
public class Order {
	Long orderNumber;
	BigDecimal orderTotal;
	OrderLines orderLines;
	public Order(long orderNumber
			,OrderLines OrderLines ) {
		this.orderNumber=orderNumber;
		this.orderLines=OrderLines;
		calOrderTotal();
	}
	public long getOrderNumber() {
		return this.orderNumber;
	}
	private void setOrderNumber(Long orderNumber) {
		this.orderNumber=orderNumber;
	}
	
	public BigDecimal getOrderTotal() {
		return this.orderTotal;
	}
	private void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal=orderTotal;
	}
	
	public OrderLines getOrderLines() {
		return this.orderLines;	
	}
	public void setOrderLines(OrderLines orderLines) {
		this.orderLines=orderLines;
	}
	public  void calOrderTotal(){
		this.orderTotal=orderLines.getListOrderLines().stream().map(OrderLine::getTotalPrice)
				.reduce(BigDecimal.ZERO,BigDecimal::add);
	}
}
