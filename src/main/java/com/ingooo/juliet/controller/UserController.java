package com.ingooo.juliet.controller;


import javax.servlet.http.HttpSession;

import com.ingooo.juliet.service.CompanyService;
import com.ingooo.juliet.service.TeamService;
import com.ingooo.juliet.service.UserService;
import com.ingooo.juliet.util.Email.EmailUtil;
import com.ingooo.juliet.util.Randoms;
import com.ingooo.juliet.util.ResponseResult;
import com.ingooo.juliet.util.msg.MsgUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CompanyService companyService;
   
    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session) {
            if(username!=null) {
                // 获取subject
                Subject subject = SecurityUtils.getSubject();
                // 封装用户数据
                UsernamePasswordToken token = new UsernamePasswordToken(username,password);
                // 执行登录
                try {
                    subject.login(token);
                    //根据用户名查id
                    Integer uid = userService.findId(username);
                    session.setAttribute("uid", uid);
                    return "index";
                } catch (UnknownAccountException e) {
                    //用户名不存在
                    model.addAttribute("msg", "用户名不存在");
                }catch (IncorrectCredentialsException e) {
                    //密码错误
                    model.addAttribute("msg", "密码错误！");
                }
            }
            return "user/login";
        }
    @RequestMapping("loginforcode")
    public String loginForCode(String username, String password, Model model, HttpSession session) {
        Object ses = session.getAttribute(username);
        if(username!=null) {
            if(ses!=null) {
               // 获取subject
               Subject subject = SecurityUtils.getSubject();
               // 封装用户数据
               UsernamePasswordToken token = new UsernamePasswordToken(username,password);
               // 执行登录
               try {
                   subject.login(token);
                   //remove session中的验证码
                   session.removeAttribute(username);
                   //根据用户名查id
                   Integer uid = userService.findId(username);
                   session.setAttribute("uid", uid);
                   return "index";
               } catch (UnknownAccountException e) {
                   //用户名不存在
                   model.addAttribute("msg", "用户名不存在");
               }catch (IncorrectCredentialsException e) {
                   //密码错误
                   model.addAttribute("msg", "密码错误！");
               }
           }
           model.addAttribute("msg", "未验证码或该手机注册！");
       }
        return "login";
   }
    
    @RequestMapping("reg")
    public String reg(String username, String password, Integer usertype, String code, HttpSession session, Model model){
        if(username!=null & username !="" ) {
            Object phone = session.getAttribute(username);
            if(phone!=null) {
                String str = (String)phone;
                if(str.equals(code)) {
                    Integer i = userService.register(username, password, usertype);
                    if(i==-1) {
                        model.addAttribute("msg", "用户名重复");
                        return null;
                    }
                    session.removeAttribute(username);
                    return "login";
                }else {
                    model.addAttribute("msg", "验证码错误！");
                    return null;
                }
            }
            model.addAttribute("msg", "请获取验证码！");
            return "register";
        }
        return "register";
    }
    
    @RequestMapping("email")
    @ResponseBody
    public ResponseResult<String> yanzheng(HttpSession session, String email){
        String key = Randoms.randomInt();
        EmailUtil.send(email, "验证码", "验证码为："+key, null);
        session.setAttribute(email, key);
        return new ResponseResult<>(201, "验证码已发送！");
    }
    
    @RequestMapping("phone")
    @ResponseBody
    public ResponseResult<String> phone(HttpSession session, String phone){
        String key = Randoms.randomInt();
        MsgUtil.msgUtil(phone, key);
        session.setAttribute(phone, key);
        return new ResponseResult<>(201, "验证码已发送！");
    }

    /**
     * 根据uid查询用户昵称
     * @param session
     * @return
     */
    @RequestMapping("getNickName")
    @ResponseBody
    public ResponseResult<String> getNickName(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid != null){
            String nickName = userService.getNickNameByUid(uid);
            return new ResponseResult<>(200,nickName);
        }

        return new ResponseResult<>(200,"UnName");
    }

    /**
     * 根据uid查询个人/公司信息
     * @param session
     * @return
     */
    @RequestMapping("findInformationByUid")
    @ResponseBody
    public Map<String,Object> findInformationByUid(HttpSession session){
        Object obj = session.getAttribute("uid");
        if(obj != null){
            Integer uid = (Integer) obj;
            Map<String,Object> map=userService.findInformationByUid(uid);
            return map;
        }else {
            return null;
        }
    }

    /**
     * 根据uid查询账户密码
     * @param session
     * @return
     */
    @RequestMapping("/findPasswordByUid")
    @ResponseBody
    public ResponseResult<String> findPasswordByUid(HttpSession session){
        Object obj = session.getAttribute("uid");
        if(obj != null){
            Integer uid = (Integer) obj;
            String password = userService.findPasswordByUid(uid);
            return new ResponseResult<>(200,password);
        }else {
            return null;
        }
    }

    @RequestMapping("/updatePasswordByUid")
    @ResponseBody
    public ResponseResult<String> updatePasswordByUid(HttpSession session,String confirmpassword){
        Object obj = session.getAttribute("uid");
        if(obj != null){
            Integer uid = (Integer) obj;

            Integer a = userService.updatePasswordByUid(uid,confirmpassword);
            if(a > 0){
                return new ResponseResult<>(200,"修改成功");
            }
        }else {
            return null;
        }
        return null;
    }
}
