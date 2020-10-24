package pl.coderslab.charity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.services.DonationService;


@Controller
@Slf4j
public class HomeController {
    private final DonationService donationService;

    public HomeController(DonationService donationService) {
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("numberOfDonations",donationService.numberOfDonations());
        model.addAttribute("numberOfAllDonations",11111111);

        log.info("Test" + donationService.numberOfAllDonations());
        System.out.println("Czesc ooooo -----------------------");
        log.info("----------------------------------------------------------------------");
        return "index";
    }
}
