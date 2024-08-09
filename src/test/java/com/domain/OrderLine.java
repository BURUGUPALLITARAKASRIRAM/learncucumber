package com.domain;
import java.math.BigDecimal;
import java.util.List;
public class OrderLine {
	int primeLineNo;
	int orderNumber;
	int itemId;
	String itemDescription;
	BigDecimal unitPrice;
	int qty;
	BigDecimal totalPrice;
	Discount discount ;
	Tax tax;
	OrderLineStatus orderLineStatus;

	
	public OrderLineStatus getOrderLineStatus() {
		return orderLineStatus;
	}

	public void setOrderLineStatus(OrderLineStatus orderLineStatus) {
		this.orderLineStatus = orderLineStatus;
	}

	public OrderLine(int primeLineNo,int orderNumber ,int itemId,
			String itemDescription,BigDecimal unitPrice,int qty,Discount discount,Tax tax,
			OrderLineStatus orderLineStatus) {
		this.primeLineNo=primeLineNo;
		this.orderNumber=orderNumber;
		this.itemId=itemId;
		this.itemDescription=itemDescription;
		this.unitPrice=unitPrice;
		this.qty=qty;
		this.discount =discount;
		this.tax=tax;
		this.orderLineStatus=orderLineStatus;
		calculateorderTotal();
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
	private BigDecimal getDiscountTotal() {
		return this.discount.getDiscountPerUnit()
		.multiply(BigDecimal.valueOf(this.qty));
	}
	public void calculateorderTotal(){
		this.totalPrice=(unitPrice.multiply(BigDecimal.valueOf(qty)))
				.add(tax.getTaxAmount())
				.subtract(getDiscountTotal());
	}
}
