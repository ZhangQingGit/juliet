package com.ingooo.juliet.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.ingooo.juliet.entity.*;
import com.ingooo.juliet.service.CompanyService;
import com.ingooo.juliet.service.TaskService;
import com.ingooo.juliet.service.TeamService;
import com.ingooo.juliet.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("task")
public class TaskController extends BaseController {
    @Autowired
    TaskService taskService;

    @Autowired
    TeamService teamService;

    @Autowired
    CompanyService companyService;


    @RequestMapping("show")
    @ResponseBody
    public ResponseResult<List<TaskV>> showByLimit(){

        List<TaskV> t = taskService.showByLimit();

        return new ResponseResult<>(SUCCESS,t);
    }
    
    @RequestMapping("datails")
    @ResponseBody
    public ResponseResult<Task> showDatails(Integer rid){
        
        Task task = taskService.taskDatails(rid);
        
        return new ResponseResult<>(SUCCESS, task);
    }
    
    @RequestMapping("bidding")
    @ResponseBody
    public ResponseResult<String> taskBidding(){
        return null;
    }

    @RequestMapping("taskList")
    @ResponseBody
    public PageResult taskList(Integer pageNum, Integer pageSize){
        PageResult result = taskService.taskList(pageNum, pageSize);
        Integer num = new Long(result.getTotalSize()).intValue();
        Integer pages = (num+5-1)/5;
        result.setPages(pages);
        return result;
    }

    @RequestMapping("findTaskType")
    @ResponseBody
    public ResponseResult<String[]> findTaskType(){
        String[] arr = taskService.findTaskType();
        return new ResponseResult<>(200, arr);
    }

    @RequestMapping("findCname")
    @ResponseBody
    public ResponseResult<String[]> findCname(){
        String[] arr = taskService.findCname();
        return new ResponseResult<>(200,arr);
    }

    @RequestMapping("taskPush_One")
    @ResponseBody
    public ResponseResult<String> taskPush(TaskBasic taskBasic, HttpSession session){
        Integer i = taskService.addTask(taskBasic, session);
        if(i==1){
            return new ResponseResult<>(200, "OK");
        }
        return new ResponseResult<>(201, "失败");
    }

    @RequestMapping("taskPush_Two")
    @ResponseBody
    public ResponseResult<String> updatePsynopsis(String psynopsis, HttpSession session){
        Integer i = taskService.updatepSynopsis(psynopsis, session);
        if(i==1){
            return new ResponseResult<>(200, "OK");
        }
        return new ResponseResult<>(201, "失败");
    }

    @RequestMapping("taskPush_Three")
    @ResponseBody
    public ResponseResult<String> updateCore(String core, HttpSession session){
        Integer i = taskService.updateCore(core, session);
        if(i==1){
            return new ResponseResult<>(200, "OK");
        }
        return new ResponseResult<>(201, "失败");
    }

    @RequestMapping("search")
    @ResponseBody
    public PageResult search(String msg, String tasktype, Integer pageNum, Integer pageSize, HttpSession session){
        PageResult result = taskService.search(msg, tasktype, pageNum, pageSize, session);
        Integer num = new Long(result.getTotalSize()).intValue();
        Integer pages = (num+5-1)/5;
        result.setPages(pages);
        return result;
    }

    /**
     * 查询当前用户发布的任务
     * @return
     */
    @RequestMapping("/findReleaseTaskByCname")
    @ResponseBody
    public ResponseResult<List<Task>> findReleaseTaskByCname(HttpSession session,Integer page,Integer limit){
        Object uid = session.getAttribute("uid");
        Integer count;
        if(uid!=null) {
            String companyname = companyService.getNameById((int)uid);
            if(companyname!=null) {
                //发布任务的总条数
                count=taskService.findReleaseTaskByCname(companyname).size();

                Page page1= new Page();
                page1.setPage((page-1)*limit);
                page1.setLimit(limit);
                page1.setCortname(companyname);

                //分页方法
                List<Task> taskList = taskService.findReleaseTaskLimitByCname(page1);
                return new ResponseResult<>(200,count,taskList);
            }else {
                return  null;
            }
        }
        return  null;
    }
}

