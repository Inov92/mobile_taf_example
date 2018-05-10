package org.exampleProject.qa.common.gui.services.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import javax.annotation.PostConstruct;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.exampleProject.qa.common.gui.annotations.MobilePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MainScreenPageObject extends AbstractMobilePageObject{

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.epam.hello:id/editText")
    private MobileElement editTextField;

    @AndroidFindBy(id = "com.epam.hello:id/okButton")
    private MobileElement okButton;

    @AndroidFindBy(id = "com.epam.hello:id/helloText")
    private MobileElement helloText;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement alertText;

    public void fillTextField(String sText){
        editTextField.click();
        editTextField.sendKeys(sText);
    }

    public void pressOkButton(){
        okButton.click();
    }

    public String getHelloText(){
        return helloText.getText();
    }

    public void clearEditText(){
        editTextField.clear();
    }

    public String getAlertText(){
        return alertText.getText();
    }

    public boolean isLoaded(){
        return editTextField.isDisplayed();
    }
}
