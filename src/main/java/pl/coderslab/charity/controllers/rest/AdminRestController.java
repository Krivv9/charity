package pl.coderslab.charity.controllers.rest;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.models.dtos.RegisterAdminDTO;
import pl.coderslab.charity.models.dtos.ShowUserDTO;
import pl.coderslab.charity.models.dtos.EditUserDTO;
import pl.coderslab.charity.models.entities.Role;
import pl.coderslab.charity.services.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/superadmin/admins")
public class AdminRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getAll(){
        List<ShowUserDTO> showUserDTOList = userService.showUserDTOList(Role.ROLE_ADMIN.toString());
        if(!showUserDTOList.isEmpty()){
            return ResponseEntity.ok(showUserDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity saveAdmin(@Valid @RequestBody RegisterAdminDTO registerAdminDTO) throws TemplateException, IOException, MessagingException {
        userService.saveAdmin(registerAdminDTO);
        return ResponseEntity.created(URI.create("/superadmin/admins/" + registerAdminDTO.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        ShowUserDTO showUserDTO = userService.findUserDTOByIdAndRole(id, Role.ROLE_ADMIN.toString());
        if(showUserDTO != null){
            return ResponseEntity.ok(showUserDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public void editAdmin(@Valid @RequestBody EditUserDTO editUserDTO, @PathVariable long id){
        if(id != editUserDTO.getId()){
            throw new ElementNotFoundException("ID się nie zgadzają");
        }
        userService.adminEditUser(editUserDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.remove(id, Role.ROLE_ADMIN.toString());
    }
}