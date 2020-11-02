package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exceptions.IdsAreNotTheSameException;
import pl.coderslab.charity.models.dtos.InstitutionDTO;
import pl.coderslab.charity.models.entities.Institution;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/institutions")
public class InstitutionController {
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionsList() { return institutionService.findAllInstitutions();
    }

    @RequestMapping
    public String showAdminDashboard() {
        return "adminInstitutions";
    }

    @GetMapping("/add")
    public String showAddFoundationForm(Model model) {
        model.addAttribute("institutionDTO", new InstitutionDTO());
        return "institutionForm";
    }

    @PostMapping("/add")
    public String saveInstitution(@Valid InstitutionDTO institutionDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "institutionForm";
        }
        institutionService.saveInstitution(institutionDTO);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/edit/{id}")
    public String showEditInstitutionForm(@PathVariable long id,
                                          Model model) {
        model.addAttribute("institutionDTO", institutionService.findInstitutionDTOById(id));
        return "editInstitutionForm";
    }

    @PostMapping("/edit/{id}")
    public String editInstitution(@Valid InstitutionDTO institutionDTO,
                                  BindingResult result,
                                  @PathVariable long id){

        if(result.hasErrors()){
            return "editInstitutionForm";
        }
        if(id != institutionDTO.getId()){
            throw new IdsAreNotTheSameException("Id nie są takie same");
        }
        institutionService.editInstitution(institutionDTO);
        return "redirect:/admin/institutions";
    }

    @PostMapping("/remove/confirm")
    public String confirmRemoval(@RequestParam long id,
                                 Model model){
        model.addAttribute("id", id);
        return "confirmationPage";
    }

    @RequestMapping("/remove/{id}")
    public String removeInstitution(@PathVariable long id){
        institutionService.remove(id);
        return "redirect:/admin/institutions";
    }
}
