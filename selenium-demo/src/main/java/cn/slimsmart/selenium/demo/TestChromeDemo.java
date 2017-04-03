package cn.slimsmart.selenium.demo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 测试chrome
 */
public class TestChromeDemo {
	
	WebDriver driver;
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/Administrator/Downloads/chromedriver_win32/chromedriver.exe");
		// 获取Driver
		driver = new ChromeDriver();
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
}
