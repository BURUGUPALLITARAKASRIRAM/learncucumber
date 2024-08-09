package learncucumber;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import com.domain.OrderLineMLMU;
import com.domain.OrderMLMU;
import com.domain.service.CancelSingleQty;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class TestDifferenceAfterCalculate {
	  public CreateMLMUOLService createMLMUOLService;
	  public CancelSingleQty cancelSingleQty;
	  public OrderMLMU order;
	  public BigDecimal initialOrderTotal;
	  
	@Given("Create order with multi line and multi quantitys")
	public void create_order_with_multi_line_and_multi_quantitys() {
		  createMLMUOLService = new CreateMLMUOLService();
	        order = createMLMUOLService.createOrderMLMUQtys(); 
	        initialOrderTotal=order.getTaxTotal();
	        
	}
	@When("Cancel single quantitys")
	public void cancel_single_quantitys() {
		 cancelSingleQty = new CancelSingleQty();
	     cancelSingleQty.cancelSingleQty(order, 1, 1);
	}
	@Then("Compare the price before and after ordertotal price should be less the previous")
	public void compare_the_price_before_and_after_ordertotal_price_should_be_less_the_previous(){
		System.out.println("Initial price :"+initialOrderTotal.toString()
				+" after cancel price: "+order.getOrderTotal().toString());
		
		assertTrue(initialOrderTotal.compareTo(order.getOrderTotal())>0);
	}
}