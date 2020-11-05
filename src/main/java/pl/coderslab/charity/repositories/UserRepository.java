package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.models.dtos.ShowUserDTO;
import pl.coderslab.charity.models.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRole(String role);

    List<User> findAllByRole(String role);

    User findUserById(long id);

    ShowUserDTO findUserDTOByIdAndRole(long id, String role);

    User findUserByIdAndRole(long id, String role);
}