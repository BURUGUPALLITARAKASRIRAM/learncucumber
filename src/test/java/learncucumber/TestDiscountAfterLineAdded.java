package learncucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.List;
import com.domain.OrderLineMLMU;
import com.domain.OrderMLMU;
import com.domain.service.AddLine;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestDiscountAfterLineAdded {
	public CreateMLMUOLService createMLMUService;
	public OrderMLMU order;
    public AddLine aL;
    public BigDecimal initialDiscountTotal;
	@Given("An existing order with multiple lines and quantities")
	public void an_existing_order_with_multiple_lines_and_quantities() {
		createMLMUService= new CreateMLMUOLService();
		order = createMLMUService.createOrderMLMUQtys();
		initialDiscountTotal=BigDecimal.ZERO;
        List<OrderLineMLMU> orderLines = order.getOrderLines().getListOrderLines();
        for (OrderLineMLMU line : orderLines) {
        	initialDiscountTotal =initialDiscountTotal.add(line.getDiscountTotal());	
        }
        System.out.println("InitialDiscountTotal: "+initialDiscountTotal);
	   
	}
	@When("A new line  already added to the order")
	public void a_new_line_already_added_to_the_order() {
		  aL = new AddLine();
	      aL.addLine(order); 
	}
	@Then("The order should have one more line added and the discount should be recalculated")
	public void the_order_should_have_one_more_line_added_and_the_discount_should_be_recalculated() {
	  BigDecimal newDiscountTotal = BigDecimal.ZERO;
	  List<OrderLineMLMU> orderLines = order.getOrderLines().getListOrderLines();
	  for(OrderLineMLMU line : orderLines) {
		  newDiscountTotal=newDiscountTotal.add(line.getDiscountTotal());
	  }
		  System.out.println("NewDiscountTotal: "+newDiscountTotal);
		  BigDecimal discountDifference = newDiscountTotal.subtract(initialDiscountTotal);
		  System.out.println("DiscountDifference: "+discountDifference);
		  OrderLineMLMU addedLine = orderLines.get(orderLines.size()-1);
		  BigDecimal expectedDiscountDifference = addedLine.getDiscountTotal();
		  System.out.println(expectedDiscountDifference );
		  assertEquals(expectedDiscountDifference,discountDifference);  
	}
}