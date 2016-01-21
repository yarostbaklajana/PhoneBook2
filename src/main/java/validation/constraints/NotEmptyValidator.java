package validation.constraints;


import validation.constraints.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
    @Override
    public void initialize(NotEmpty notEmpty) {
    }

    @Override
    public boolean isValid(String notEmptyString, ConstraintValidatorContext constraintValidatorContext) {
        return notEmptyString != "" && notEmptyString != null;
    }
}
