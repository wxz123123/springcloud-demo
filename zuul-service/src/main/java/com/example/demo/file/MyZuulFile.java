package com.example.demo.file;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description Zuul过滤器，可以做安全验证等功能
 * @Author wxz
 * @Date 2019/2/13 11:50
 */
@Component
@Slf4j
public class MyZuulFile extends ZuulFilter {
    /**
     * 返回一个字符串代表过滤器类型，zuul有四种生命周期过滤器类型：
     * pre：请求被路由之前调用
     * routing：在路由请求时被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * zuul过滤器的执行过程：
     * 当外部HTTP请求到达API网关服务的时候，首先它会进入第一个阶段pre，在这里它会被pre类型的过滤器进行处理，
     * 该类型的过滤器主要目的是在进行请求路由之前做一些前置加工，比如请求的校验等。
     * 在完成了pre类型的过滤器处理之后，请求进入第二个阶段routing，也就是之前说的路由请求转发阶段，
     * 请求将会被routing类型过滤器处理，这里的具体处理内容就是将外部请求转发到具体服务实例上去的过程，
     * 当服务实例将请求结果都返回之后，routing阶段完成，请求进入第三个阶段post，此时请求将会被post类型的过滤器进行处理，
     * 这些过滤器在处理的时候不仅可以获取到请求信息，还能获取到服务实例的返回信息，
     * 所以在post类型的过滤器中，我们可以对处理结果进行一些加工或转换等内容。
     * 另外，还有一个特殊的阶段error，该阶段只有在上述三个阶段中发生异常的时候才会触发，
     * 但是它的最后流向还是post类型的过滤器，因为它需要通过post过滤器将最终结果返回给请求客户端
     * @return
     */
    @Override
    public String filterType() {
        log.info("-------------------------MyZuulFile filterType-------------------------");
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序，同filterType类型中，order值越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        log.info("-------------------------MyZuulFile filterOrder-------------------------");
        return 0;
    }

    /**
     * 判断该过滤器是否要执行，ture表示要执行过滤，我们可以通过该方法指定过滤器的有效范围
     * ,如果返回ture时run方法会执行，返回false时不会执行run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        log.info("-------------------------MyZuulFile shouldFilter-------------------------");
        return true;
    }

    /**
     * 过滤器具体逻辑,如判断是否已登录，校验token是否存在等
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info("-------------------------MyZuulFile run-------------------------");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getParameter("token");
        log.info("执行Filter .....token=" + token 	);

        //如果用户名和密码都正确，则继续执行下一个filter
        if("true".equals(token) ){
            ctx.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者
            ctx.setResponseStatusCode(200);
            ctx.set("isOK",true);//可以把一些值放到ctx中，便于后面的filter获取使用
        }else{
            ctx.setSendZuulResponse(false);//不需要进行路由，也就是不会调用api服务提供者
            ctx.setResponseStatusCode(401);
            ctx.set("isOK",false);//可以把一些值放到ctx中，便于后面的filter获取使用
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"token 已经过期!\"}");// 返回错误内容
        }

        return null;
    }
}
