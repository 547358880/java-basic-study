#### Spring Ioc容器

##### 容器实现
在Ioc中容器代表就是org.springframework.beans包中的BeanFactory接口，BeanFactory接口提供了Ioc容器最基本功能;  
而org.springframework.context包下的ApplicationContext接口扩展了BeanFactory, 还提供了AOP继承， 国际化处理， 事件传播以及提供不同层次的context实现
- **XmlBeanFactory:** BeanFactory实现， 提供基本的Ioc容器功能， 可以从classpath或文件系统中获取资源
- **AnnotationConfigApplicationContext:** 从一个或多个以及Java配置类中加载Spring应用上下文
- **AnnotationConfigWebApplicationContext:** 从一个或多个基于Java的配置类中加载Sping Web应用上下文
- **ClassPathXmlApplicationContext:** 从类路径下的一个或多个XML配置文件中加载上下文
- **FileSystemXmlApplicationContext:** 从文件系统下的一个或多个XML配置文件中加载上下文
- **XmlWebApplicationContext:** 从Web应用下的一个或多个XML配置文件中加载上下文

JavaBean(本质就是一个POJO类)
具有一下限制:   
1. 该类必须要有公共的无参数构造器  
2. 属性为private的访问级别  
3. setter方法, 以"开头", 后跟首字母大写的属性名  
4. getter方法    

##### 资源注入	
- 构造器注入: 容器实例化Bean时注入那些依赖  

```
1) 常量值  
简写: <constructor-arg index="0" value="常量">
全写: <constructor-arg index="0"><value>常量</value></constructor-arg>
2) 引用
简写: <constructor-arg index="0" ref="引用" />
全些: <constructor-arg index="0"><ref bean="应用"/></constructor-arg>
```

- setter注入: 通过setter方法进行注入依赖

```
1) 常量值
简写: <property name="message" value="常量">
全些: <property name="message"><value>常量</value></property>
2) 引用
简写: <property name="message" ref="引用" />
全些: <property name="message"><ref bean="引用" /></property>
3) 数组
4) 列表
5) 集合
6) 字典
简写: <map>
	<entry key="键" value="值" />
	<entry key-ref=""键引用" value-ref="值引用" />
	</map>
全些:<map>
	<entry><key><value>键</value></key><value>值常量</value></entry>
	<entry><key><ref bean="键引用"></key><value><ref="值引用"/></value></entry>
    </map>
```

- 方法注入: 通过配置方法替换掉Bean

