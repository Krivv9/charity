package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.models.dtos.UserToAddDTO;
import pl.coderslab.charity.models.entities.User;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("user", new UserToAddDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid UserToAddDTO userToAddDTO, BindingResult bindingResult) {
        User userExists = userService.findUserByEmail(userToAddDTO.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(userToAddDTO);
        return "redirect:/login";
    }

    @RequestMapping("/login/success")
    public String success(Principal principal) {
        if (principal != null) {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if ("ADMIN".equals(grantedAuthority.getAuthority())) {
                    return "redirect:/admin/panel";
                } else if ("USER".equals(grantedAuthority.getAuthority())) {
                    return "redirect:/";
                }
            }
        }
        return "redirect:/";
    }
}