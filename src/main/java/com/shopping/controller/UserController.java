package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.User;
import com.shopping.service.UserService;
import org.hibernate.Session;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class UserController {

    @Resource
    UserService userService;

    /////////////系统用户列表查询/////////////////////////////////////////////////////////
    @RequestMapping(value = "/serchUser")
    public List<User> serchUser(){
        return userService.getAllUser();
    }


    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/userManager")
    public String userManager(Model model) {
        List<User> list=null;
        list = userService.getAllUser();
        model.addAttribute("userList", list);
        return "userManager";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/control")
    public String control() {
        return "control";
    }

    /*@RequestMapping(value="login")
    @ResponseBody
    public String login(String userName,String password){
        User user=userService.getUser(userName);
        if(userName=="222"){
            if(user.getPassword()==password)
                return "userManager";
            else return "redirect:/doLogin";
        }
        else return "redirect:/doLogin";
    }*/

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String userName, String password, HttpSession httpSession) {
        System.out.println("我接收到了登录请求" + userName + " " + password);
        String result = "fail";
        User user = userService.getUser(userName);
        if (user == null)
            result = "unexist";
        else {
            if (user.getPassword().equals(password)) {
                result = "success";
                httpSession.setAttribute("currentUser", user);
            } else
                result = "wrong";
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(String userName, String password) {

        String result = "fail";
        User user = userService.getUser(userName);
        if (user != null) {
            result = "nameExist";
        } else {
            User user1 = new User();
            user1.setName(userName);
            System.out.println(userName);
            user1.setPassword(password);
            System.out.println(password);
            user1.setRole(0);
            userService.addUser(user1);
            user1 = userService.getUser(userName);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = "success";
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(String userName, String password) {
        String result = "fail";
        User user = userService.getUser(userName);
        user.setPassword(password);
        userService.updateUser(user.getId(),user);
        result = "success";
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllUser() {
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        String allUsers = JSONArray.toJSONString(userList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allUsers",allUsers);
        return resultMap;
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET})
    public String deleteUser(int id) {
        userService.deleteUser(id);
        return "redirect:userManager";
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(HttpSession httpSession){
        httpSession.setAttribute("currentUser","");
        return "redirect:login";
    }

    // 修改用户信息页面显示
    @RequestMapping(value = "/editUser", method = {RequestMethod.GET})
    public String editUserUI(Integer id, Model model) throws Exception {
        if (id == null) {

            return "redirect:/showUser";
        }
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/editUserInfo";
    }

    // 修改用户信息处理
    @RequestMapping(value = "/editUser", method = {RequestMethod.POST})
    public String editUser(@ModelAttribute User user,HttpSession session) throws Exception {
        System.out.print("用户序号为："+user.getId());

        if(userService.updateUser(user.getId(),user))
        System.out.print("用户名字为："+user.getName());
        //重定向
        return "redirect:/userManager";
    }

    // 添加用户信息页面显示
    @RequestMapping(value = "/addUser", method = {RequestMethod.GET})
    public String addUserUI( Model model) throws Exception {
        return "/addUserInfo";
    }
    // 添加用户信息处理
    @RequestMapping(value = "/addUserInfo", method = {RequestMethod.POST})
    public String addUser(@ModelAttribute User user,HttpSession session) throws Exception {
        System.out.print("用户序号为："+user.getId());
        userService.addUser(user);
        //重定向
        return "redirect:/userManager";
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserById(int id) {
        User user = userService.getUser(id);
        String result = JSON.toJSONString(user);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

}
