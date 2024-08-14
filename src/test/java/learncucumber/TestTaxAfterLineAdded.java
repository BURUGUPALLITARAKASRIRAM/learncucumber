package learncucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import com.domain.OrderLineMLMU;
import com.domain.OrderLinesMLMU;
import com.domain.OrderMLMU;
import com.domain.service.AddLine;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestTaxAfterLineAdded {
	public CreateMLMUOLService createMLMUService;
	public OrderMLMU order;
    public AddLine aL;
    public BigDecimal initialTaxTotal;
	@Given("An existing order with multilines and quantities")
	public void an_existing_order_with_multilines_and_quantities() {
		createMLMUService = new CreateMLMUOLService();
	     order = createMLMUService.createOrderMLMUQtys(); 
	     initialTaxTotal = BigDecimal.ZERO;
	        List<OrderLineMLMU> orderLines = order.getOrderLines().getListOrderLines();
	        for (OrderLineMLMU line : orderLines) {
	            initialTaxTotal = initialTaxTotal.add(line.getTaxTotal());
	        }
	        System.out.println("InitialTaxTotal: "+initialTaxTotal);
	}
	@When("A new line is already added to the order")
	public void a_new_line_is_already_added_to_the_order() {
		  aL = new AddLine();
	      aL.addLine(order);    
	}
	@Then("The order should have one more line added and the tax should be recalculated")
	public void the_order_should_have_one_more_line_added_and_the_tax_should_be_recalculated(){
		 BigDecimal newTaxTotal = BigDecimal.ZERO;
	        List<OrderLineMLMU> orderLines = order.getOrderLines().getListOrderLines();
	        for (OrderLineMLMU line : orderLines) {
	            newTaxTotal = newTaxTotal.add(line.getTaxTotal());
	        }
	        System.out.println("NewTaxTotal: "+newTaxTotal);
	        BigDecimal taxDifference = newTaxTotal.subtract(initialTaxTotal);
	        System.out.println("TaxDifference: "+taxDifference);

	        OrderLineMLMU addedLine = orderLines.get(orderLines.size()-1);
	        System.out.println("Added Line :"+addedLine.toString());
	        BigDecimal expectedTaxDifference = addedLine.getTaxTotal();

	        assertEquals(expectedTaxDifference, taxDifference);	    
	}
}