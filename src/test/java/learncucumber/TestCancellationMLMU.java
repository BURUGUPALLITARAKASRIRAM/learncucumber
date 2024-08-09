package learncucumber;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.OrderMLMU;
import com.domain.StatusName;
import com.domain.service.CancelSingleQty;
import com.domain.service.CreateMLMUOLService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestCancellationMLMU {
    public  CreateMLMUOLService createMLMUOLService;
    public  CancelSingleQty cancelSingleQty;
    public OrderMLMU order;
	@Given("Create order with multi line and multi quantity")
	public void create_order_with_multi_line_and_multi_quantity() {
		createMLMUOLService = new CreateMLMUOLService();
	   order = createMLMUOLService.createOrderMLMUQtys();
	}
	@When("Cancel single quantity")
	public void cancel_single_quantity() {
		CancelSingleQty cancelSQ = new CancelSingleQty();
		cancelSQ.cancelSingleQty(order, 1, 1);
	}
	@Then("One of the status in the states arraylist moves to cancelled status")
	public void one_of_the_status_in_the_states_arraylist_moves_to_cancelled_status() {
		 boolean statusCancelled = false;
		 for (OrderLineMLMU orderLine : order.getOrderLines().getListOrderLines()) {
	            for (OrderLineStatus status : orderLine.getOrderLineStatus()) {
	                if (status.getStatusName() == StatusName.CANCELLED) {
	                    statusCancelled = true;
	                    break; 
	                }
	            }
	            if (statusCancelled) {
	                break; 
	            }
		 }
		   assertTrue(statusCancelled);
	}
}