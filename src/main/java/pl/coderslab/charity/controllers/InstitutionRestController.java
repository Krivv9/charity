package pl.coderslab.charity.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exceptions.IdsAreNotTheSameException;
import pl.coderslab.charity.models.dtos.InstitutionDTO;
import pl.coderslab.charity.services.InstitutionService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionRestController {

    private final InstitutionService institutionService;
    private final MessageSource messageSource;

    public InstitutionRestController(InstitutionService institutionService, MessageSource messageSource) {
        this.institutionService = institutionService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public List<InstitutionDTO> getAll() {
        return institutionService.institutionDTOList();
    }

    @PostMapping
    // 201 Created + Location z adresem utworzonego zasobu
    public ResponseEntity saveInstitution(@Valid @RequestBody InstitutionDTO institutionDTO) {
        institutionService.saveInstitution(institutionDTO);
        return ResponseEntity.created(URI.create("/api/locations/" + institutionDTO.getId())).build();
    }

    @GetMapping("/{id}")
    // 200 OK + obiekt
    // 404 Not found
    public ResponseEntity getOne(@PathVariable Long id) {
        InstitutionDTO institution = institutionService.findInstitutionDTOById(id);
        if (institution != null) {
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    // 200 OK +- treść
    public ResponseEntity editInstitution(@Valid @RequestBody InstitutionDTO institutionDTO,
                                          @PathVariable Long id, Locale locale) throws JsonProcessingException {
        if(!id.equals(institutionDTO.getId())){
            return ResponseEntity.badRequest().body("{\"error\" : \"" + messageSource.getMessage("not.same.ids", null, locale) + "\"}");
        }
        institutionService.editInstitution(institutionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    // 200 OK - bez treść
    // 204 No Content
    public void confirmRemoval(@PathVariable Long id){
        institutionService.remove(id);
    }
}
