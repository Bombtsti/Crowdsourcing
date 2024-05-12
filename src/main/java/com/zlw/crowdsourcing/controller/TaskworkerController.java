package com.zlw.crowdsourcing.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zlw.crowdsourcing.mapper.TaskworkerMapper;
import com.zlw.crowdsourcing.pojo.Taskworker;
import com.zlw.crowdsourcing.vo.TaskVo;
import com.zlw.crowdsourcing.vo.WorkerTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlw
 * @since 2022-04-09
 */
@Controller
public class TaskworkerController {

    @Autowired
    private TaskworkerMapper taskworkerMapper;

    //获取所有任务
    @RequestMapping("/task/getAllTaskVoByWorkerId")
    public String getAllTaskVoByWorkerId(Model model, HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        String userid = String.valueOf(session.getAttribute("userid"));
        List<WorkerTaskVo> taskVos = taskworkerMapper.selectTaskVoByWorkerId(userid);
        PageInfo<WorkerTaskVo> pageInfo = new PageInfo<>(taskVos);
        model.addAttribute("pageInfo",pageInfo);
        return "worker/task-list";
    }
}
