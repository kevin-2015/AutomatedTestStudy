package cn.slimsmart.selenium.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDemo5 {

	/**
	 * 显式等待可以使用selenium预置的判断方法，也可以使用自定义的方法。 1.除了以上内容，selenium还提供了很多预置的判断方法。
	 * 2.这些判断方法，在超出时间限制时就会抛出异常。
	 */
	@Test
	public void testExplicitWait() throws Exception {
		WebDriver driver = new FirefoxDriver();
		try {
			driver.get("http://www.baidu.com");
			driver.manage().window().maximize();

			// 标题是不是“百度一下，你就知道”
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.titleIs("百度一下，你就知道"));
			// 标题是不是包含“百度一下”
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.titleContains("百度一下"));
			// 判断该元素是否被加载在DOM中，并不代表该元素一定可见
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id='kw']")));
			// 判断元素(定位后)是否可见
			new WebDriverWait(driver, 5)
					.until(ExpectedConditions.visibilityOf(driver
							.findElement(By.xpath("//*[@id='kw']"))));
			// 判断元素是否可见（非隐藏，并且元素的宽和高都不等以0）
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id='kw']")));
			// 只要存在一个就是true
			ExpectedConditions.presenceOfAllElementsLocatedBy(By
					.xpath("//*[@id='kw']"));
			// 元素中的text是否包含语气的字符串
			ExpectedConditions.textToBePresentInElementLocated(
					By.xpath("//*[@id='kw']"), "百度一下");
			// 元素的value属性中是否包含语气的字符串
			ExpectedConditions.textToBePresentInElementValue(
					By.xpath("//*[@id='kw']"), "***");
			// 判断该表单是否可以切过去，可以就切过去并返回true，否则放回false
			ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("**"));
			// 判断某个元素是否不存在于DOM或不可见
			ExpectedConditions.invisibilityOfElementLocated(By
					.xpath("//*[@id='kw']"));
			// 判断元素是否可以点击
			ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kw']"));
			// 等到一个元素从DOM中移除
			ExpectedConditions.stalenessOf(driver.findElement(By
					.xpath("//*[@id='kw']")));
			// 判断某个元素是否被选中，一般用在下拉列表
			ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='kw']"));
			// 判断某个元素的选中状态是否符合预期
			ExpectedConditions.elementSelectionStateToBe(
					By.xpath("//*[@id='kw']"), true);
			// 判断某个元素(已定位)的选中状态是否符合预期
			ExpectedConditions.elementSelectionStateToBe(
					driver.findElement(By.xpath("//*[@id='kw']")), false);
			// 判断页面中是否存在alert
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.alertIsPresent());
			// --------------------自定义判断条件-----------------------------
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return !driver.findElement(By.xpath("//*[@id='kw']"))
							.getAttribute("class")
							.contains("x-form-invalid-field");
				}
			});

			Thread.sleep(3000);
		} finally {
			driver.quit();
		}
	}

	/**
	 * 隐式等待相当于设置全局的等待，在定位元素时，对所有元素设置超时时间。
	 * 隐式等待使得WebDriver在查找一个Element或者Element数组时
	 * ，每隔一段特定的时间就会轮询一次DOM，如果Element或数组没有马上被发现的话。
	 * 默认设置是0。一旦设置，这个隐式等待会在WebDriver对象实例的整个生命周期起作用。一劳永逸。
	 */
	@Test
	public void testImplicitWait() throws Exception {
		WebDriver driver = new FirefoxDriver();
		// 设置隐式等待
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();

		try {
			SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss-SSS");
			String time = format.format(Calendar.getInstance().getTime());
			System.out.println("开始的时间： " + time);

			driver.findElement(By.id("kw22")).sendKeys("selenium");

		} catch (NoSuchElementException e) {
			System.out.println("没有找到元素");
			e.printStackTrace();
		} finally {
			SimpleDateFormat format2 = new SimpleDateFormat("HH-mm-ss-SSS");
			String time2 = format2.format(Calendar.getInstance().getTime());
			System.out.println("结束的时间： " + time2);
			driver.quit();
		}
	}

}
