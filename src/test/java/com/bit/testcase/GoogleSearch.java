package com.bit.testcase;

import com.bit.base.BaseTest;
import com.bit.base.TestBase;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.Test;

public class GoogleSearch extends BaseTest {

    @Test
    public void googleSearchOne()
    {
        driver.get("https://www.TestingDocs.com/mantis");
        System.out.println(driver.getTitle() +
                " \nwww.TestingDocs.com -Selenium Tutorials");
        System.out.println("Test was run on Docker container.Check the Docker Logs!");
    }
}
