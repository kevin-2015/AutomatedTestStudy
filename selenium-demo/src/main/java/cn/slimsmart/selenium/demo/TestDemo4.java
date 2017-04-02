package cn.slimsmart.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//键盘事件
public class TestDemo4 {
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();
		// 输入框输入内容
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("selenium");
		// 删除一个m
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys(Keys.BACK_SPACE);
		// 输入m 空格 教程
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("m");
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys(Keys.SPACE);
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys("教程");
		// 全选输入框内容
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys(Keys.CONTROL,
				"a");
		// 剪切输入框内容
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys(Keys.CONTROL,
				"x");
		// 粘贴输入框内容
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys(Keys.CONTROL,
				"v");
		// 通过回车来代替提交动作
		driver.findElement(By.xpath("//*[@id='kw']")).sendKeys(Keys.ENTER);
		waitTime(5000);
		driver.quit();
	}

	static public void waitTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
