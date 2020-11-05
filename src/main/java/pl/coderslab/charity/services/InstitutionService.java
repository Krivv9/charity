package pl.coderslab.charity.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.exceptions.ElementNotFoundException;
import pl.coderslab.charity.models.dtos.InstitutionDTO;
import pl.coderslab.charity.models.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public void saveInstitution(InstitutionDTO institutionDTO) {
        Institution institutionEntity = Institution.builder()
                .name(institutionDTO.getName())
                .description(institutionDTO.getDescription())
                .build();
        institutionDTO.setId(institutionEntity.getId());

        institutionRepository.save(institutionEntity);

    }

    public InstitutionDTO findInstitutionDTOById(long id) {
        Institution iDB = institutionRepository.findInstitutionById(id);

        return InstitutionDTO.builder()
                .id(iDB.getId())
                .name(iDB.getName())
                .description(iDB.getDescription())
                .build();
    }

    public void editInstitution(InstitutionDTO institutionDTO) {
        Institution iDB = institutionRepository.findInstitutionById(institutionDTO.getId());
        if(iDB == null){
            throw new ElementNotFoundException("Elementu nie odnaleziono!");
        }

        iDB.setName(institutionDTO.getName());
        iDB.setDescription(institutionDTO.getDescription());


        institutionRepository.save(iDB);
    }

    public void remove(long id) {
        institutionRepository.delete(institutionRepository.findInstitutionById(id));
        Institution iDB = institutionRepository.findInstitutionById(id);
        if(iDB == null){
            throw new ElementNotFoundException("Elementu nie odnaleziono!");
        }
        institutionRepository.delete(iDB);
    }

    public List<InstitutionDTO> institutionDTOList(){
        List<Institution> allInstitutionsFromDB = institutionRepository.findAll();
        List<InstitutionDTO> institutionDTOList = new ArrayList<>();
        for(Institution institution : allInstitutionsFromDB){
            InstitutionDTO institutionDTO = InstitutionDTO.builder()
                    .id(institution.getId())
                    .name(institution.getName())
                    .description(institution.getDescription())
                    .build();
            institutionDTOList.add(institutionDTO);
        }
        return institutionDTOList;

    }
}
