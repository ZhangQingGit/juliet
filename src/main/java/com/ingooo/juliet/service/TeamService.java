package com.ingooo.juliet.service;

import com.ingooo.juliet.entity.Team;
import com.ingooo.juliet.entity.TeamV;

import java.util.List;

public interface TeamService {
    /**
     * 团队注册
     * @param tname
     * @param experience
     * @param uid
     * @param temail
     * @param tphone
     * @param tasktype
     * @param taskcount
     * @param fintask
     * @return
     */
    Integer teamReg(String tname, String experience, Integer uid
            , String temail, String tphone, String tasktype
            , Integer taskcount, Integer fintask);
    /**
     * 展示用
     * @return
     */
    List<TeamV> show();
    /**
     * 根据id查用户名
     * @param uid
     * @return
     */
    String getNameById(Integer uid);

    /**
     * 个人详情页
     * @param tid
     * @return
     */
    Team teamDetails(Integer tid);

    /**
     * 根据团队名称去查询tid
     * @param tname
     * @return
     */
    Integer findTidByTname(String tname);
}
