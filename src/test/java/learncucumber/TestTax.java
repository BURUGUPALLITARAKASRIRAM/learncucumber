package learncucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import com.domain.Order;
import com.domain.OrderLine;
import com.domain.Tax;
import com.domain.service.CreateSingleQtyOLService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestTax {
	CreateSingleQtyOLService oT ;
	Order order ;
	@Given("Input The Order")
	public void input_the_order() {
		   oT = new CreateSingleQtyOLService();
	}
	@When("Create Order For Tax")
	public void create_order() {
		order =oT.createOrderSingleQtys();	    	
	}
	@Then("No Less Than In The Tax")
	public void no_less_than_in_the_tax() {
		 BigDecimal sum = BigDecimal.ZERO;
		    List<OrderLine> ol = order.getOrderLines().getListOrderLines();
		    for(OrderLine o:ol) {
		    	Tax tax = o.getTax();
		    	if(tax!=null) {
		    		sum = sum.add(tax.getTaxAmount());
		    	}
	    }
		    assertTrue(sum.compareTo(BigDecimal.valueOf(20))>0);
	}
}
