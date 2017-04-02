package cn.slimsmart.selenium.demo;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//selenium定位方法
public class TestDemo {
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// 指定firefox的安装路径,安装完成后最好将Firefox的安装路径加入Path中，
		//不然就需要用下面的方法指定路径后才能新建FirefoxDriver实例
		System.setProperty("webdriver.firefox.bin",
				"C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		// 获取Driver
		driver = new FirefoxDriver();

		driver.get("http://www.baidu.com/");
		// 将屏幕最大化
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		// 退出浏览器
		driver.quit();
	}

	// 通过ID来定位
	@Test
	public void test001_GetByID() throws Exception {
		// 清空输入框，并输入查询关键字“selenium”，然后点击查询按钮
		driver.findElement(By.id("kw")).clear();
		driver.findElement(By.id("kw")).sendKeys("selenium");
		driver.findElement(By.id("su")).click();
		Thread.sleep(1000);// 演示待页面异步加载完毕
		WebElement el = driver.findElement(By
				.linkText("Selenium - Web Browser Automation"));
		Boolean flag = el.isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// 通过Name来定位
	@Test
	public void test002_GetByName() throws Exception {
		// 清空输入框，并输入查询关键字“selenium”，然后点击查询按钮
		driver.findElement(By.name("wd")).clear();
		driver.findElement(By.name("wd")).sendKeys("selenium");
		driver.findElement(By.id("su")).click();
		Thread.sleep(1000);
		WebElement el = driver.findElement(By
				.partialLinkText("Web Browser Automation"));
		Boolean flag = el.isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// 通过Class定位
	@Test
	public void test003_GetByClass() throws Exception {
		// 清空输入框，并输入查询关键字“selenium”，然后点击查询按钮
		driver.findElement(By.className("s_ipt")).clear();
		driver.findElement(By.className("s_ipt")).sendKeys("selenium");
		driver.findElement(By.id("su")).click();
		Thread.sleep(1000);
		WebElement el = driver.findElement(By
				.partialLinkText("Web Browser Automation"));
		Boolean flag = el.isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// 通过Tag来定位
	@Test
	public void test004_GetByTag() throws InterruptedException {
		// 清空输入框，并输入查询关键字“selenium”，然后点击查询按钮
		// input 这个tag在百度首页中比较多，所以需要使用findElements(),将所有input返回，并从中找寻需要的两个input
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		Iterator<WebElement> it = inputs.iterator();
		WebElement we = null;
		WebElement inputWe = null;
		WebElement buttonWe = null;
		while (it.hasNext()) {
			we = (WebElement) it.next();
			if (we.getAttribute("id").toString().equals("kw")) {
				inputWe = we;
			}

			if (we.getAttribute("id").toString().equals("su")) {
				buttonWe = we;
			}
		}

		// 找到之后开始操作
		if (inputWe != null && buttonWe != null) {
			inputWe.clear();
			inputWe.sendKeys("selenium");
			buttonWe.click();
		} else {
			System.out.println("can not find input and button");
		}
		Thread.sleep(1000);
		// 判断结果
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// 通过Link来定位
	@Test
	public void test005_GetByLink() {
		// 点击新闻链接
		driver.findElement(By.linkText("新闻")).click();
		// 获取title
		Boolean flag = driver.getTitle().toString().contains("新闻");
		assertTrue("\"新闻\" is not included in title", flag);
	}

	// 通过PartialLink来定位
	@Test
	public void test006_GetByPartialLink() {
		driver.findElement(By.partialLinkText("使用百度")).click();
		// 获取title
		Boolean flag = driver.getTitle().toString().contains("百度免责");
		assertTrue("the page of \"百度免责声明\" is not open", flag);
	}

	// XPath定位
	// XPath定位-1.绝对路径
	@Test
	public void test007_GetByAbsolutePath() throws Exception {
		driver.findElement(
				By.xpath("/html/body/div/div/div/div/div/form/span/input"))
				.sendKeys("selenium");
		driver.findElement(
				By.xpath("/html/body/div/div/div/div/div/form/span[2]/input"))
				.click();
		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	@Test
	// XPath定位-2.相对路径
	public void test008_GetByRelativePath() throws Exception {
		driver.findElement(By.xpath("//form/span/input")).sendKeys("selenium");
		driver.findElement(By.xpath("//form/span[2]/input")).click();
		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// XPath定位-3.利用元素属性定位
	@Test
	public void test009_GetByElementAttribute() throws Exception {
		driver.findElement(By.xpath("//input[@id='kw']")).sendKeys("selenium");
		driver.findElement(By.xpath("//input[@id='su']")).click();
		/*
		 * // 表示当前页面某个某个目录 input 表示定位元素的标签名 [@id='kw']表示这个元素的id属性值等于kw
		 * 
		 * 除了使用id这个属性，也可以使用name和class属性等其他属性来定位，只要值是唯一的。1.//input[@name='wd']
		 * 2.//input[@class='s_ipt']3.//*[@class='bg s_btn'] 符号* 代表不想指定标签名
		 */
		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// XPath定位-4.层级与属性结合
	@Test
	public void test010_GetByParent() throws Exception {

		driver.findElement(
				By.xpath("//span[@class='bg s_ipt_wr iptfocus quickdelete-wrap']/input"))
				.sendKeys("selenium");
		driver.findElement(By.xpath("//span[@class='bg s_btn_wr']/input"))
				.click();
		// 可以通过先定位父元素，在加上层级关系来定位目标元素。 这种方法还可以扩展到先查找爷爷元素。
		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// XPath定位-5.使用逻辑运算符
	@Test
	public void test011_GetByAttributeCombination() throws InterruptedException {
		// 这里就是元素属性的组合
		driver.findElement(By.xpath("//input[@class='s_ipt' and @id='kw']"))
				.sendKeys("selenium");
		driver.findElement(By.xpath("//input[@class='bg s_btn' and @id='su']"))
				.click();

		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// CSS定位
	// CSS定位-1.通过class属性定位
	@Test
	public void test012_GetByClassAttribute() throws InterruptedException {

		// 使用符号(.)来表示用class属性来定位
		driver.findElement(By.cssSelector(".s_ipt")).sendKeys("selenium");
		driver.findElement(By.id("su")).click();
		;

		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// CSS定位-2.通过id属性定位
	@Test
	public void test013_GetByIdAttribute() throws InterruptedException {

		// 使用符号(#)来表示用id属性来定位
		driver.findElement(By.cssSelector("#kw")).sendKeys("selenium");
		driver.findElement(By.cssSelector("#su")).click();
		;

		Thread.sleep(1000);
		Boolean flag = driver.findElement(
				By.partialLinkText("Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// CSS定位-3.通过标签名定位
	@Test
	public void test014_GetByTagAttribute() {
		// 清空输入框，并输入查询关键字“selenium”，然后点击查询按钮
		// input 这个tag在百度首页中比较多，所以需要使用findElements(),将所有input返回，并从中找寻需要的两个input
		List<WebElement> inputs = driver.findElements(By.cssSelector("input"));
		Iterator<WebElement> it = inputs.iterator();
		WebElement we = null;
		WebElement inputWe = null;
		WebElement buttonWe = null;
		while (it.hasNext()) {
			we = (WebElement) it.next();
			if (we.getAttribute("id").toString().equals("kw")) {
				inputWe = we;
			}

			if (we.getAttribute("id").toString().equals("su")) {
				buttonWe = we;
			}
		}

		// 找到之后开始操作
		if (inputWe != null && buttonWe != null) {
			inputWe.clear();
			inputWe.sendKeys("selenium");
			buttonWe.click();
		} else {
			System.out.println("can not find input and button");
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 判断结果
		Boolean flag = driver.findElement(
				By.linkText("Selenium - Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// CSS定位-通过父子关系定位
	@Test
	public void test015_GetByParentTag() {
		// 清空输入框，并输入查询关键字“selenium”，然后点击查询按钮
		// input 这个tag在百度首页中比较多，所以需要使用findElements(),将所有input返回，并从中找寻需要的两个input
		List<WebElement> inputs = driver.findElements(By
				.cssSelector("span>input"));
		Iterator<WebElement> it = inputs.iterator();
		WebElement we = null;
		WebElement inputWe = null;
		WebElement buttonWe = null;
		while (it.hasNext()) {
			we = (WebElement) it.next();
			if (we.getAttribute("id").toString().equals("kw")) {
				inputWe = we;
			}

			if (we.getAttribute("id").toString().equals("su")) {
				buttonWe = we;
			}
		}

		// 找到之后开始操作
		if (inputWe != null && buttonWe != null) {
			inputWe.clear();
			inputWe.sendKeys("selenium");
			buttonWe.click();
		} else {
			System.out.println("can not find input and button");
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 判断结果
		Boolean flag = driver.findElement(
				By.linkText("Selenium - Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// CSS定位-通过属性定位
	@Test
	public void test016_GetByAttribute() {
		// 在中括号[]中可以放置任意唯一的属性值对来定位
		driver.findElement(By.cssSelector("[id=kw]")).sendKeys("selenium");
		driver.findElement(By.cssSelector("[id=su]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean flag = driver.findElement(
				By.linkText("Selenium - Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}

	// CSS定位-通配符
	@Test
	public void test017_GetByAttributeAndWildcard() {
		// [class$=_ipt]代表以_ipt结尾
		// [class^=bg]代表以bg开头
		// [class*=_ip]代表中间内容是_ip
		// 这种方法存在一定的不稳定性，因为会出现匹配到多个元素的情况
		driver.findElement(By.cssSelector("[class$=_ipt]"))
				.sendKeys("selenium");
		driver.findElement(By.cssSelector("[class^=bg]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Boolean flag = driver.findElement(
				By.linkText("Selenium - Web Browser Automation")).isDisplayed();
		assertTrue("\"Selenium - Web Browser Automation\" is not display", flag);
	}
	
	//CSS定位-组合定位
    @Test
    public void test018_GetByCombination(){
        //通过先定位父元素，再定位子元素。通过元素的class或者id属性来定位。
        driver.findElement(By.cssSelector("form.fm>span>input.s_ipt")).sendKeys("selenium");
        driver.findElement(By.cssSelector("form#form>span>input#su")).click();;
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Boolean flag = driver.findElement(By.linkText("Selenium - Web Browser Automation")).isDisplayed();
        assertTrue("\"Selenium - Web Browser Automation\" is not display",flag);
    }
}
