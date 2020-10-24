package pl.coderslab.charity.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.models.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public Institution findInstitutionById(Long institutionId) {
        return  institutionRepository.findInstitutionById(institutionId);
    }

    public List<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }
}
