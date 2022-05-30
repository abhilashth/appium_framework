package com.qa.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class CapabilitiesManager {
    TestUtils utils = new TestUtils();

    public DesiredCapabilities getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();
        String androidAppUrl;
        String iOSAppUrl;
        try {
            utils.log().info("getting capabilities");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
          //  caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

            switch (params.getRunType()) {
                case "Android":
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                            + File.separator + "resources" + File.separator + "apps" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.2.1.apk";
                    utils.log().info("Android AppUrl is" + androidAppUrl);
                    caps.setCapability("app", androidAppUrl);
                    break;
                case "Android_Sauce":
                    caps.setCapability("platformVersion", "10.0");
                    caps.setCapability("app", "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    //  capabilities.setCapability("app", "storage:filename=io.chingari.app_2022-02-15.apk");
                    caps.setCapability("deviceOrientation", "portrait");
                    caps.setCapability("idleTimeout", "90");
                    caps.setCapability("noReset", "true");
                    caps.setCapability("newCommandTimeout", "90");
                    caps.setCapability("name", "Android_Sauce");
                    break;
                case "iOS":
                    caps.setCapability("automationName", props.getProperty("iOSAutomationName"));
                    iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                            + File.separator + "resources" + File.separator + "apps" + File.separator + "SwagLabsMobileApp.app";
                    //	String iOSAppUrl = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
                    utils.log().info("appUrl is" + iOSAppUrl);
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    caps.setCapability("wdaLocalPort", params.getWdaLocalPort());
                    caps.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                    caps.setCapability("app", iOSAppUrl);
                    break;
                case "iOS_Sauce":
                    caps.setCapability("platformVersion", "15.0");
                    caps.setCapability("app", "storage:ios_ipa.zip");
                    caps.setCapability("deviceOrientation", "portrait");
                    caps.setCapability("idleTimeout", "90");
                    caps.setCapability("noReset", "true");
                    caps.setCapability("newCommandTimeout", "90");
                    caps.setCapability("name", "iOS_Sauce");
                    break;
            }
            return caps;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }
}
