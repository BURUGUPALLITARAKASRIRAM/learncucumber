package learncucumber;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.util.List;
import com.domain.Discount;
import com.domain.Order;
import com.domain.OrderLine;
import com.domain.service.CreateSingleQtyOLService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDisount {
	CreateSingleQtyOLService oT ;
	Order order ;
	@Given("Input The order")
	public void input_the_order() {
	   oT = new CreateSingleQtyOLService();
	}
	@When("Create Order")
	public void create_order() {
		order =oT.createOrderSingleQtys();	    
	}
	@Then("No Less then In The Discount")
	public void no_less_then_in_the_discount() {
		BigDecimal sum = BigDecimal.ZERO;
		List<OrderLine> ol = order.getOrderLines().getListOrderLines();
		for(OrderLine listOrderLine : ol) {
			Discount discount = listOrderLine.getDiscount();
			if(discount !=null) {
				sum=sum.add(discount.getDiscountPerUnit());
			}
		}
		assertTrue(sum.compareTo(BigDecimal.valueOf(50))>0);
	
	assertTrue((ol.stream().map(s -> s.getDiscount()
			.getDiscountPerUnit())
			.reduce(BigDecimal.ZERO,BigDecimal::add))
			.compareTo(BigDecimal.valueOf(50))>0);
	}
}