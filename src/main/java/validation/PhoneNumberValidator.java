package validation;

import models.PhoneNumber;

public interface PhoneNumberValidator {
    ValidationResult validate(PhoneNumber phoneNumber);
}
