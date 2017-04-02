package cn.slimsmart.selenium.demo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//浏览器控制
public class TestDemo1 {
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// 获取Driver
		driver = new FirefoxDriver();
	}

	@Test
	public void testWindowSize() throws Exception {
		driver.get("http://www.baidu.com/");
		// 将屏幕最大化
		driver.manage().window().maximize();
		Thread.sleep(3000);
		// 将浏览器大小设置成宽800，高480
		Dimension arg0 = new Dimension(800, 480);
		driver.manage().window().setSize(arg0);
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void testWindowBackAndForward() throws Exception {
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.get("http://news.baidu.com");
		Thread.sleep(3000);
		// 回到百度首页
		driver.navigate().back();
		Thread.sleep(3000);
		// 前进到百度新闻
		driver.navigate().forward();
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void testWindowRefresh() throws Exception {
		driver.get("http://news.baidu.com");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void getWindowInfo() {
		driver.get("http://www.baidu.com");
        driver.manage().window().maximize();
		System.out.println("CurrentUrl: " + driver.getCurrentUrl());
		// System.out.println("PageSource: "+driver.getPageSource());
		System.out.println("Title: " + driver.getTitle());
		System.out.println("WindowHandle: " + driver.getWindowHandle());
		System.out.println("hashCode: " + driver.hashCode());
		driver.quit();	
	}
}
