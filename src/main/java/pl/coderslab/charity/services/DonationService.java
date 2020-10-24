package pl.coderslab.charity.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.models.dtos.DonationToAddDTO;
import pl.coderslab.charity.models.entities.Category;
import pl.coderslab.charity.models.entities.Donation;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DonationService {
    private final DonationRepository donationRepository;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    public DonationService(DonationRepository donationRepository, InstitutionService institutionService, CategoryService categoryService) {
        this.donationRepository = donationRepository;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
    }

    public long numberOfDonations(){return donationRepository.count();}

    public Integer numberOfAllDonations() {
        return donationRepository.numberOfAllDonations();
    }

    public void saveDonation(DonationToAddDTO donationDTO){
        List<Long> categoryIds = donationDTO.getCategoriesId();
        List<Category> categories = new ArrayList<>();

        for(long id : categoryIds){
            categories.add(categoryService.findCategoryById(id));
        }

        Donation donationEntity = new Donation();
        donationEntity.setCategories(categories);
        donationEntity.setQuantity(donationDTO.getQuantity());
        donationEntity.setInstitution(institutionService.findInstitutionById(donationDTO.getInstitutionId()));
        donationEntity.setStreet(donationDTO.getStreet());
        donationEntity.setCity(donationDTO.getCity());
        donationEntity.setZipCode(donationDTO.getZipCode());
        donationEntity.setPickUpDate(donationDTO.getPickUpDate());
        donationEntity.setPickUpTime(donationDTO.getPickUpTime());
        donationEntity.setPickUpComment(donationDTO.getPickUpComments());

        donationRepository.save(donationEntity);
    }
}
