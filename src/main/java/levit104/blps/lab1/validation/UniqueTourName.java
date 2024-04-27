package levit104.blps.lab1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import levit104.blps.lab1.utils.ValidationUtils;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueTourNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTourName {
    String message() default ValidationUtils.TOUR_NAME_TAKEN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}