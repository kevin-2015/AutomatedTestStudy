package cn.slimsmart.selenium.demo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

/**
 * 鼠标事件
1.右击
2.双击
3.拖到
4.悬停
 */
public class TestDemo3 {
	public static void main(String[] args) {
		//根據自己的系統環境配置
        FirefoxProfile profile = new FirefoxProfile(
                new File("C:/Users/Administrator/AppData/Roaming/Mozilla/Firefox/Profiles/oby00qhr.default"));
        WebDriver driver = new FirefoxDriver(profile);
        driver.get("http://c37.yunpan.360.cn");
        driver.manage().window().maximize();
        waitTime(3000);

        driver.findElement(By.xpath("//*[@id='infoPanel']/a[2]")).click();
        waitTime(3000);

        driver.findElement(By.xpath("//*[@id='tbText']")).click();
        WebElement testitem = driver.findElement(By.xpath("//*[@id='list']/li[1]/div[2]/span[2]"));
        testitem.click();
        waitTime(3000);

        // 左击实现（和元素的click类似）
        Actions action = new Actions(driver);
        WebElement test1item = driver.findElement(By.xpath("//*[@id='list']/li[1]/div[2]/span[2]"));
        //perform()的作用是 执行所有Actions中存储的行为。
        action.click(test1item).perform();
        waitTime(3000);

        // 返回上一级
        driver.findElement(By.xpath("//*[@id='crumb']/div/span[1]")).click();
        waitTime(3000);

        // 双击实现
        new Actions(driver).doubleClick(driver.findElement(By.xpath("//*[@id='list']/li[1]/div[2]/span[2]"))).perform();
        waitTime(3000);

        // 返回上一级
        driver.findElement(By.xpath("//*[@id='crumb']/div/span[1]")).click();
        waitTime(3000);

        // 悬停 到更多按钮实现
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//*[@id='topPanel']/ul/li[3]/a"))).perform();

        // 拖动实现
        driver.findElement(By.xpath("//*[@id='tbPic']")).click();
        WebElement begin = driver.findElement(By.xpath("//*[@id='list']/li[1]/div[2]/span[1]"));
        WebElement end = driver.findElement(By.xpath("//*[@id='list']/li[2]/div[2]/span[1]"));
        new Actions(driver).dragAndDrop(begin, end).perform();

        // 右击实现
        // 这里虽然使用的是元素任然是test1item，但是页面刷新过后需要重新定位
        // 参考http://docs.seleniumhq.org/exceptions/stale_element_reference.jsp
        driver.findElement(By.xpath("//*[@id='tbText']")).click();
        new Actions(driver).contextClick(driver.findElement(By.xpath("//*[@id='list']/li[1]/div[2]/span[2]")))
                .perform();
        waitTime(3000);
        driver.findElement(By.xpath("//*[@id='x-yp-3']/ul/li[4]/a/span")).click();
        waitTime(3000);
        driver.quit();
    }
    static public void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
