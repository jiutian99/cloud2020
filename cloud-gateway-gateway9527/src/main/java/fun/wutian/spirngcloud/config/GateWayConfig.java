package fun.wutian.spirngcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuyuetian
 * @create 2020-07-20 11:04
 */
@Configuration
public class GateWayConfig {

    /**
     * 配置了一个名为path_route_tian的路由规则
     * 当访问 http://localhost:9527/guonei 将会自动转发到 http://news.baidu.com/guonei
     * @param rb
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder rb){
        RouteLocatorBuilder.Builder routes = rb.routes();
        routes.route("path_route_guonei",
                r->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"))
                .build();
        routes.route("path_route_guoji",
                r->r.path("/guoji")
                        .uri("http://news.baidu.com/guoji"))
                .build();
        return routes.build();
    }


}
