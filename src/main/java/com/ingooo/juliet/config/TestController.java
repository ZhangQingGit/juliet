package com.ingooo.juliet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by 丶TheEnd on 2019/10/9 0009.
 * @author Administrator
 */
@Controller
public class TestController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/test")
    public void test(){
        String id = request.getSession().getId();
        System.out.println(id);
    }

}
