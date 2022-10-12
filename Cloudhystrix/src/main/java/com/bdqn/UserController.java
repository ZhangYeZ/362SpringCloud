package com.bdqn;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Bean
    @LoadBalanced
    //负载均衡注解
    public RestTemplate getResttemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate resttemplate;

    @RequestMapping("/getdata")
    @HystrixCommand(fallbackMethod = "helloFallback")//失败时
    public String hello() {
        //指出服务地址   http://{服务提供者应用名名称}/{具体的controller}
        String url = "http://provider-user/user/list";
        System.out.println(resttemplate.getForObject(url, String.class));
        //返回值类型和我们的业务返回值一致
        return resttemplate.getForObject(url, String.class);
    }

    public String helloFallback() {
        return "您请求的数据没拿到，我是hystrix返回的默认数据--helloxxxx";
    }
}
