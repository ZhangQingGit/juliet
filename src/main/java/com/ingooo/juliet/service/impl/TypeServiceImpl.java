package com.ingooo.juliet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ingooo.juliet.entity.Menu;
import com.ingooo.juliet.mapper.TypeMapper;
import com.ingooo.juliet.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper mapper;
    @Override
    public List<Menu> showList() {
        List<Menu> list = new ArrayList<Menu>();
        String[] str = mapper.findOne();
        for (int i = 0; i<str.length; i++) {
            Menu menu = new Menu();
            String s=mapper.findTwo(str[i]);
            menu.setFather(str[i]);
            menu.setSon(s);
            list.add(menu);
        }
        return list;
    }

}
