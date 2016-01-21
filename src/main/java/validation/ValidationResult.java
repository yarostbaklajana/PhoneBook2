package validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private boolean isValid;
    private List<String> errors;
    public ValidationResult() {
        errors = new ArrayList<String>();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean getIsValid() {
        return errors.isEmpty();
    }
}
