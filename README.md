### fitnesse 接口测试实例
> 项目地址：https://github.com/unclebob/fitnesse

> 官网地址：http://fitnesse.org/

> 构建地址：https://cleancoder.ci.cloudbees.com/job/fitnesse/

> 仓库地址：http://repo1.maven.org/maven2/org/fitnesse/fitnesse/

> eclipse插件：http://fitnesse-eclipse.github.io/

> jenkins插件：https://wiki.jenkins-ci.org/display/JENKINS/Fitnesse+Plugin

**1. fitnesse-feature**

基于okhttp开发的fitnesse的feature，用于http api测试。已经打包统一放在FitNesseRoot/fitnesseJar/下，便于测试用例引用

**2.目录结构说明
****1.fitnesse-demo**

fitnesse 内置feature测试java代码

****2.fitnesse-feature**

开发一些测试组件，如httpclient，dbutil等

****3.selenium-demo**

selenium的一些测试java代码以及集成fitnesse实例代码

****4.FitNesseRoot**

fitnesse 工作目录，存放wiki编写的文件以及以来的jar

****5.images**

文档展示图片

**3. fitnesse服务启动**

window 运行startFitnesse.bat即可，linux运行startFitnesse脚本，运行fitnesse-standalone.jar，工作目录FitNesseRoot

**4. 实例样图**

![主页](images/page1.png)

![测试用例](images/page2.png)

![测试suite](images/page3.png)

![执行测试suite](images/page4.png)
