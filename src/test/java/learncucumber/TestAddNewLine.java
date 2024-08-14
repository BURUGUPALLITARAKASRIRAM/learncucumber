package learncucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.domain.OrderLinesMLMU;
import com.domain.OrderMLMU;
import com.domain.service.AddLine;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestAddNewLine {
	public CreateMLMUOLService createMLMUOLService;
	public OrderMLMU order;
	public AddLine aL;
	@Given("An existing order with multilines and qtys")
	public void an_existing_order_with_multilines_and_qtys() {
		createMLMUOLService = new CreateMLMUOLService();
	   order =createMLMUOLService.createOrderMLMUQtys();
	}
	@Then("A new line is added to the order")
	public void a_new_line_is_added_to_the_order() {
		aL = new AddLine();
		aL.addLine(order);	
	}
	@When("The order should have one more line added")
	public void the_order_should_have_one_more_line_added() {
		 OrderLinesMLMU orderLines = order.getOrderLines();
		int expectedLineCount=3;
		assertEquals(expectedLineCount,orderLines.getListOrderLines().size());
	}
}