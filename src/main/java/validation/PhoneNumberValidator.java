package validation;

import models.PhoneNumber;

public class PhoneNumberValidator {
    public ValidationResult validate(PhoneNumber phoneNumber) {
        ValidationResult validationResult = new ValidationResult();
        if(isEmptyOfNull(phoneNumber.getType())) {
            validationResult.addError("Phone type is empty.");
        }

        if(isEmptyOfNull(phoneNumber.getPhoneNumber())) {
            validationResult.addError("Phone number is empty.");
        } else if(!isNumeric(phoneNumber.getPhoneNumber())) {
            validationResult.addError("Only numbers are allowed in Phone Number.");
        }

        if(validationResult.getErrors().size() == 0) {
            validationResult.setIsValid(true);
        }
        return validationResult;
    }

    private boolean isEmptyOfNull(String parameter) {
        return (parameter == null || parameter.equals(""));
    }

    private boolean isNumeric(String parameter) {
        return parameter.matches("^\\d{1,12}$");
    }
}
