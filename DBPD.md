# 朝花夕拾
> Dawn Blossms Plucked at Dusk  
> 乱七八杂的记录一些学习的知识点与问题  
> 时光如金，努力再努力  

### Session和Cookie区别
```text
session与cookie与JWT
	http是无状态协议 Session和Cookie弥补http的无状态特性
	Session
		服务端开辟内存存储Session，数据结构是：ConcurrentHashMap 存储在同一会话期间的一些操作
		如何判断是同一会话？
			1.服务器第一次接收到请求，创建Session对象，生成SessionId 
			2.在发送响应时，设置响应头的"Set-Cookie：JSESSIONID=xxxxx" Set-Cookie是相应的一个标头
			3.客户端收到响应设置一个Cookie 这个Cookie过期时间是浏览器会话结束。
			4.之后客户端发送请求时都会带上该Cookie，之后服务端就能找到Session进行其他操作
		缺点：
			负载均衡后，A服务器存有Session而B服务器没有，导致Session失效
		Session既能存储用户信息也能做用户验证，不用每次都登陆
	Cookies
		Web Cookies和浏览器Cookie，通常用来判断是否来自同一个浏览器，例如用户保持登陆状态
		作用：
			会话管理
				登陆（保持登陆信息）、购物车、游戏得分或者其他服务器记住需要记录的信息
			个性化
				用户偏好、主题和其他设置
			追踪
				记录和分析用户行为

		Cookie有两种类型：
			Session Cookies
				Cookies不包含日期，为会话Cookie；存在内存，浏览器关闭，消失
			Persistent Cookies
				包含日期是持久cookie，日期到了从磁盘删除
	JWT
		Json Web Token Json令牌
		作用：
			认证
				用户登陆后，每个请求都会包含JWT，用户可以访问该令牌所允许的路由、服务和资源。例如单点登录
			信息交换
		JWT和Session Cookies的不同
			JWT是无状态的，存储在客户端，身份验证可在本地进行，不用请求服务器消耗大量资源
			JWT支持跨域认证
				Session Cookies只能在单个节点的域或子域中有效。
```

### @Resource和@Autowride
```text
@Resource和@Autowride
	都是用来依赖注入的注解
	@Resource
		默认按名称注入 例如一个接口有多个实现类时，依赖注入就可以使用
	@Autowride
		类型优先，其次是@Qualifier约束，然后是按照名称，最后判断是否required
		标记在构造函数
			@Autowired如果目标Bean只定义一个构造函数，则不再需要在该构造函数上添加@Autowired注解。如果目标Bean有几个构造函数可用，并且没有主/默认构造函数，则必须至少有一个构造函数被@Autowired标记，以指示Spring IoC容器使用哪个构造函数。
			现在只有一个构造函数，进行依赖注入可以省略@Autowride
			@Component
			public class Demo01Producer {
			//    @Autowired
			//    private KafkaTemplate<Object,Object> kafkaTemplate;

			    private final KafkaTemplate<Object,Object> kafkaTemplate;

			    public Demo01Producer(KafkaTemplate<Object, Object> kafkaTemplate) {
			        this.kafkaTemplate = kafkaTemplate;
			    }

			    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
			        //新建一条消息
			        Demo01Message message = new Demo01Message();
			        message.setId(id);
			        ListenableFuture<SendResult<Object, Object>> send = kafkaTemplate.send(Demo01Message.TOPIC, message);
			        //kafkaTemplate
			        return send.get();
			    }
			}
			如果再新增几个构造函数而不使用@Autowride指定使用那个构造器idea会报错
		标注到成员变量
			是我们经常使用的依赖注入的方式
		标注到方法
			例如某个方法来注入该对象
		@Resource没有提供可选择装配的特性，如果无法装配，会直接抛出异常。而@Autowired有required属性，默认是required=true 表示注入时bean必须存在，false有直接注入，没有跳过，不会报错。
	@Resource和@Autowired的优先级顺序不同（参见上图），另外@Resource属于 Jakarta EE规范而@Autowired属于Spring范畴，@Resource无法使用在构造参数中，@Autowired支持required属性。从面向对象来说，@Resource更加适用于多态性的细粒度注入，而@Autowired更多专注于单例。
```