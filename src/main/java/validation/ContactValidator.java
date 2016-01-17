package validation;

import models.Contact;

public interface ContactValidator {
    ValidationResult validate(Contact contact);
}
