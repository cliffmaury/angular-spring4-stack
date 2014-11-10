package fr.valtech.angularspring.app.web.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cliff.maury on 10/11/2014.
 */

/**
 * data transfer object containing all the validation errors that will be returned to the client
 * when the MethodArgumentNotValidException is thrown.
 */
public class ValidationErrorsView {

    // http://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-adding-validation-to-a-rest-api/
    // Creating the data transfer object

    private List<FieldErrorView> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String messageKey) {
        fieldErrors.add(new FieldErrorView(field, messageKey));
    }

    public List<FieldErrorView> getFieldErrors() {
        return fieldErrors;
    }

    public static class FieldErrorView {

        private String field;

        private String messageKey;

        public FieldErrorView(String field, String messageKey) {
            this.field = field;
            this.messageKey = messageKey;
        }

        public String getField() {
            return field;
        }

        public String getMessageKey() {
            return messageKey;
        }
    }
}
