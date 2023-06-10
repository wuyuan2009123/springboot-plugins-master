# springboot-plugins

#### 介绍

基于SpringBoot框架的JAR包插件式开发，通过实现自定义的ClassLoader配合定时器功能实现指定目录下JAR包的热部署和热更新，从而实现线上系统"无感式"功能的快速升级！

也是看到博主在b站讲classloader 动态加载plugin jar 然后替换父加载器中的bean属性，这里的核心是子加载器可以看到父类的所有bean，然后也可以被父容器中反射赋值
博客地址可参考：https://blog.csdn.net/Appleyk/article/details/128166621