package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.models.dtos.UserToAddDTO;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userToAddDTO", new UserToAddDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid @ModelAttribute("userToAddDTO") UserToAddDTO userToAddDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(userToAddDTO);
        return "redirect:/login";
    }
}
