package learncucumber;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import com.domain.OrderMLMU;
import com.domain.service.CancelSingleQty;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestDiscountDifference {
	public CreateMLMUOLService createMLMUServies;
	 public CancelSingleQty cancelSingleQty;
	public OrderMLMU order;
	  public BigDecimal initialOrderTotal;
	  public BigDecimal initialDiscountTotal;
	@Given("Create order with multi line and multi qty")
	public void create_order_with_multi_line_and_multi_qty() {
		 createMLMUServies = new CreateMLMUOLService();
		  order = createMLMUServies.createOrderMLMUQtys();
		  initialOrderTotal = order.getOrderTotal();
		  initialDiscountTotal = order.getDiscountTotal();
	}
	@When("Cancel single qty")
	public void cancel_single_qty() {
		cancelSingleQty = new CancelSingleQty();
        cancelSingleQty.cancelSingleQty(order, 1, 1); 
	}
	@Then("Compare the discount before and after discount should be less the previous")
	public void compare_the_discount_before_and_after_discount_should_be_less_the_previous() {
		 BigDecimal currentOrderTotal = order.getOrderTotal();
		 BigDecimal currentDiscountTotal =order.getDiscountTotal();
		 
		 System.out.println("Initial price: " + initialOrderTotal.toString()
         + ", After cancel price: " + currentOrderTotal.toString());
         System.out.println("Initial tax: " + initialDiscountTotal.toString()
         + ", After cancel tax: " + currentDiscountTotal.toString());
         
 	    assertTrue(currentDiscountTotal.compareTo(initialDiscountTotal) < 0);
 	    assertTrue(currentOrderTotal.compareTo(initialOrderTotal) < 0); 
	}
}