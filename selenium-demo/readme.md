**基于selenium开发web测试**
---

Selenium 是用于测试 Web 应用程序用户界面 (UI) 的常用框架。它是一款用于运行端到端功能测试的超强工具。您可以使用多个编程语言编写测试，并且 Selenium 能够在一个或多个浏览器中执行这些测试。

**1.环境搭建**

selenium对Firefox浏览器支持的比较好，所以在开发测试case一般使用Firefox浏览器。需要并在添加组件页面的搜索输入框内输入firebug和firepath（定位元素），进行分别搜索和安装，安装完成后重启FireFox浏览器，使插件生效。

selenium和Firefox常常会出现版本不匹配的问题，常见的现象是Firefox浏览器启动时就报错，所以就需要使用老版本的FireFox，如26版本

Firefox下载目录：https://ftp.mozilla.org/pub/firefox/releases/

selenium下载目录：http://selenium-release.storage.googleapis.com/index.html

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

2.chrome的xpath插件，选中元素，copy -->copy xpth即可获取元素路径
![image](https://github.com/slimina/fitnesse_demo/blob/master/images/13.png?raw=true)

参考文档：

1.[Selenium 2入门](https://www.ibm.com/developerworks/cn/web/wa-selenium2/)
