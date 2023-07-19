package softuni.exam.util.impl;

import org.springframework.stereotype.Component;
import softuni.exam.util.ValidationUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public <E> Set<ConstraintViolation<E>> violation(E entity) {
        return validator.validate(entity);
    }

    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }
}
