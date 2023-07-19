package softuni.exam.util.impl;

import org.springframework.context.annotation.Configuration;
import softuni.exam.util.ValidationUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Configuration
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationUtilImpl() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violation(T entity) {
        return validator.validate(entity);
    }

    @Override
    public <T> boolean isValid(T entity) {
        return validator.validate(entity).isEmpty();
    }
}
