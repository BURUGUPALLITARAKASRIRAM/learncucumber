package learncucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.domain.Discount;
import com.domain.OrderLineMLMU;
import com.domain.OrderLineStatus;
import com.domain.OrderLinesMLMU;
import com.domain.OrderMLMU;
import com.domain.StatusName;
import com.domain.Tax;
import com.domain.service.ChangeLineStatusService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestAfterChangingLineStatus {
    private OrderMLMU order;
    private OrderLineMLMU orderLine;
    @Given("an order line with prime line number {int} and initial setup for method")
    public void setup_order_line_for_method(int primeLineNumber) {
        orderLine = new OrderLineMLMU(4,1003,103,"Laptop",new BigDecimal("35000.00"), 
        1, new Discount("Coupon","Welcome Offer",new BigDecimal("300.00")),
        new Tax("GST", new BigDecimal("10.00")),
        new ArrayList<>(List.of(new OrderLineStatus(StatusName.ORDERED,5,LocalDate.parse("2024-08-16")))));
        List<OrderLineMLMU> ol = new ArrayList<>();
    	ol.add(orderLine);
        OrderLinesMLMU orderLines = new OrderLinesMLMU(ol);
        order = new OrderMLMU(123, orderLines);
        System.out.println("Setup order line with prime line number: " + primeLineNumber);
        System.out.println("Order line status: " + orderLine.getOrderLineStatus().get(0).getStatusName() +
                           " with quantity: " + orderLine.getOrderLineStatus().get(0).getQty());
    }
    @When("the method is executed with specific arguments")
    public void execute_method_with_arguments() {
    	   ChangeLineStatusService service = new ChangeLineStatusService();
           OrderLineStatus fromStatus = new OrderLineStatus(StatusName.ORDERED, 5, LocalDate.parse("2024-08-16"));
           OrderLineStatus toStatus = new OrderLineStatus(StatusName.SHIPPED, 5, LocalDate.now());

           service.changeLineStatus(order, fromStatus, toStatus, orderLine.getPrimeLineNo());
           System.out.println("Executed changeLineStatus method on the order line.");
    }
    @Then("the expected outcomes should be verified")
    public void verify_expected_outcomes() {
    	for (OrderLineStatus status : orderLine.getOrderLineStatus()) {
            System.out.println("Order line status: " + status.getStatusName() +
                               " with quantity: " + status.getQty());
        }
    	OrderLineStatus expectedStatus = orderLine.getOrderLineStatus().stream()
                .filter(status -> status.getStatusName() == StatusName.SHIPPED)
                .findFirst().orElse(null);
            assertEquals(StatusName.SHIPPED, expectedStatus.getStatusName());
            assertEquals(5, expectedStatus.getQty());
}
}