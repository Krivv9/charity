package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.models.entities.Institution;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Institution findInstitutionById(Long institutionId);
}
