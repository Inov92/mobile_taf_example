package org.exampleProject.qa.common.gui.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Configuration
public class MobileWebDriverConfig {
    private AndroidDriver<?> driver;

    //This url will contain the Appium servers availability.
    @Value("${driverURL}")
    URL url;

    @Value("${appDir}")
    String appDir;
    @Value("${app}")
    String app;
    @Value("${deviceName}")
    String deviceName;
    @Value("${udid}")
    String udid;
    @Value("${platformName}")
    String platformName;
    @Value("${platformVersion}")
    String platformVersion;
    @Value("${appPackage}")
    String appPackage;
    @Value("${newCommandTimeout}")
    String newCommandTimeout;
    @Value("${useAvd}")
    String useAvd;
    @Value("${avd}")
    String avd;


    @Bean(destroyMethod = "quit")
    public AndroidDriver initAndroidDriver(){

        //Set the application file
        File appDirFile = new File(System.getProperty("user.dir"));
        File appFile = new File(appDirFile,app);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);

        //Set the platformName and platformVersion desired capability
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
        capabilities.setCapability("appPackage", appPackage);

        // Set how long (in seconds) Appium should wait for a new command from the client before assuming the client quit and ending the session
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, newCommandTimeout);

        //Set full-reset capability. It will reset app state by uninstalling app instead of clearing app data. On Android, this will also remove the app after the session is complete.
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        //Set the avd capability by checking if we want to run our test in an emulator. It will start the emulator if it's not started yet.
        if (useAvd.equals("true")) {
            capabilities.setCapability("avd",avd);
        }
        driver = new AndroidDriver(url, capabilities);
        return driver;
    }


}
