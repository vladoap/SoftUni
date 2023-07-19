package softuni.exam.util;


import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {


    <T> Set<ConstraintViolation<T>> violation(T entity);

    <T> boolean isValid(T entity);
}
