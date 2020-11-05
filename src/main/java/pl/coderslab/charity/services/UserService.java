package pl.coderslab.charity.services;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.app.PasswordGenerator;
import pl.coderslab.charity.app.SendPasswordEmail;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.models.dtos.*;
import pl.coderslab.charity.models.entities.Role;
import pl.coderslab.charity.models.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SendPasswordEmail sendPasswordEmail;
    private final PasswordGenerator passwordGenerator;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SendPasswordEmail sendPasswordEmail, PasswordGenerator passwordGenerator) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendPasswordEmail = sendPasswordEmail;
        this.passwordGenerator = passwordGenerator;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(UserToAddDTO userToAddDTO) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userToAddDTO.getPassword()));
        user.setActive(true);
        user.setEmail(userToAddDTO.getEmail());
        log.info("Attempt to save user: " + user);
        userRepository.save(user);
    }

    public boolean existsByRole(String role) {
        return userRepository.existsByRole(role);
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createSuperAdmin() {
        User user = User.builder()
                .email("admin@gmail.com")
                .role(Role.ROLE_SUPERADMIN.toString())
                .password(bCryptPasswordEncoder.encode("admin"))
                .build();
        userRepository.save(user);
    }

    public List<ShowUserDTO> showUserDTOList(String role) {
        List<User> userFromDBList = userRepository.findAllByRole(role);
        List<ShowUserDTO> showUserDTOList = new ArrayList<>();
        if (!userFromDBList.isEmpty()) {
            for (User user : userFromDBList) {
                ShowUserDTO showUserDTO = ShowUserDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .active(user.isActive())
                        .build();
                showUserDTOList.add(showUserDTO);
            }
        }
        return showUserDTOList;
    }

    public ShowUserDTO findUserDTOByIdAndRole(long id, String role) {
        User userFromDB = userRepository.findUserByIdAndRole(id, role);
        if (userFromDB != null) {
            ShowUserDTO showUserDTO = ShowUserDTO.builder()
                    .id(userFromDB.getId())
                    .firstName(userFromDB.getFirstName())
                    .lastName(userFromDB.getLastName())
                    .email(userFromDB.getEmail())
                    .active(userFromDB.isActive())
                    .build();
            return showUserDTO;
        } else {
            return null;
        }
    }

    public void adminEditUser(EditUserDTO editUserDTO) {
        User userFromDB = userRepository.findUserByIdAndRole(editUserDTO.getId(), Role.ROLE_USER.toString());
        if(userFromDB != null){
            userFromDB.setFirstName(editUserDTO.getFirstName());
            userFromDB.setLastName(editUserDTO.getLastName());
            userFromDB.setActive(editUserDTO.isActive());
            userRepository.save(userFromDB);
        } else {
            throw new ElementNotFoundException("Nie odnaleziono elementu");
        }
    }

    public void remove(long id, String role) {
        User userFromDB = userRepository.findUserByIdAndRole(id, role);
        if(userFromDB != null){
            userRepository.delete(userFromDB);
        } else {
            throw new ElementNotFoundException("Nie odnaleziono elementu");
        }

    }

    public void saveAdmin(@Valid RegisterAdminDTO registerAdminDTO) throws TemplateException, IOException, MessagingException {
        String password = passwordGenerator.generateCommonLangPassword();
        User userEntity = User.builder()
                .role(Role.ROLE_ADMIN.toString())
                .firstName(registerAdminDTO.getFirstName())
                .lastName(registerAdminDTO.getLastName())
                .email(registerAdminDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(password))
                .build();

        userRepository.save(userEntity);
        sendPasswordEmail.sendPasswordMail(password, userEntity);
        registerAdminDTO.setId(userEntity.getId());
    }

    public void savePassword(EditUserPasswordDTO editUserPasswordDTO) {
        User userFromDB = userRepository.findUserById(editUserPasswordDTO.getId());
        if(userFromDB != null){
            userFromDB.setPassword(bCryptPasswordEncoder.encode(editUserPasswordDTO.getPassword()));
            userRepository.save(userFromDB);
        }  else {
            throw new ElementNotFoundException("Nie odnaleziono elementu");
        }
    }

    }