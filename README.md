# springcloud-demo
<pre><code>
本例使用的框架版本
springboot 2.1.2.RELEASE版本 
springcloud Greenwich.RELEASE版本
</code></pre>
 ###1、注册与发现
 * springcloud常用注册中心是Eureka,也可以使用其他注册中心，如zookeeper
 * @EnableEurekaClient和@EnableDiscoveryClient注解都可以用于注册发现，将微服务注册到注册中心
 * @EnableEurekaClient只适用于Eureka注册中心，@EnableDiscoveryClient可以适用于其他注册中心
 
 ###2、springcloud中调用微服务的两种方式介绍(ribbon / feign )
 * ribbon需要搭配RestTemplate使用
 * ribbon和feign都是springcloud调用微服务的方式
 * feign默认集成ribbon，具有负载均衡的能力
 * ribbon是一个负载均衡客户端
 * feign自带断路器hystrix，可直接在配置文件开启并使用（默认是关闭状态）；而ribbon需要整合断路器
 * feign采用基于注解方式调用微服务，只要创建一个接口并采用注解就可以调用微服务
 
 ###3、Hystrix断路器
 
 ###4、zuul网关路由
 * zuul功能上类似于nginx
 * zuul主要功能是路由转发和过滤器
 * zuul默认和ribbon结合实现负载均衡的功能
 #####4.1、zuul路由转发配置
 <pre><code>
 #zuul网关路由配置
 zuul:
   routes:
     #配置路由对应的微服务
     api-user:
       #/user/开头的请求，转发到user-service服务
       path: /user/**
       serviceId: user-service
     api-order:
       #/order/开头的请求，转发到order-service服务
       path: /order/**
       serviceId: order-service
 </code></pre>
 #####4.2、zuul过滤器介绍
  <pre><code>
       zuul有四种生命周期过滤器类型：
       * pre：请求被路由之前调用
       * routing：在路由请求时被调用
       * post：在routing和error过滤器之后被调用
       * error：处理请求时发生错误时被调用

       zuul过滤器的执行过程：
       * 当外部HTTP请求到达API网关服务的时候，首先它会进入第一个阶段pre，在这里它会被pre类型的过滤器进行处理，
       * 该类型的过滤器主要目的是在进行请求路由之前做一些前置加工，比如请求的校验等。
       * 在完成了pre类型的过滤器处理之后，请求进入第二个阶段routing，也就是之前说的路由请求转发阶段，
       * 请求将会被routing类型过滤器处理，这里的具体处理内容就是将外部请求转发到具体服务实例上去的过程，
       * 当服务实例将请求结果都返回之后，routing阶段完成，请求进入第三个阶段post，此时请求将会被post类型的过滤器进行处理，
       * 这些过滤器在处理的时候不仅可以获取到请求信息，还能获取到服务实例的返回信息，
       * 所以在post类型的过滤器中，我们可以对处理结果进行一些加工或转换等内容。
       * 另外，还有一个特殊的阶段error，该阶段只有在上述三个阶段中发生异常的时候才会触发，
       * 但是它的最后流向还是post类型的过滤器，因为它需要通过post过滤器将最终结果返回给请求客户端
  </code></pre>
  
  ###5、配置中心
  分布式配置中心分为两个角色：配置服务器 、配置客户端（客户端不独立存在，通常是集成到各个普通的微服务中）
