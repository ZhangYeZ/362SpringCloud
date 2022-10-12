package com.bdqn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;
@FeignClient("provider-user")
public interface UserClient {

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public Object pagedata(@RequestParam("a") String a);
}
