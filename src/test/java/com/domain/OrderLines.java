package com.domain;
import java.util.List;
public class OrderLines {
	List<OrderLine> listOrderLines ; 
	
	public OrderLines(List<OrderLine> lisOrderLines) {
		this.listOrderLines=lisOrderLines;	
	}

	public List<OrderLine> getListOrderLines() {
		return listOrderLines;
	}

	public void setListOrderLines(List<OrderLine> listOrderLines) {
		this.listOrderLines = listOrderLines;
	}
	

}
