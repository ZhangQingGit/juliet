package com.ingooo.juliet.controller;

import java.util.List;

import com.ingooo.juliet.entity.Menu;
import com.ingooo.juliet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TypeController extends BaseController{
    
    @Autowired
    private TypeService service;
    
    @RequestMapping("showList")
    @ResponseBody
    public List<Menu> showList(){
        
        List<Menu> map = service.showList();
        
        return map;
    }
}
