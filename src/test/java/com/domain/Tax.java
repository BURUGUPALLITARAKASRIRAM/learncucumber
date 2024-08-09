package com.domain;
import java.math.BigDecimal;

public class Tax {
	String taxName;
	BigDecimal taxAmount;
	
	public Tax(String taxName ,BigDecimal taxAmount) {
		this.taxName= taxName;
		this.taxAmount=taxAmount;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	

}
