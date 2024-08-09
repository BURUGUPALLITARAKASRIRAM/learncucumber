package learncucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import io.cucumber.java.en.*;
public class StepDefinitions {
	private Person lucy;
	private Person shaun;
	@Given("Lousy is in {} m away")
	public void lousy_is_in_15m_away(int distance) {
		lucy= new Person();
		lucy.move();
		System.out.println(distance);   
	}
	@When("Shaun shouts {string}")
	public void shaun_shouts(String message) {
	     shaun= new Person();
	    shaun.shouts(message);
	    lucy.listens(message);
	}
	@Then("Lousy listens the message")
	public void lousy_listens_the_message() {
		assertEquals(shaun.message,lucy.message);
	    // Write code here that turns the phrase above into concrete actions
	
	}
}