package android.stepDef;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.exampleProject.qa.common.gui.services.rest.endpoints.RandomQuoteEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

public class ExampleRestStepsDef extends AbstractStepsDef{

    @Autowired
    @Lazy
    RandomQuoteEndpoint randomQuoteEndpoint;

    @Given("^Connection to Random Quote service established$")
    public void connectionToRandomQuoteServiceEstablished() {
        System.out.println("Connected\n");
    }

    @When("^User send GET request to to Random Quote service$")
    public void userSendGETRequestToToRandomQuoteService() throws IOException {
        dataContainer.setRestServiceOutput(randomQuoteEndpoint.getQuote());
        dataContainer.setQuoteServiceResponse();
    }

    @Then("^response code from Random Quote service: \"([^\"]*)\"$")
    public void responseCodeFromRandomQuoteService(String sEcpectedResposeCode) {
        String sResponseCode = dataContainer.getRestServiceOutput().getHttpStatus().toString();
        assertThat(sResponseCode.equals(sEcpectedResposeCode));
    }

    @And("^response content is not empty$")
    public void responseContentIsNotEmpty() {
        String sQuote = dataContainer.getQuoteServiceResponse().getValue().getQuote();
        System.out.println(sQuote);
        assertThat(!sQuote.isEmpty());
    }
}
