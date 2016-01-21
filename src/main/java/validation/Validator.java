package validation;


public interface Validator {
    public <T> ValidationResult validate(T item);
}
