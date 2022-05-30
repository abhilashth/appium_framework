package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class RunnerBase {
    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getRunner() {
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1) {
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

//    @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort",
//            "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort", "runType"})
//    @BeforeClass
//    public void beforeTest(ITestContext iTestContext, @Optional("androidOnly") String emulator, String platformName, @Optional String udid, String deviceName,
//                           @Optional("androidOnly") String systemPort, @Optional("androidOnly") String chromeDriverPort,
//                           @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort, String runType) throws Exception {
//    //    setDateTime(utils.dateTime());
////        setPlatform(platformName);
////        setDeviceName(deviceName);
//
//        GlobalParams params = new GlobalParams();
//        params.setPlatformName(platformName);
//        // params.setUDID(udid);
//        params.setDeviceName(deviceName);
//
//        URL url;
//        InputStream inputStream = null;
//        InputStream stringsis = null;
//        Properties props = new Properties();
//        AppiumDriver driver;
//        String androidAppUrl;
//        String iOSAppUrl;
//
//        String strFile = "logs" + File.separator + platformName + "_" + deviceName;
//        File logFile = new File(strFile);
//        if (!logFile.exists()) {
//            logFile.mkdirs();
//        }
//        //route logs to separate file for each thread
//        ThreadContext.put("ROUTINGKEY", strFile);
//     //   utils.log().info("log path: " + strFile);
//
//        try {
//            props = new Properties();
//            String propFileName = "config.properties";
//            String xmlFileName = "strings/strings.xml";
//
//      //      utils.log().info("load " + propFileName);
//            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//            props.load(inputStream);
//      //      setProps(props);
//
//      //      utils.log().info("load " + xmlFileName);
//            stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
//       //     setStrings(utils.parseStringXML(stringsis));
//
//            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//            desiredCapabilities.setCapability("platformName", platformName);
//            desiredCapabilities.setCapability("deviceName", deviceName);
//            //     desiredCapabilities.setCapability("udid", udid);
//            url = new URL(props.getProperty("appiumURL"));
//
//            String email = "kakiv66483@zneep.com";
//            String username = "kakiv66483";
//            String accesskey = "02ba7f9a-a725-465b-b811-b85c8d895661";
//            String sauceUrl = "@ondemand.us-west-1.saucelabs.com:443";
//            String SAUCE_REMOTE_URL = "https://" + username + ":" + accesskey + sauceUrl + "/wd/hub";
//
//            switch (runType) {
//                case "Android":
//                    desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
//                    desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
//                    desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
//                    if (emulator.equalsIgnoreCase("true")) {
//                        desiredCapabilities.setCapability("avd", deviceName);
//                        desiredCapabilities.setCapability("avdLaunchTimeout", 120000);
//                    }
//                    androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//                            + File.separator + "resources" + File.separator + "app" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.2.1.apk";
//                    //	String androidAppUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
//           //         utils.log().info("Android AppUrl is" + androidAppUrl);
//                    desiredCapabilities.setCapability("app", androidAppUrl);
//                    driver = new AndroidDriver(url, desiredCapabilities);
//                    break;
//                case "Android_Sauce":
//                    desiredCapabilities.setCapability("platformVersion", "10.0");
//                    desiredCapabilities.setCapability("app", "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
//                    //  capabilities.setCapability("app", "storage:filename=io.chingari.app_2022-02-15.apk");
//                    desiredCapabilities.setCapability("deviceOrientation", "portrait");
//                    desiredCapabilities.setCapability("idleTimeout", "90");
//                    desiredCapabilities.setCapability("noReset", "true");
//                    desiredCapabilities.setCapability("newCommandTimeout", "90");
//                    desiredCapabilities.setCapability("name", "Android_Sauce");
//
//                    if (emulator.equalsIgnoreCase("true")) {
//                        desiredCapabilities.setCapability("avd", deviceName);
//                        desiredCapabilities.setCapability("avdLaunchTimeout", 120000);
//                    }
//                    driver = new AndroidDriver<>(new URL(SAUCE_REMOTE_URL), desiredCapabilities);
//                    break;
//                case "iOS":
//                    desiredCapabilities.setCapability("automationName", props.getProperty("iOSAutomationName"));
//                    iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
//                            + File.separator + "resources" + File.separator + "app" + File.separator + "SwagLabsMobileApp.app";
//                    //	String iOSAppUrl = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
//           //         utils.log().info("appUrl is" + iOSAppUrl);
//                    desiredCapabilities.setCapability("bundleId", props.getProperty("iOSBundleId"));
////                    desiredCapabilities.setCapability("wdaLocalPort", wdaLocalPort);
////                    desiredCapabilities.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
//                    desiredCapabilities.setCapability("app", iOSAppUrl);
//
//                    driver = new IOSDriver(url, desiredCapabilities);
//                    break;
//                case "iOS_Sauce":
//                    desiredCapabilities.setCapability("platformVersion", "15.0");
//                    desiredCapabilities.setCapability("app", "storage:ios_ipa.zip");
//                    desiredCapabilities.setCapability("deviceOrientation", "portrait");
//                    desiredCapabilities.setCapability("idleTimeout", "90");
//                    desiredCapabilities.setCapability("noReset", "true");
//                    desiredCapabilities.setCapability("newCommandTimeout", "90");
//                    desiredCapabilities.setCapability("name", "iOS_Sauce");
//
//                    driver = new IOSDriver<>(new URL(SAUCE_REMOTE_URL), desiredCapabilities);
//                    break;
//                default:
//                    throw new Exception("Invalid platform! - " + platformName);
//            }
//            setDriver(driver);
//            utils.log().info("driver initialized: " + driver);
//        } catch (Exception e) {
//            utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
//            throw e;
//        } finally {
//            if (inputStream != null) {
//                inputStream.close();
//            }
//            if (stringsis != null) {
//                stringsis.close();
//            }
//        }
//        setRunner(new TestNGCucumberRunner(this.getClass()));
//    }


    @Parameters({"platformName", "udid", "deviceName", "systemPort",
            "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort", "runType"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String platformName, @Optional String udid, String deviceName, @Optional("Android") String systemPort,
                           @Optional("Android") String chromeDriverPort,
                           @Optional("iOS") String wdaLocalPort,
                           @Optional("iOS") String webkitDebugProxyPort, String runType) throws Exception {


        ThreadContext.put("ROUTINGKEY", platformName + "_" + deviceName);

        GlobalParams params = new GlobalParams();
        params.setPlatformName(platformName);
        params.setRunType(runType);
        params.setDeviceName(deviceName);

//        switch (runType) {
//            case "Android":
//                params.setSystemPort(systemPort);
//                params.setChromeDriverPort(chromeDriverPort);
//                break;
//            case "iOS":
//                params.setWdaLocalPort(wdaLocalPort);
//                params.setWebkitDebugProxyPort(webkitDebugProxyPort);
//                break;
//        }

     //   new ServerManager().startServer();
        new DriverManager().initializeDriver();

        setRunner(new TestNGCucumberRunner(this.getClass()));
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        ServerManager serverManager = new ServerManager();
        if (serverManager.getServer() != null) {
            serverManager.getServer().stop();
        }
        if (testNGCucumberRunner != null) {
            getRunner().finish();
        }
    }
}
