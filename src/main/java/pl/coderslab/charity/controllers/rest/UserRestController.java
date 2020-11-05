package pl.coderslab.charity.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.models.dtos.EditUserDTO;
import pl.coderslab.charity.models.dtos.ShowUserDTO;
import pl.coderslab.charity.models.entities.Role;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity getAll() {
        List<ShowUserDTO> showUserDTOList = userService.showUserDTOList(Role.ROLE_USER.toString());
        if (!showUserDTOList.isEmpty()) {
            return ResponseEntity.ok(showUserDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        ShowUserDTO showUserDTO = userService.findUserDTOByIdAndRole(id, Role.ROLE_USER.toString());
        if(showUserDTO != null){
            return ResponseEntity.ok(showUserDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public void editUser(@Valid @RequestBody EditUserDTO editUserDTO, @PathVariable long id){
        if(id != editUserDTO.getId()){
            throw new ElementNotFoundException("ID się nie zgadzają");
        }
        userService.adminEditUser(editUserDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.remove(id, Role.ROLE_USER.toString());
    }
}