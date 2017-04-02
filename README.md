#微信模块
微信菜单、素材管理、微信用户、群发消息、关注回复、关键字回复、多个公众号管理——可以同时管理多个公众号！

#maven依赖方式使用
```
<dependency>
  <groupId>net.mingsoft</groupId>
  <artifactId>ms-mweixin</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <type>war</type>
</dependency>

```
#源码方式使用
直接将在MStore下载的源代码解压覆盖到项目中，编译并运行

#数据库文件导入
将doc/ms-mweixin.sql数据到db-mcms-open(mcms系统对应到数据库) 注意：如果已经安装过，可以跳过该过程；

#初始化
先登录后台系统，在地址输入安装链接；http://cms后台管理地址/weixin/initWeixin.do 
例如:http://localhost:8080/mcms/ms/login.do ，安装对应地址为：http://localhost:8080/mcms/ms/weixin/initWeixin.do 
提示安装成功后返回主界面就可以看到微信菜单