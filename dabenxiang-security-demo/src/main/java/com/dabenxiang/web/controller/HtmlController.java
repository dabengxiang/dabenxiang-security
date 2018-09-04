package com.dabenxiang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date:2018/9/4
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Controller
@RequestMapping("/html")
public class HtmlController {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("name","张三");
        model.addAttribute("age",4);
        model.addAttribute("sex","男");
        return "/index.html";
    }


}
