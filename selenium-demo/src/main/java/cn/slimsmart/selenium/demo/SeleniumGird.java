package cn.slimsmart.selenium.demo;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumGird {

	@Test
	public void testGrid() throws IOException {
		RemoteBrowserBean remoteBrowserBean = new RemoteBrowserBean("firefox");
		remoteBrowserBean.setHubURL("http://192.168.145.132:1234/wd/hub");
		remoteBrowserBean.setPlatform(Platform.WIN8_1);
		WebDriver driver = DriverFactory.getRemoteDriver(remoteBrowserBean);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://www.baidu.com");
		driver.findElement(By.id("kw")).clear();
		driver.findElement(By.id("kw")).sendKeys("selenium");
		driver.findElement(By.id("su")).click();
		WebElement el = driver.findElement(By
				.linkText("Selenium - Web Browser Automation"));
		Boolean flag = el.isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}
}
