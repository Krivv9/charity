package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.dtos.DonationToAddDTO;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;

@RequestMapping("/donation")
@Controller
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("/form")
    public String showDonationForm(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        model.addAttribute("donation", new DonationToAddDTO());
        return "formDonation";
    }

    @PostMapping("/form")
    public String saveDonation(@Valid DonationToAddDTO donationDTO,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "formDonation";
        }
        donationService.saveDonation(donationDTO);
        return "formDonation";
    }
}