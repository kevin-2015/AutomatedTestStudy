package cn.slimsmart.selenium.demo;

import org.openqa.selenium.Platform;

public class RemoteBrowserBean {
	private String browserName;
	private String version;
	private Platform platform;
	private String hubURL;

	public RemoteBrowserBean() {
		this.browserName = "firefox";
		this.version = "26";
		this.platform = Platform.WINDOWS;
		this.hubURL = "http://localhost:4444/wd/hub";
	}

	public RemoteBrowserBean(String browser) {
		this.browserName = browser;
		this.version = "26";
		this.platform = Platform.WINDOWS;
		this.hubURL = "http://localhost:4444/wd/hub";
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getHubURL() {
		return hubURL;
	}

	public void setHubURL(String hubURL) {
		this.hubURL = hubURL;
	}
}
