package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/dashboard")
public class AdminController {
    @RequestMapping
    public String panel(){ return "redirect:/admin/institutions";}
}
