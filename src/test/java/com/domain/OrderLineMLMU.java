package com.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
public class OrderLineMLMU implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	int primeLineNo;
	int orderNumber;
	int itemId;
	String itemDescription;
	BigDecimal unitPrice;
	int qty;
	BigDecimal totalPrice;
	Discount discount ;
	Tax tax;
	List<OrderLineStatus> orderLineStatuses;
//creating constructor 
	public OrderLineMLMU(int primeLineNo,int orderNumber ,int itemId,
			String itemDescription,BigDecimal unitPrice,int qty,
			Discount discount,Tax tax,
			List<OrderLineStatus> orderLineStatuses) {
		this.primeLineNo=primeLineNo;
		this.orderNumber=orderNumber;
		this.itemId=itemId;
		this.itemDescription=itemDescription;
		this.unitPrice=unitPrice;
		this.qty=qty;
		this.discount =discount;
		this.tax=tax;
		this.orderLineStatuses=orderLineStatuses;
		calculateorderTotal();
	}
//getters and setters
	public List<OrderLineStatus> getOrderLineStatus() {
		return orderLineStatuses;
	}

	public void setOrderLineStatus(List<OrderLineStatus> orderLineStatuses) {
		this.orderLineStatuses = orderLineStatuses;
	}
	
	public int getPrimeLineNo() {
		return primeLineNo;
	}

	public void setPrimeLineNo(int primeLineNo) {
		this.primeLineNo = primeLineNo;
	}
	public int getOrderNumber() {
		return this.orderNumber;
	}
	private void setOrderNumber(int orderNumber) {
		this.orderNumber=orderNumber;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getTotalPrice() {
		calculateorderTotal();
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}
//cancel qty method
	public int getCancelledQty() {
		return this.orderLineStatuses.stream()
				.filter(s -> s.getStatusName()==StatusName.CANCELLED)
				.collect(Collectors.summingInt(OrderLineStatus::getQty));
	}
//creating discounttotal method
	public BigDecimal getDiscountTotal() {
		return this.discount.getDiscountPerUnit()
		.multiply(BigDecimal.valueOf(this.qty));
	}
//calculating ordertotal 
	public void calculateorderTotal(){
		this.totalPrice=(unitPrice.multiply(BigDecimal.valueOf(qty)))
				.add(tax.getTaxAmount())
				.subtract(getDiscountTotal());
	}
	public BigDecimal getTaxTotal() {
		return this.tax.getTaxAmount()
				.multiply(BigDecimal.valueOf(this.qty));
	}
	
	public BigDecimal taxAmount; 
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}