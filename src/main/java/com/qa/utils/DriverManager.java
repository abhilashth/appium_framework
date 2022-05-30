package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams params = new GlobalParams();
       // PropertyManager props = new PropertyManager();
        Properties props = new PropertyManager().getProps();

        String email = "kakiv66483@zneep.com";
        String username = "kakiv66483";
        String accesskey = "02ba7f9a-a725-465b-b811-b85c8d895661";
        String sauceUrl = "@ondemand.us-west-1.saucelabs.com:443";
        String SAUCE_REMOTE_URL = "https://" + username + ":" + accesskey + sauceUrl + "/wd/hub";

        if (driver == null) {
            try {
                utils.log().info("initializing Appium driver");
                switch (params.getRunType()) {
                    case "Android":
           //             driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                        driver = new AndroidDriver(new URL(props.getProperty("appiumURL")), new CapabilitiesManager().getCaps());
                        
                        break;
                    case "iOS":
                        driver = new IOSDriver(new URL(props.getProperty("appiumURL")), new CapabilitiesManager().getCaps());
                        break;
                    case "Android_Sauce":
                        driver = new AndroidDriver(new URL(SAUCE_REMOTE_URL), new CapabilitiesManager().getCaps());
                        break;
                    case "iOS_Sauce":
                        driver = new IOSDriver(new URL(SAUCE_REMOTE_URL), new CapabilitiesManager().getCaps());
                        break;
                }
                if (driver == null) {
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }

}
