package com.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class OrderMLMU implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	Long orderNumber;
	BigDecimal orderTotal;
	OrderLinesMLMU orderLines;
	//creating constructor
	public OrderMLMU(long orderNumber
			,OrderLinesMLMU OrderLines ) {
		this.orderNumber=orderNumber;
		this.orderLines=OrderLines;
		calOrderTotal();
	//Geters and Setters	
	}
	public long getOrderNumber() {
		return this.orderNumber;
	}
	private void setOrderNumber(Long orderNumber) {
		this.orderNumber=orderNumber;
	}
	
	public BigDecimal getOrderTotal() {
		calOrderTotal();
		return this.orderTotal;
	}
	private void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal=orderTotal;
	}
	
	public OrderLinesMLMU getOrderLines() {
		return this.orderLines;	
	}
	public void setOrderLines(OrderLinesMLMU orderLines) {
		this.orderLines=orderLines;
	}
	//calculating order total with method
	public  void calOrderTotal(){
		this.orderTotal=orderLines.getListOrderLines().stream()
				.map(OrderLineMLMU::getTotalPrice)
				.reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	//calculating total tax with method
    public BigDecimal getTaxTotal() {
        List<OrderLineMLMU> ol =this.orderLines.getListOrderLines(); 
        BigDecimal totalTax = BigDecimal.ZERO;
        for (OrderLineMLMU line : ol) {
       totalTax = totalTax.add(line.getTaxAmount());
        }
       return totalTax;
    }
    //calculating total discount with method
    public BigDecimal getDiscountTotal() {
    List<OrderLineMLMU> ol1 = this.orderLines.getListOrderLines();
    	BigDecimal totalDiscount = BigDecimal.ZERO;
    	for(OrderLineMLMU line :ol1) {
    		totalDiscount=totalDiscount.add(line.getDiscountTotal());
    	}
		return totalDiscount;	
    }
}