package com.zlw.crowdsourcing.Router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RouterController {
    @RequestMapping("/toIndex")
    public String toIndexPage(){
        return "index";
    }

    @RequestMapping("/toEdit")
    public String toEditPage(){
        return "demand-edit";
    }

    @RequestMapping("/toDemandList")
    public String toFind(){
        return "demand-list";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userid");
        session.removeAttribute("username");
        return "login";
    }

    @RequestMapping("/getPosition")
    public String getPosition(){
        return "location-get";
    }

    @RequestMapping("/worker/toIndex")
    public String toWorkerIndex(){
        return "worker/index";
    }

    @RequestMapping("/worker/toOrderList")
    public String toOrderList(){
        return "worker/order-list";
    }

    @RequestMapping("/worker/toTaskList")
    public String toTaskList(){
        return "worker/task-list";
    }

    @RequestMapping("/admin/toIndex")
    public String toAdminIndex(){
        return "admin/index";
    }

    @RequestMapping("/admin/welcome")
    public String toWelcome(){
        return "admin/welcome";
    }
}
