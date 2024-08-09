package com.domain;

import java.math.BigDecimal;

public class Discount {
	String discountType;
	String discountName;
	BigDecimal discountPerUnit;
	public Discount(String discountType, 
			String discountName,BigDecimal discountPerUnit) {
		this.discountName=discountName;
		this.discountType=discountType;
		this.discountPerUnit=discountPerUnit;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public BigDecimal getDiscountPerUnit() {
		return discountPerUnit;
	}
	public void setDiscountPerUnit(BigDecimal discountPerUnit) {
		this.discountPerUnit = discountPerUnit;
	}
	
}
