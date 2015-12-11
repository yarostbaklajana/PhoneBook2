package validation;

import models.Contact;

public class ContactValidator {
    public ValidationResult validate(Contact contact) {
        ValidationResult validationResult = new ValidationResult();
        if(isEmptyOrNull(contact.getFirstName())) {
            validationResult.addError("First Name is empty");
        }

        if(isEmptyOrNull(contact.getLastName())) {
            validationResult.addError("Last Name is empty");
        }

        if(validationResult.getErrors().size() == 0) {
            validationResult.setIsValid(true);
        }

        return validationResult;
    }

    private boolean isEmptyOrNull(String field) {
        return field.equals("") || field.equals(null);
    }
}
