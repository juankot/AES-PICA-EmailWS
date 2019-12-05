package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.Email;
import co.edu.javeriana.pica.kallsonys.dto.GenericResponse;
import co.edu.javeriana.pica.kallsonys.facade.EmailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Notification")
public class EmailWS {

    @Autowired
    EmailFacade emailFacade;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity sendEmail(@Valid @RequestBody Email email) throws Exception {
        try {
            emailFacade.sendEmail(email);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw e;
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        StringBuilder errorsSb = new StringBuilder();
        errors.forEach((errorK, errorV) -> {
            errorsSb.append(errorV + "; ");
        });

        errorsSb.deleteCharAt(errorsSb.length()-1);
        return new GenericResponse(2, errorsSb.toString());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericResponse handleValidationExceptions(Exception ex) {
        return new GenericResponse(1, ex.getMessage());
    }
}
