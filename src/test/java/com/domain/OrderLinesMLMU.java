package com.domain;

import java.io.Serializable;
import java.util.List;

public class OrderLinesMLMU implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<OrderLineMLMU> listOrderLines ; 
	
	public OrderLinesMLMU(List<OrderLineMLMU> lisOrderLines) {
		this.listOrderLines=lisOrderLines;	
	}

	public List<OrderLineMLMU> getListOrderLines() {
		return listOrderLines;
	}

	public void setListOrderLines(List<OrderLineMLMU> listOrderLines) {
		this.listOrderLines = listOrderLines;
	}


	
}
