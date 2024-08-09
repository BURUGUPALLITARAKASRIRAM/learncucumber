package learncucumber;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import com.domain.OrderMLMU;
import com.domain.service.CancelSingleQty;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestTaxDifference {
	 public CreateMLMUOLService createMLMUOLService;
	  public CancelSingleQty cancelSingleQty;
	  public OrderMLMU order;
	  public BigDecimal initialOrderTotal;
	  public BigDecimal initialTaxTotal;
	@Given("Create order with multi line and multi qtys")
	public void create_order_with_multi_line_and_multi_qtys() {
		 createMLMUOLService = new CreateMLMUOLService();
		 order = createMLMUOLService.createOrderMLMUQtys();
		 initialOrderTotal = order.getOrderTotal();
		 initialTaxTotal=order.getTaxTotal();
	}
	@When("Cancel single qtys")
	public void cancel_single_qtys() {
		    cancelSingleQty = new CancelSingleQty();
	        cancelSingleQty.cancelSingleQty(order, 1, 1);    
	}
	@Then("Compare the tax before and after tax should be less the previous")
	public void compare_the_tax_before_and_after_tax_should_be_less_the_previous() {
		 BigDecimal currentOrderTotal = order.getOrderTotal();
	     BigDecimal currentTaxTotal = order.getTaxTotal(); 
	     
	     System.out.println("Initial price: " + initialOrderTotal.toString()
	                + ", After cancel price: " + currentOrderTotal.toString());
	     System.out.println("Initial tax: " + initialTaxTotal.toString()
	                + ", After cancel tax: " + currentTaxTotal.toString());
	        
	    assertTrue(currentTaxTotal.compareTo(initialTaxTotal)<0);
	    assertTrue(currentOrderTotal.compareTo(initialOrderTotal)<0);
	    }  
	}