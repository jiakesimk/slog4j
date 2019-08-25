# mlog4j
mlog4j 全称为Java智子日志记录框架,英文为 sophon logger for java

# mlog4j的优点
1.不用在每个类中都加上下面这段代码

```java
private static final Logger log = LoggerFactory.getLogger(Main.class);
```

可以直接在类中使用,并且不用声明任何对象
```java
mlog.info("xxx");
mlog.debug("xxx");
mlog.error("xxx");
mlog.warn("xxx");
```

2. 可将异常转换为打印输出在控制台,让异常不影响程序后面的运行

# 缺点
目前mlog4j内容还很少,比如不可配置等必要操作,后续会进行更新

# 为什么我会写 mlog4j?
我这个人其实是非常偷懒的,先开始使用日志的时候需要在每个类中都加上private...LoggerFactory.getLogger(xxx.class);

到后来我发现这样写起来太繁琐了,而且很没必要,然后我开始使用lombok的@Slf4j注解,用久了也觉得不舒服,这个只是比声明对象少了几个字罢了

再到后来我就在想怎么做一款不用声明就能输出的日志框架,于是就诞生出了mlog4j