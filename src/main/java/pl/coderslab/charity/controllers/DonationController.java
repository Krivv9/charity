package pl.coderslab.charity.controllers;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.models.dtos.DonationToAddDTO;
import pl.coderslab.charity.models.entities.Category;
import pl.coderslab.charity.models.entities.Institution;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/donation/form")
    public String saveDonation(DonationToAddDTO donationDTO,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        donationService.saveDonation(donationDTO);
        return "confirm";
    }

    @GetMapping("/donation/form")
    public String showDonationForm(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        model.addAttribute("donationToAddDTO", new DonationToAddDTO());
        return "form";
    }
}