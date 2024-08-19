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
import com.domain.service.SplitLineService;
import com.domain.service.ChangeLineStatusService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TestAfterSliptingLineCheckingQty {
    private OrderMLMU order;
    private OrderLineMLMU orderLine;
    private SplitLineService splitService;
    private ChangeLineStatusService changeService;
    @Given("an order line with prime line number {int}, quantity {int}")
    public void setup_order_line(int primeLineNumber, int qty) {
        orderLine = new OrderLineMLMU(primeLineNumber, 1004, 104, "Laptop charger", new BigDecimal("2500.00"), 
        qty, new Discount("Coupon", "Welcome Offer", new BigDecimal("130.00")),
        new Tax("GST", new BigDecimal("10.00")),
        new ArrayList<>(List.of(new OrderLineStatus(StatusName.ORDERED, qty, LocalDate.parse("2024-08-16")))));
        List<OrderLineMLMU> ol = new ArrayList<>();
        ol.add(orderLine);
        OrderLinesMLMU orderLines = new OrderLinesMLMU(ol);
        order = new OrderMLMU(123, orderLines);
        splitService = new SplitLineService();
        changeService = new ChangeLineStatusService();
    }

    @When("the order line with prime line number {int} is split with quantity {int}")
    public void split_quantity(int primeLineNumber, int splitQty) {
        splitService.splitLine(orderLine, primeLineNumber, splitQty);
    }
    @When("the split order line with prime line number {int} is canceled with quantity {int}")
    public void cancel_quantity(int primeLineNumber, int cancelQty) {
        // Assuming the split order line is tracked & accessible
        OrderLineMLMU splitOrderLine = findOrderLineByPrimeLineNo(primeLineNumber);
        //changeService.cancelQuantity(splitOrderLine, cancelQty);
    }
    @Then("the remaining order line quantity should be {int} and canceled quantity should be {int}")
    public void verify_remaining_and_canceled_quantities(int remainingQty, int canceledQty) {
        assertEquals(remainingQty, orderLine.getQty(), "The remaining quantity should be as expected.");
        
        OrderLineStatus status = orderLine.getOrderLineStatus().get(0);
        assertEquals(StatusName.CANCELLED, status.getStatusName(), "The status should be CANCELED.");
        assertEquals(canceledQty, status.getQty(), "The canceled quantity should be as expected.");
    }

    private OrderLineMLMU findOrderLineByPrimeLineNo(int primeLineNo) {
        return orderLine;
    }
}