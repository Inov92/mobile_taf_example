package android.stepDef;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class MainScreenStepsDef extends AbstractStepsDef{
    @Given("^User opens application on main screen$")
    public void userOpensApplication(){
        mainScreenPageObject.isLoaded();
    }

    @When("^User fill Text Field with \"([^\"]*)\" on main screen$")
    public void userFillTextfieldWith(String sInputString) {
        mainScreenPageObject.fillTextField(sInputString);
    }

    @Then("^Welcome text contains \"([^\"]*)\" on main screen$")
    public void welcomeTextContains(String sExpectedText) {
       assertThat(mainScreenPageObject.getHelloText()).isEqualToIgnoringCase(sExpectedText);
    }


    @When("User press OK button on main screen$")
    public void pressOKButtonOnMainScreen() {
        mainScreenPageObject.pressOkButton();
    }

    @Then("^Alert window contains \"([^\"]*)\"$")
    public void alertWindowContains(String sAlertMessage) {
        assertThat(mainScreenPageObject.getAlertText().equalsIgnoreCase(sAlertMessage));
    }

    @And("^Text Field is empty on main screen$")
    public void textFieldIsEmptyOnMainScreen() {
        mainScreenPageObject.clearEditText();
        assertThat(mainScreenPageObject.getTextFieldValue().isEmpty());
    }
}
