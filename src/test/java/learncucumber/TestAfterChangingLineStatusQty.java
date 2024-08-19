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

public class TestAfterChangingLineStatusQty {
    private OrderMLMU order;
    private OrderLineMLMU orderLine;
    @Given("an order with a line having prime line number {int} and initial status {string} with quantity {int}")
    public void setup_order_with_line(int primeLineNumber, String initialStatus, int initialQty) {
        orderLine = new OrderLineMLMU(primeLineNumber, 1003, 103, "Laptop", new BigDecimal("35000.00"), 
        initialQty, new Discount("Coupon", "Welcome Offer", new BigDecimal("300.00")),
        new Tax("GST", new BigDecimal("10.00")),
        new ArrayList<>(List.of(new OrderLineStatus(StatusName.valueOf(initialStatus), initialQty, LocalDate.parse("2024-08-16")))));
        List<OrderLineMLMU> ol = new ArrayList<>();
        ol.add(orderLine);
        OrderLinesMLMU orderLines = new OrderLinesMLMU(ol);
        order = new OrderMLMU(123, orderLines);
    }
    @When("the quantity of the line with prime line number {int} is updated to {int}")
    public void update_line_quantity(int primeLineNumber, int newQty) {
        ChangeLineStatusService service = new ChangeLineStatusService();
        service.changeLineStatusQty(order, newQty, primeLineNumber);
    }

    @Then("the order line status should be updated to {string} with quantity {int}")
    public void verify_order_line_status_and_quantity(String expectedStatus, int expectedQty) {
       
        OrderLineStatus status = orderLine.getOrderLineStatus().get(0);
        assertEquals(StatusName.valueOf(expectedStatus), status.getStatusName(),
        		"The status name should be " + expectedStatus);
        assertEquals(expectedQty, status.getQty()
        		, "The quantity should be " + expectedQty);
    }
}
