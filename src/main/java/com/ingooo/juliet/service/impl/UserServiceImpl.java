package com.ingooo.juliet.service.impl;

import com.ingooo.juliet.entity.Company;
import com.ingooo.juliet.entity.Team;
import com.ingooo.juliet.entity.User;
import com.ingooo.juliet.mapper.CompanyMapper;
import com.ingooo.juliet.mapper.TeamMapper;
import com.ingooo.juliet.mapper.UserMapper;
import com.ingooo.juliet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private CompanyMapper companyMapper;
    
    @Override
    public User login(String username) {
        if(username!=null & !"".equals(username)) {
            if(-1==username.indexOf("@")) {
                User user = userMapper.findByPhone(username);
                return user;
            }else {
                User user = userMapper.findByEmail(username);
                return user;
            }
        }
        return null;
    }

    @Override
    public Integer register(String username, String password, Integer usertype) {
        //不可重复
        if(username!=null & !"".equals(username)) {
            if(-1==username.indexOf("@")) {
                User user = userMapper.findByPhone(username);
                if(user!=null) {
                    return -1;
                }
            }else {
                User user = userMapper.findByEmail(username);
                if(user!=null) {
                    return -1;
                }
            }
        }
        //注册
        if(username!=null & !"".equals(username)) {
            if(-1==username.indexOf("@")) {
                Integer i = userMapper.PhoneReg(username, password, usertype);
                return i;
            }else {
                Integer i = userMapper.EmailReg(username, password, usertype);
                return i;
            }
        }
        return null;
    }

    @Override
    public Integer findId(String username) {
        if(username!=null & !"".equals(username)) {
            if(-1==username.indexOf("@")) {
                User user = userMapper.findByPhone(username);
                return user.getUid();
            }else {
                User user = userMapper.findByEmail(username);
                return user.getUid();
            }
        }
        return null;
    }

    @Override
    public boolean findToTeamExit(Integer uid) {
        Team team = teamMapper.findByUid(uid);
        if(team!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean findToCompanyExit(Integer uid) {
        Company com = companyMapper.findByUid(uid);
        if(com!=null){
            return true;
        }
        return false;
    }

        /*Company com = companyMapper.findByUid(uid);
        Team team = teamMapper.findByUid(uid);*/

    @Override
    public Map<String,Object> findInformationByUid(Integer uid) {
        Company company = companyMapper.findByUid(uid);
        Team team = teamMapper.findByUid(uid);
        Map<String,Object> map=new HashMap<>();
        if(company != null) {
            map.put("usertype",1);
            map.put("cort",company);
            return map;
        }
        if(team != null) {
            map.put("usertype",2);
            map.put("cort",team);
            return map;
        }
        return null;
    }

    @Override
    public String findPasswordByUid(Integer uid) {
        return userMapper.findPasswordByUid(uid);
    }

    @Override
    public Integer updatePasswordByUid(Integer uid,String password) {
        return userMapper.updatePasswordByUid(uid,password);
    }

    @Override
    public String getNickNameByUid(Integer uid) {
        return userMapper.getNickNameByUid(uid);
    }

}
