package com.globot.hmi.attendance.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ambitous on 2017/11/28.
 */
@Controller
@RequestMapping("/api/page")
public class PageController {

    @RequestMapping("/login")
    public String showLogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);
        return "login";
    }
}
