package com.domain;
import java.time.LocalDate;
public class OrderLineStatus {
	StatusName statusName ;
	int qty;
	LocalDate statusDate;	
	public OrderLineStatus(StatusName statusName,int qty
			,LocalDate statusDate) {
		this.statusName=statusName;
		this.qty=qty;
		this.statusDate=statusDate;
	}
    public StatusName getStatusName() {
		return statusName;
	}
	public void setStatusName(StatusName statusName) {
		this.statusName = statusName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public LocalDate getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(LocalDate statusDate) {
		this.statusDate = statusDate;
	}
}