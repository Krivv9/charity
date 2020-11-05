package pl.coderslab.charity.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.models.dtos.EditUserDTO;
import pl.coderslab.charity.models.dtos.EditUserPasswordDTO;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class EditUserDataRestController {

    private final UserService userService;


    @PutMapping("/{id}/editData")
    public void editPersonalData(@PathVariable long id,
                                 @Valid @RequestBody EditUserDTO editUserDTO,
                                 Principal principal){
        if(id != editUserDTO.getId()){
            throw new ElementNotFoundException("ID się nie zgadzają");
        }
        userService.adminEditUser(editUserDTO);
    }

    @PutMapping("/{id}/editPassword")
    public void editPassword(@PathVariable long id, @Valid @RequestBody EditUserPasswordDTO editUserPasswordDTO){
        if(id != editUserPasswordDTO.getId()){
            throw new ElementNotFoundException("ID się nie zgadzają");
        }
        userService.savePassword(editUserPasswordDTO);
    }

}