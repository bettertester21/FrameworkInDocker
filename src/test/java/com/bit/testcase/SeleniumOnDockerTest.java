package com.bit.testcase;

import org.testng.annotations.Test;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumOnDockerTest {

 @Test
 public void MantisTestOnDocker() throws Exception {
 ChromeOptions options = new ChromeOptions();
// URL remoteURL = new URL("http://localhost:49153/wd/hub");
// URL remoteURL = new URL("http://127.0.1.1:4444/wd/hub");
 URL remoteURL = new URL("http://localhost:4444/wd/hub");
 WebDriver driver = new RemoteWebDriver(remoteURL, options);
 driver.get("https://www.TestingDocs.com/mantis");
 System.out.println(driver.getTitle() + 
" \nwww.TestingDocs.com -Selenium Tutorials");
 System.out.println("Test was run on Docker container.Check the Docker Logs!");
 }
}