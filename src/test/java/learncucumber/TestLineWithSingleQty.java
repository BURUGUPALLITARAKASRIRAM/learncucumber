package learncucumber;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import com.domain.Order;
import com.domain.OrderLine;
import com.domain.OrderLines;
import com.domain.service.CreateSingleQtyOLService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestLineWithSingleQty {
	CreateSingleQtyOLService oT ;
	Order order ;
	@Given("Input the order")
	public void input_the() {
	     oT = new CreateSingleQtyOLService();    
	}
	@When("Create order")
	public void create() {
	  order =oT.createOrderSingleQtys();
	}
	@Then("No Negative Sum In The Lines")
	public void no_negative_sum_in_the_lines() {
	List<OrderLine> orderLines = order.getOrderLines().getListOrderLines();
	assertTrue(orderLines.stream().filter(s -> s.getTotalPrice()
			.compareTo(BigDecimal.ZERO)>0).count()==3);
	/*
	 * for (int i=0;i<orderLines.size();i++) { OrderLine line =orderLines.get(i);
	 * if(line.getTotalPrice().compareTo(BigDecimal.ZERO)<0) { } }
	 */
	}
}