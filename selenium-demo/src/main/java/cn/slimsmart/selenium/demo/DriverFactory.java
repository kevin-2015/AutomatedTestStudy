package cn.slimsmart.selenium.demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	public static WebDriver getRemoteDriver(RemoteBrowserBean remoteBrowserBean) {
		DesiredCapabilities capability = null;
		if (remoteBrowserBean.getBrowserName().contains("opera")) {
			capability = DesiredCapabilities.operaBlink();
		} else if (remoteBrowserBean.getBrowserName().contains("chrome")) {
			capability = DesiredCapabilities.chrome();
		} else if (remoteBrowserBean.getBrowserName().contains("safari")) {
			capability = DesiredCapabilities.safari();
		}else if(remoteBrowserBean.getBrowserName().contains("ie")) {
			capability = DesiredCapabilities.internetExplorer();
		}else{
			capability = DesiredCapabilities.firefox();
		}
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(remoteBrowserBean.getHubURL()), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		capability.setBrowserName(remoteBrowserBean.getBrowserName());
		capability.setVersion(remoteBrowserBean.getVersion());
		capability.setPlatform(remoteBrowserBean.getPlatform());
		driver.manage().window().maximize();
		return driver;
	}
}
