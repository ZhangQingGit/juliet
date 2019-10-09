package com.ingooo.juliet.service.impl;

import java.util.List;

import com.ingooo.juliet.entity.Team;
import com.ingooo.juliet.entity.TeamV;
import com.ingooo.juliet.mapper.TeamMapper;
import com.ingooo.juliet.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper mapper;
    @Override
    public Integer teamReg(String tname, String experience, Integer uid, String temail, String tphone, String tasktype,
            Integer taskcount, Integer fintask) {
        Integer i = mapper.findTidByTname(tname);
        if(i==null){
            return mapper.teamReg(tname, experience, uid, temail, tphone, tasktype, taskcount, fintask);
        }
        return -1;
    }
    
    @Override
    public List<TeamV> show() {
        List<TeamV> list = mapper.findByLimit();
        for (TeamV teamV : list) {
            if(teamV.getTaskcount()!=0) {
                teamV.setGoodtask((teamV.getFintask()*100)/teamV.getTaskcount());
            }else {
                teamV.setGoodtask(0);
            }
        }
        return list;
    }
    
    @Override
    public String getNameById(Integer uid) {
        Team team = mapper.findByUid(uid);
        if(team!=null) {
            return team.getTname();
        }
        return null;
    }

    @Override
    public Team teamDetails(Integer tid) {
        return mapper.findById(tid);
    }

    @Override
    public Integer findTidByTname(String tname) {
        return mapper.findTidByTname(tname);
    }

}
