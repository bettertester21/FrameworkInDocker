package com.bit.testcase;

import com.bit.base.BaseTest;
import com.bit.swaglabs.LoginPage;
import com.bit.utilities.DataProviders;
import com.bit.utilities.TestUtil;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Hashtable;

//public class LoginTest extends BaseTest
public class LoginTest extends BaseTest
{
	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp",description = "Login to Swags Labs Application")
	@Description("Test Description: Login test with valid and invalid credentials")
	public void loginTest(Hashtable<String, String> data)
	{
		log.info("In start of the loginTest()");

		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the case as the Run mode for data set is NO");
		}

//		String screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
		String screenshotName = null;	//"Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
		Date d = new Date();
		screenshotName = d.toString().replace(":","_").replace(" ","_")+".jpg";
//		String url = config.getProperty("url");
		String username = data.get("username");
		String password = data.get("password");
		driver.get(appUrl);
		log.info("Test application URL is loaded!");
		log.info("Page title is: "+driver.getTitle());
		log.info("Username: "+username+" and password is: "+password);  //For debugging
		String expPageTitle = "Swag Labs",actPageTitle;
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);
		TestUtil.captureScreenshot(userDir + "/target/"+screenshotName);
		actPageTitle = driver.getTitle();
		Assert.assertEquals(actPageTitle,expPageTitle,"Login Test is not executed successfully!");
		log.info("In end of the loginTest()");
	}
}
