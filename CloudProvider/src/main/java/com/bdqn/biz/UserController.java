package com.bdqn.biz;

import com.bdqn.dao.ItripHotelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    ItripHotelMapper dao;

    @RequestMapping("/list")
    public Object getlist() {
        return dao.list();
    }

    @RequestMapping("/sayHello")
    public String sayhello() {
        return "I`m provider 1 ,Hello consumer!";
    }

    @RequestMapping("/sayHi")
    public String sayHi() {
        return "I`m provider 1 ,Hello consumer!";
    }

    @RequestMapping("/sayHaha")
    public String sayHaha() {
        return "I`m provider 1 ,Hello consumer!";
    }
}