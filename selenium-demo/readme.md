**基于selenium开发web测试**
---

Selenium 是用于测试 Web 应用程序用户界面 (UI) 的常用框架。它是一款用于运行端到端功能测试的超强工具。您可以使用多个编程语言编写测试，并且 Selenium 能够在一个或多个浏览器中执行这些测试。
官网地址：http://seleniumhq.org/
项目地址：https://github.com/SeleniumHQ/selenium
wiki地址：https://github.com/SeleniumHQ/selenium/wiki

**1.环境搭建**

FireFox的WebDriver实现了FireFoxDriver，无需用户下载FireFoxDriver。FireFoxDriver对页面的自动化测试支持得比较好，很直观地模拟页面的操作，对JavaScript的支持也非常完善，基本上页面上做的所有操作FireFox Driver都可以模拟，所以在开发测试case一般使用Firefox浏览器。需要并在添加组件页面的搜索输入框内输入firebug和firepath（定位元素），进行分别搜索和安装，安装完成后重启FireFox浏览器，使插件生效。

selenium和Firefox常常会出现版本不匹配的问题，常见的现象是Firefox浏览器启动时就报错，所以就需要使用老版本的FireFox。

1.[Firefox下载地址](https://ftp.mozilla.org/pub/firefox/releases/)

2.[selenium下载地址](http://selenium-release.storage.googleapis.com/index.html)

3.[关于webdriver对各种浏览器的支持说明](http://www.07net01.com/linux/webdriverduigezhongliulanqidezhichi_588995_1379679901.html)

也可以在这下载：[selenium自动化测试工具Firefox以及插件](http://download.csdn.net/detail/tianwei7518/9801880)

**2.eclipse环境**

新建工程将下载的selenium-java-2.53.1.zip里面的jar包导入eclipse里面，或者使用maven引入jar包

**3.取页面元素**

![image](https://github.com/slimina/fitnesse_demo/blob/master/images/950020-20160705210138936-1387302722.png?raw=true)

实例：

![image](https://github.com/slimina/fitnesse_demo/blob/master/images/402003944.png?raw=true)

**4.获取元素xpath**

1.火狐的firepath（安装firepath和firebug），安装完成后，打开debug控制台切换到firepath，使用拾取器选中页面一元素，即可在xpath显示当前元素的路径
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/12.png?raw=true)

参考：http://blog.csdn.net/sxl0727tu/article/details/51897693

2.chrome的xpath插件，选中元素，copy -->copy xpth即可获取元素路径
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/13.png?raw=true)

**5.总结图(源于网上)**
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/21.png?raw=true)
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/22.png?raw=true)
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/23.png?raw=true)
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/24.png?raw=true)

**6.Selenium grid**

可在 Selenium 2 中进行本地或远程测试。要实现远程运行，该测试需要使用名为 RemoteWebDriver的 WebDriver接口的特定实现。您可以指定浏览器采用 DesiredCapabilities类运行
```java
 DesiredCapabilities capabilities = new DesiredCapabilities(); 
 capabilities.setBrowserName("firefox"); 
 capabilities.setVersion("7"); 
 capabilities.setPlatform("MAC"); 
 WebDriver webdriver = new RemoteWebDriver(capabilities);
```
借助 DesiredCapabilities类，您可以指定浏览器的名称、平台和浏览器版本。您还可指定浏览器支持的其他功能。如果想要远程执行结构化测试，并运行多个浏览器（并且可能是不同的虚拟机），Selenium Grid 提供了很好的解决方案。
Selenium Grid 2 提供了基础设施，其中每个节点代表了不同浏览器将自身注册到 hub 当中。单数的测试将会调用一个 hub，它负责将每个请求分配到正确的浏览器。Hub 和节点可以运行在不同的虚拟机当中。要实现远程测试，则需要在您将要使用的每台机器上下载 selenium-server-standalone-<version>.jar,并在机器上安装 hub
>java -jar selenium-server-standalone-2.53.1.jar -role hub

其默认监听端口4444，默认IP localhost ，如果要修改，只需要加-port 参数和-Hubhost。如：
>java -jar selenium-server-standalone-2.53.1.jar -role hub -port 1234 -Hubhost 10.1.199.168

您可在 http://10.1.199.168:1234/grid/console 访问 Grid 2 控制台，其中会列出所有可用的节点。要注册一个节点，仅需运行一个命令
> java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://10.1.199.168:1234/grid/register -port 1235

在默认情况下，注册了 11 个浏览器：5 个 Firefox 实例、5 个 Chrome 实例以及一个 Internet Explorer 实例。您可以在特定的端口上定位一个特定浏览器
>java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://10.1.199.168:1234/grid/register -port 1236 -browser browserName=chrome,version=14,platform=MAC

为了使用chrome和IE driver，我们需要这样设置:
>java -Dwebdriver.ie.driver="C:\Users\workspace\Demo\webDriver\IEDriverServer.exe" -Dwebdriver.chrome.driver="C:\Users\workspace\Demo\webDriver\chromedriver.exe" -jar selenium-server-standalone-2.53.1.jar -role node -hub http://10.1.199.168:1234/grid/register

![grid](https://github.com/slimina/fitnesse_demo/blob/master/images/25.png?raw=true)

要使用网格，则需要在测试用例中指定 hub 的 URL 和所要控制的浏览器
```java
 DesiredCapabilities capability = new DesiredCapabilities(); 
 capability.setBrowserName("chrome"); 
 capability.setVersion("14"); 
 capability.setPlatform(Platform.MAC); 
 WebDriver webdriver = new RemoteWebDriver(new URL("http://10.1.199.168:1234/grid/register"), capability);
```
Selenium Grid 2 还向后兼容 Selenium 1。您可以在 hub 中注册 Selenium 1 RC 节点
java -jar selenium-server-standalone-2.9.0.jar -role rc -hub http://10.1.199.168:1234/grid/register -port 5557
参考：https://github.com/SeleniumHQ/selenium/wiki/Grid2


#### 参考文档：

###### 1.[Selenium 2入门](https://www.ibm.com/developerworks/cn/web/wa-selenium2/)

###### 2.[Selenium学习资料](http://www.cnblogs.com/tobecrazy/category/605623.html)

###### 3.appium移动端的自动化框架

[appium移动端的自动化框架实例](http://www.cnblogs.com/tobecrazy/category/699177.html)

[Appium 中文 Appium API 文档](https://testerhome.com/topics/3144)

[appium简明教程](http://www.yangyanxing.com/article/1266.html)

##### 4.webdirver wiki
https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
https://github.com/SeleniumHQ/selenium/wiki/SafariDriver
https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver

下载驱动：

[googleapis](http://selenium-release.storage.googleapis.com/index.html)

[Selenium chrome和iE webdriver](http://download.csdn.net/detail/tianwei7518/9802292)
