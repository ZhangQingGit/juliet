package com.ingooo.juliet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ingooo.juliet.entity.Team;
import com.ingooo.juliet.entity.TeamV;
import com.ingooo.juliet.service.TeamService;
import com.ingooo.juliet.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TeamController extends BaseController{
    @Autowired
    private TeamService teamService;
    
    @RequestMapping("teamReg")
    @ResponseBody
    public ResponseResult<String> teamReg(String tname, String experience, HttpSession session
            , String temail, String tphone, String tasktype) {
        Object obj = session.getAttribute("uid");
        if(obj!=null) {
            Integer uid = (int)obj;
            Integer i = teamService.teamReg(tname, experience, uid, temail, tphone, tasktype, 0, 0);
            if(i==-1){
                return new ResponseResult<>(201,"团队名已存在！");
            }
            if(i==1) {
                return new ResponseResult<>(200);
            }
        }
         return new ResponseResult<>(201,"未知错误！");
    }
    
    @RequestMapping("showTeam")
    @ResponseBody
    public ResponseResult<List<TeamV>> showList(){
        List<TeamV> list = teamService.show();
        return new ResponseResult<>(200,list);
    }

    @RequestMapping("team_details")
    @ResponseBody
    public ResponseResult<Team> details(Integer tid){
        Team team = teamService.teamDetails(tid);
        return new ResponseResult<>(200, team);
    }
    
}
