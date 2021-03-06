package pl.coderslab.charity.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
@Slf4j
public class CustomExceptionHandlerController {

    @ExceptionHandler(ElementNotFoundException.class)
    public String elementNotFound(Exception e, Model model){
        log.error("Exception: Element not found");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(IdsAreNotTheSameException.class)
    public String idsAreNotTheSameException(Exception e, Model model){
        log.error("Exception: Given id's are not the same");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }


}