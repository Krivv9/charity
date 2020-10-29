package pl.coderslab.charity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.models.dtos.DonationToAddDTO;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;


@Controller
@Slf4j
public class HomeController {
    private final DonationService donationService;
    private final InstitutionService institutionService;

    public HomeController(DonationService donationService, InstitutionService institutionService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("numberOfDonations",donationService.numberOfDonations());
        model.addAttribute("numberOfAllDonations",donationService.numberOfAllDonations());
        model.addAttribute("institutions",institutionService.findAllInstitutions());
        return "index";
    }
}
