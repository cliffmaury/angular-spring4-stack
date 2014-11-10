package fr.valtech.angularspring.app.web;

import fr.valtech.angularspring.app.web.view.ValidationErrorsView;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cliff.maury on 10/11/2014.
 */

/**
 * Manage all errors from REST APIs
 */
@ControllerAdvice
public class RestErrorHandler {

    // when Bean Validation fails
    // see http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-adding-validation-to-a-rest-api/

    /**
     * returns a list of field errors, with the first code associated with the constraint (i.e the constraint message key)
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorsView processValidation(MethodArgumentNotValidException ex) {

        ValidationErrorsView validationErrorsView = new ValidationErrorsView();

        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrorsView.addFieldError(fieldError.getField(), fieldError.getCodes()[0]);
        }

        return validationErrorsView;
    }

}
