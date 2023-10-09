package com.bit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bit.utilities.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static WebDriver driver;
    protected ChromeOptions coptions;
    protected FirefoxOptions foptions;
    protected URL remoteURL;

    //From Datadrivenframework
    protected Properties config = new Properties();
    protected Properties OR = new Properties();
    FileInputStream fis = null;
    protected static Logger log;
//    public ExcelReader excel;

    protected String userDir = System.getProperty("user.dir");
    public String path = userDir + "/src/test/resources/excel/Testdata.xlsx";
    public ExcelReader excel = new ExcelReader(path);

    //public ExtentReports rep = ExtentManager.createExtentReports();
    public ExtentReports extent = null;

    public ExtentTest test = null;

    protected static String browser, appUrl;

    public WebDriverWait wait = null;

    @BeforeSuite
    public void setUp() {
        if (driver == null) {
            log = LogManager.getLogger(BaseTest.class);
            log.info("In start of the setUp()");
            userDir = System.getProperty("user.dir");
            System.out.println("UserDir: " + userDir);
            try {
                fis = new FileInputStream(userDir + "/src/test/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.info("Config file is loaded!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                fis = new FileInputStream(userDir + "/src/test/resources/properties/OR.properties");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.info("Config file is loaded!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            String path = "src/test/resources/excel/Testdata.xlsx";
//            log = LogManager.getLogger(BaseTest.class);
            coptions = new ChromeOptions();
//            foptions = new FirefoxOptions();
//            URL remoteURL = new URL("http://localhost:49153/wd/hub");
//            URL remoteURL = new URL("http://127.0.1.1:4444/wd/hub");
            try {
//                remoteURL = new URL("http://localhost:4444/wd/hub");   //chrome
//                remoteURL = new URL("http://localhost:4444/wd/hub");    //Firefox
		 remoteURL = new URL("http://127.0.1.1:4444/wd/hub");

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver = new RemoteWebDriver(remoteURL, coptions);
//            driver = new RemoteWebDriver(remoteURL, foptions);
            browser = config.getProperty("browser");
            System.out.println("browser: " + browser);
            appUrl = config.getProperty("appUrl");
            System.out.println("appUrl: " + appUrl);
            wait = new WebDriverWait(driver, Duration.ofMinutes(5));
            log.info("Chrome browser is loaded!");
        }
        driver.manage().window().maximize();

        log.info("In end of the setUp()");
    }

    @AfterSuite
    public void tearDown() {
        log.info("In start of the tearDown()");
        if (driver != null) {
            driver.quit();
            log.info("Browser is closed!");
        }
        log.info("In end of the tearDown()");
    }

    public WebDriver getDriver() {
        return driver;
    }


}
