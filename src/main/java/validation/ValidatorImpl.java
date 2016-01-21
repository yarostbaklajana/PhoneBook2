package validation;


import models.PhoneNumber;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidatorImpl implements Validator {

    @Override
    public <T> ValidationResult validate(T item) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> validationSet = validator.validate(item);

        ValidationResult result = new ValidationResult();
        for (ConstraintViolation<T> violation : validationSet) {
            result.addError(violation.getMessage());
        }

        return result;
    }
}
