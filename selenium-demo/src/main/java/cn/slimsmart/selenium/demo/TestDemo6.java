package cn.slimsmart.selenium.demo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDemo6 {
	WebDriver driver = null;
	String path = "";

	@Before
	public void before() {
		driver = new FirefoxDriver();
		path = new java.io.File("").getAbsolutePath() + "\\src\\main\\resources\\html\\";
	}

	@After
	public void after() {
		driver.quit();
	}

	// 组元素操作
	@Test
	public void testCheckBox() throws Exception {
		driver.get("file:/" + path + "testDemo6-1.html");
		driver.manage().window().maximize();
		List<WebElement> WebElements = driver.findElements(By.cssSelector("[type=checkbox]"));
		Iterator iterator = WebElements.iterator();
		// 全部勾选
		while (iterator.hasNext()) {
			WebElement wb = (WebElement) iterator.next();
			wb.click();
		}
		// 去掉第一个
		Thread.sleep(3000);
		driver.findElements(By.cssSelector("[type=checkbox]")).get(0).click();
		// 去掉最后一个
		Thread.sleep(3000);
		driver.findElements(By.cssSelector("[type=checkbox]")).get(WebElements.size() - 1).click();
		Thread.sleep(3000);
	}

	// 多表单切换
	@Test
	public void testFrameSwitch() throws Exception {
		driver.get("file:/" + path + "testDemo6-2.html");
		driver.manage().window().maximize();
		// 利用id来切换
		Thread.sleep(3000);
		driver.switchTo().frame("if");

		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='kw']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("switch successfully");
			// 切换到父frame
			driver.switchTo().parentFrame();
			try {
				driver.findElement(By.xpath("//*[@id='kw']"));
			} catch (NoSuchElementException e) {
				String js = "alert(\"switch to parent\")";
				((JavascriptExecutor) driver).executeScript(js);
				Thread.sleep(3000);
				driver.switchTo().alert().dismiss();
			}
		}

		// 利用name来切换
		Thread.sleep(3000);
		driver.switchTo().frame("nf");

		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='kw']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("switch successfully");
			driver.switchTo().parentFrame();
			try {
				driver.findElement(By.xpath("//*[@id='kw']"));
			} catch (NoSuchElementException e) {
				String js = "alert(\"switch to parent\")";
				((JavascriptExecutor) driver).executeScript(js);
				Thread.sleep(3000);
				driver.switchTo().alert().dismiss();
			}
		}

		// 利用定位元素来切换-xpath
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='if']")));

		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='kw']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("switch successfully");
			driver.switchTo().parentFrame();
			try {
				driver.findElement(By.xpath("//*[@id='kw']"));
			} catch (NoSuchElementException e) {
				String js = "alert(\"switch to parent\")";
				((JavascriptExecutor) driver).executeScript(js);
				Thread.sleep(3000);
				driver.switchTo().alert().dismiss();
			}
		}

		// 利用定位元素来切换-css
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#if")));

		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='kw']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("switch successfully");
			driver.switchTo().parentFrame();
			try {
				driver.findElement(By.xpath("//*[@id='kw']"));
			} catch (NoSuchElementException e) {
				String js = "alert(\"switch to parent\")";
				((JavascriptExecutor) driver).executeScript(js);
				Thread.sleep(3000);
				driver.switchTo().alert().dismiss();
				driver.quit();
			}
		}
	}

	// 多窗口切换
	@Test
	public void testWindowsSwitch() throws Exception {
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();

		String searchHandle = driver.getWindowHandle();
		System.out.println("baidu search handle : " + searchHandle);

		// 获取百度新闻的连接，然后利用js打开一个新的窗口
		Thread.sleep(3000);
		String href = driver.findElement(By.cssSelector("[name=tj_trnews]")).getAttribute("href");
		System.out.println("the link of news is : " + href);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.open('" + href + "')");

		Thread.sleep(3000);
		Set<String> handles = driver.getWindowHandles();
		Iterator iterator = handles.iterator();
		Iterator iterator2 = handles.iterator();

		// 进入百度新闻窗口,并获取title验证
		Thread.sleep(3000);
		while (iterator.hasNext()) {
			String h = (String) iterator.next();
			if (h != searchHandle) {
				driver.switchTo().window(h);
				if (driver.getTitle().contains("百度新闻")) {
					System.out.println("switch to news page successfully");
					break;
				} else {
					continue;
				}
			}

		}

		// 返回百度首页
		Thread.sleep(3000);
		/*
		 * 可以直接使用searchHandle来切换，也可以利用遍历来切换
		 * driver.switchTo().window(searchHandle);
		 * if(driver.getTitle().contains("百度一下")){
		 * System.out.println("switch to search page successfully");
		 * driver.findElement(By.cssSelector("#kw")).sendKeys(
		 * "switch successfully"); }
		 */
		while (iterator2.hasNext()) {
			String h = (String) iterator2.next();
			if (searchHandle.equals(h)) {
				driver.switchTo().window(h);
				if (driver.getTitle().contains("百度一下")) {
					System.out.println("switch to search page successfully");
					driver.findElement(By.cssSelector("#kw")).sendKeys("switch successfully");
					break;
				} else {
					continue;
				}
			}

		}
		Thread.sleep(3000);
	}

	// 告警框的处理方法
	@Test
	public void testAlterHandle() throws Exception {
		driver.get("file:/" + path + "testDemo6-3.html");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("#altertest")).click();
		// 先等待prompt框的出现，然后输入内容
		new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().sendKeys("处理告警框的例子");

		// 确认输入内容
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		// 获取Alert框内text内容
		Thread.sleep(3000);
		new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
		String inputInfo = driver.switchTo().alert().getText();
		System.out.println(inputInfo);

		// 关闭Alert框
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		// 利用js构造一个confirm框
		Thread.sleep(3000);
		String js = "confirm(\"这就是一个告警框的例子\")";
		((JavascriptExecutor) driver).executeScript(js);

		// 取消confirm框
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(3000);
	}

	// 上传文件
	// 当页面中是通过input标签实现上传功能时，可以使用selenium来上传功能。
	/**
	 * 2.如果网页中的上传功能不是使用input来实现，那就需要使用其他方法来实现模拟 可以使用AutoIt录制脚本实现： 使用方法参考：
	 * http://www.cnblogs.com/fnng/p/4188162.html 工具下载地址：
	 * 官网：https://www.autoitscript.com/site/autoit/downloads/
	 * 网盘：http://pan.baidu.com/s/1cievQe
	 */
	@Test
	public void testUpload() throws Exception {
		driver.get("file:/" + path + "testDemo6-4.html");
		driver.manage().window().maximize();
		System.out.println(path + "testDemo6-4.html");
		File file = new File(path + "testDemo6-4.html");
		if (file.exists()) {
			// 找到input，然后利用sendKeys来上传文件
			driver.findElement(By.tagName("input")).sendKeys(file.getPath());
			System.out.println(file.getPath());
		}
		Thread.sleep(3000);
	}

	// 下载文件
	// 下载文件需要在Firefox 的profile属性中配置一些参数
	@Test
	public void testDownload() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		// 可以在Firefox浏览器地址栏中输入about:config来查看属性
		// 设置下载文件放置路径，注意如果是windows环境一定要用\\,用/不行
		String downloadFilePath = path + "apache-flume-1.7.0-src.tar.gz";
		File file = new File(downloadFilePath);
		if (file.exists()) {
			file.delete();
		}
		// 配置响应下载参数
		profile.setPreference("browser.download.dir", path);// 下载路径
		profile.setPreference("browser.download.folderList", 2);// 2为保存在指定路径，0代表默认路径
		profile.setPreference("browser.download.manager.showWhenStarting", false);// 是否显示开始
		// 禁止弹出保存框，value是文件格式，如zip文件
		profile.setPreference(
				"browser.helperApps.neverAsk.saveToDisk",
				"application/zip,application/x-gzip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		// 关于类型：可以参考http://www.w3school.com.cn/media/media_mimeref.asp
		WebDriver driver = new FirefoxDriver(profile);
		driver.get("file:/" + path + "testDemo6-5.html");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("下载")).click();

		Thread.sleep(3000);
		String js_exist = "alert(\"download successfully\")";
		String js_not_exist = "alert(\"download unsuccessfully\")";

		if (file.exists()) {
			((JavascriptExecutor) driver).executeScript(js_exist);
		} else {
			((JavascriptExecutor) driver).executeScript(js_not_exist);
		}
		Thread.sleep(10000);
		driver.quit();
	}

	// 操作cookie
	@Test
	public void testCookieOperation() throws Exception {
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();
		// 获取所有cookie个数
		System.out.println(driver.manage().getCookies().size());
		// 增加cookie
		Cookie cookie = new Cookie("username", "name", "/", null);
		driver.manage().addCookie(cookie);
		driver.manage().addCookie(new Cookie("password", "ppppwwww", "/", null));
		// 以name获取cookie
		String name = driver.manage().getCookieNamed("username").getValue();
		String info = "用户名是： " + name;
		String js = "alert(\"" + info + "\");";
		System.out.println(js);
		((JavascriptExecutor) driver).executeScript(js);
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		// 以name删除cookie
		driver.manage().deleteCookieNamed("password");
		// 再次获取所有cookie个数，应该比之前多一个
		System.out.println(driver.manage().getCookies().size());
		driver.quit();
	}

	// 执行JS
	// 参考：http://www.cnblogs.com/tobecrazy/p/4817946.html
	@Test
	public void testJavascript() throws Exception {
		// scroll
		driver.get("http://www.baidu.com");
		driver.manage().window().setSize(new Dimension(600, 600));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#kw")).sendKeys("selenium");
		driver.findElement(By.cssSelector("#su")).click();
		Thread.sleep(3000);
		String js = "window.scrollTo(100,450);";
		((JavascriptExecutor) driver).executeScript(js);
		Thread.sleep(3000);
		// 利用JS来输入内容
		String text = "input by js";
		js = "var dom = document.getElementById('kw'); dom.value='" + text + "';";
		System.out.println(js);
		((JavascriptExecutor) driver).executeScript(js);
		Thread.sleep(3000);
	}

	// 截图
	@Test
	public void testScreenShot() throws Exception {
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();
		// 截图到output
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String savePath = path + "\\screenshot.png";
			File file = new File(savePath);
			if (file.exists()) {
				file.delete();
			}
			// 复制内容到指定文件中
			FileUtils.copyFile(scrFile, new File(savePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 关闭窗口
	// quit方法：退出相关的驱动程序和关闭所有窗口
	// close方法：关闭当前窗口
	@Test
	public void testWindowsClose() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();

		String searchHandle = driver.getWindowHandle();
		String newsHandle = null;
		System.out.println("baidu search handle : " + searchHandle);

		// 获取百度新闻的连接，然后利用js打开一个新的窗口
		Thread.sleep(3000);
		String href = driver.findElement(By.cssSelector("[name=tj_trnews]")).getAttribute("href");
		System.out.println("the link of news is : " + href);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.open('" + href + "')");

		Thread.sleep(3000);
		Set<String> handles = driver.getWindowHandles();
		Iterator iterator = handles.iterator();
		Iterator iterator2 = handles.iterator();

		// 进入百度新闻窗口,并获取title验证
		Thread.sleep(3000);
		while (iterator.hasNext()) {
			String h = (String) iterator.next();
			if (h != searchHandle) {
				driver.switchTo().window(h);
				if (driver.getTitle().contains("百度新闻")) {
					System.out.println("switch to news page successfully");
					newsHandle = driver.getWindowHandle();
					break;
				} else {
					continue;
				}
			}

		}

		// 返回百度首页
		Thread.sleep(3000);
		/*
		 * 可以直接使用searchHandle来切换，也可以利用遍历来切换
		 * driver.switchTo().window(searchHandle);
		 * if(driver.getTitle().contains("百度一下")){
		 * System.out.println("switch to search page successfully");
		 * driver.findElement(By.cssSelector("#kw")).sendKeys(
		 * "switch successfully"); }
		 */
		while (iterator2.hasNext()) {
			String h = (String) iterator2.next();
			if (searchHandle.equals(h)) {
				driver.switchTo().window(h);
				if (driver.getTitle().contains("百度一下")) {
					System.out.println("switch to search page successfully");
					driver.findElement(By.cssSelector("#kw")).sendKeys("switch successfully");
					driver.close();// 只关闭百度首页
					System.out.println("close search page successfully");
					driver.switchTo().window(newsHandle);// 切换到百度新闻
					System.out.println("当前的title是： " + driver.getTitle());// 获取title
					Thread.sleep(3000);
					break;
				} else {
					continue;
				}
			}

		}
		Thread.sleep(3000);
	}

	@Test
	public void testSelect() throws Exception {
		driver.get("file:/"+path+"testDemo6-6.html");
		driver.manage().window().maximize();
		driver.findElement(By.id("id1")).sendKeys("4");
		Select sel = new Select(driver.findElement(By.name("calc")));
		sel.selectByValue("/");
		driver.findElement(By.id("id3")).sendKeys("2");
		driver.findElement(By.id("id4")).click();
		System.out.println(driver.findElement(By.id("id5")).getAttribute("value"));

		// //////////////////////////////////////////////////////////////
		driver.findElement(By.id("id1")).clear();
		driver.findElement(By.id("id1")).sendKeys("3");

		Select sel2 = new Select(driver.findElement(By.name("calc")));
		sel2.selectByValue("+");

		driver.findElement(By.id("id3")).clear();
		driver.findElement(By.id("id3")).sendKeys("1");

		driver.findElement(By.id("id4")).click();

		System.out.println(driver.findElement(By.id("id5")).getAttribute("value"));

		// //////////////////////////////////////////////////////////////
		driver.findElement(By.id("id1")).clear();
		driver.findElement(By.id("id1")).sendKeys("5");

		Select sel3 = new Select(driver.findElement(By.name("calc")));
		sel3.selectByValue("*");

		driver.findElement(By.id("id3")).clear();
		driver.findElement(By.id("id3")).sendKeys("6");

		driver.findElement(By.id("id4")).click();

		System.out.println(driver.findElement(By.id("id5")).getAttribute("value"));

		// //////////////////////////////////////////////////////////////
		driver.findElement(By.id("id1")).clear();
		driver.findElement(By.id("id1")).sendKeys("100");

		Select sel4 = new Select(driver.findElement(By.name("calc")));
		sel4.selectByValue("-");

		driver.findElement(By.id("id3")).clear();
		driver.findElement(By.id("id3")).sendKeys("1");

		driver.findElement(By.id("id4")).click();

		System.out.println(driver.findElement(By.id("id5")).getAttribute("value"));

		// //////////////////////////////////////////////////////////////////////////////
		Select selall = new Select(driver.findElement(By.name("calc")));
		List<WebElement> lw = selall.getOptions();
		Iterator<WebElement> iterator = lw.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getAttribute("value"));
		}
	}
}
