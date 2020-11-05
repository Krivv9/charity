package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.entities.Role;
import pl.coderslab.charity.services.UserService;

@Controller
@RequiredArgsConstructor
public class CreateSuperAdminController {

    private final UserService userService;

    @RequestMapping("/create/admin")
    public String createSuperAdmin (Model model){
                if (userService.existsByRole(Role.ROLE_SUPERADMIN.toString())) {
                    model.addAttribute("error", "Podany użytkownik (SUPERADMIN) już isniteje");
                    return "createAdmin";
                }
                userService.createSuperAdmin();
                return "redirect:/login";
            }
    }