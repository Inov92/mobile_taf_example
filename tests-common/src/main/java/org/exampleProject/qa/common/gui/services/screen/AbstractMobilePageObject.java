package org.exampleProject.qa.common.gui.services.screen;

import io.appium.java_client.android.AndroidDriver;
import org.exampleProject.qa.common.gui.services.attachments.Attachments;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AbstractMobilePageObject {
    @Autowired
    @Lazy
    public AndroidDriver driver;

    @Autowired
    @Lazy
    protected Attachments attachments;
}