##### 注入集合，数组和字典
- 注入集合类型: 包括Collection类型, Set类型, List类型  
	1. List类型 需要使用List标签来配置注入  
	![](http://sishuok.com/forum/upload/2012/2/20/ea8eeb40b7c3710f2a898118481a2b67__8.JPG)  
	2. Set类型： 需要使用<set>标签配置注入  
- 注入数组类型: 需要使用<array>标签和配置注入
- 注入字典(Map类型): 字典类型是包含键值对数据的数据结构, 需要使用<map>标签来配置注入， 其属性"key-type"和"value-type"分别指“键”和“值”的数据类型

##### 延迟初始化Bean
延迟初始化也叫惰性初始化， 指不提前初始化Bean, 而是在真正使用时才创建初始化, 配置方法只需在bean标签上指定"lazy-init"为"true"即可       

##### 使用depends-on
depends-on是指指定Bean初始化及销毁的顺序, 使用depends-on属性指定的Bean要先初始化完毕，才初始化当前的Bean, 由于只有"singleton" Bean能被Spring管理销毁， 所以当指定的Bean都是"singleton"时，使用depends-on属性指定的Bean要在指定的Bean之后销毁

```
<bean id="helloApi" class="org.spring.chapter3.HelloApiDecorator" />
<bean id="decorator" class="org.spring.chapter3.HelloApiDecorator" depends-on="HelloApi">
	<property name="helloApi" ref="helloApi" />
</bean>
```

"decorator"指定了"depends-on"属性为"helloApi", 所以在"decorator"Bean初始化之前要先初始化"helloApi", 而销毁"helloApi"之前要先销毁"decorator"  


```
<bean id="resourceBean"  
    class="cn.javass.spring.chapter3.bean.ResourceBean"  
    init-method="init" destroy-method="destroy">  
    <property name="file" value="D:/test.txt"/>  
</bean>  
<bean id="dependentBean"  
    class="cn.javass.spring.chapter3.bean.DependentBean"  
    init-method="init" destroy-method="destroy" depends-on="resourceBean">  
    <property name="resourceBean" ref="resourceBean"/>  
</bean>


<property name="file" value="D:/test.txt" /> Spring容器自动把字符串转化为java.io.File
init-method="init": 指定初始化方法， 在构造器注入和setter完毕后执行
destroy-method="destroy": 指定销毁发给发， 只有"singleton"作用域能销毁, "prototype"作用域的一定不能  
```

##### 自动装配
Spring3.0 支持"no", "byName", "byType", "constructor"四种自动装配
- no: 不支持自动装配， 必须明确指出依赖
- byName: 通过设置Bean定义属性autowire="byName", 根据名字进行自动装配， 只能用于setter, 比如我们有方法"setHelloApi", 则"byName"方法容器将查找名字为helloApi的Bean并在注入， 如果找不到指定的Bean, 将什么也不注入
- byType: 通过设置Bean定义属性autowire="byType", 意思是根据类型注入， 用于setter注入, 比如如果指定自动装配方式为"byType", 而"setHelloApi"方法需要注入HelloApi类型数据， 则容器将查找HelloApi类型数据， 如果找不到将什么也不注入， 如果找到多个Bean将优先注入bean标签"primary"属性为true的bean, 否则抛出异常
- constructor: 通过设置Bean定义属性autowire="constructor", 功能和"byType"功能一样， 根据类型注入构造器参数， 只是用于构造器注入方式  
**不是所有类型都能自动装配:**  
	1. 不能自动装配的数据类型: Object, 基本数据类型(Date, CharSequence, Number, URI, URL, Class, int)等;  
	2. 通过"beans"标签default-autowire-candidates属性指定的匹配模式， 不匹配的将不能作为自动装配的候选者， 例如指定"*Service, *Dao", 将只把匹配这些模式的Bean作为候选者， 而不匹配的不会作为  
	3. 通过"beans"标签的autowire_condidate属性可被设为false, 从而该Bean将不会作为依赖注入的候选者  
**数组， 集合， 字典类型的根据类型自动装配和普通类型的自动装配是有区别的:**  
    1.数组类型，集合(Set, Collection, List)接口类型： 将根据泛型获取匹配的所有候选者并注入到数组或集合中, 如"List&lt;HelloApi&gt; list"将选择所有的HelloApi类型Bean并注入到list中，而对于集合的集体类型将只选择一个候选者, 如"ArrayList&lt;HelloApi&gt; list"将选择一个类型为ArrayList的Bean注入  
    2.字典(Map)接口类型: 同样根据泛型信息注入, 键必须是String类型的Bean名字    
         如 "Map&lt;String, HelloApi&gt; map" 将选择所有的HelloApi类型Bean并注入到map中  
          如  "HashMap&lt;String, HelloApi&gt; map"将只选择一个HelloApi类型的bean进行注入
         
     
##### Bean作用域
- **singleton:** "singleon"作用域的Bean只会在每个容器中存在一个实例,而且完整生命周期完全由Spring容器管理， 对于所有获取bean的操作spring容器都返回同一个bean

- **prototype:** 原型，每次向Spring容器请求获取Bean都发挥一个全新的Bean

- **web应用中的作用域:**  
	1. **request作用域:** 表示每个请求需要容器创建一个全新Bean, 比如提交表单的数据必须是对每次请求新建一个Bean来保存这些表单数据， 请求结束释放这些数据  
	2. **session作用域:** 表示每个会话需要容器创建一个全新bean  
	3. **globalSession:** 类似于session作用域, 只是其用于portlet环境的web应用， 如果是非porlet环境将视为session作用域
	
- **自定义作用域(见org.spring.chapter3.ThreadScope)**

#### 主要注解

##### 注解实现Bean依赖注入
spring3的基于注解实现Bean依赖注入支持如下三种注解
- **spring自带依赖注解:**  

```
	1. @Required:依赖检查： 基于@Required的依赖检查表示注解的setter方法必须， 即必须通过XML配置setter注入, 如果没有配置则会在容器启动时抛出空指针异常, @Required只能放在setter方法上  
	2. @Autowired: 自动装配 默认是根据类型注入， 可以用于构造器， 字段， 方法注入, 使用方式  
	@Autowired(required=true)  
	构造器， 字段， 方法  	
	3. @Value: 注入SpEL表达式, 使用方式  
	@Value(value = "SpEL表达式")
	字段， 方法， 参数
```
- **JSR-250注解:** Java平台的公共注解， JavaEE 5规范之一
- **JSR-330注解:** JavaEE 6 规范之一
- **JPA注解:** 用于注入持久化上下文和尸体管理器

要使用这些注解需要在Spring容器中开启注解驱动支持&lt;context:annotation-config/&gt;