package levit104.blps.lab1.utils;

import levit104.blps.lab1.exceptions.EntityCreationException;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationUtils {
    public static final String USERNAME_TAKEN = "Пользователь с указанным именем уже существует";
    public static final String EMAIL_TAKEN = "Пользователь с указанным email уже существует";
    public static final String TOUR_NAME_TAKEN = "Экскурсия с указанным именем уже существует";
    public static final String BLANK_FIELD = "Заполните поле";
    public static final String INVALID_VALUE = "Некорректное значение";

    public static void handleCreationErrors(BindingResult bindingResult) {
        String errorsString = bindingResult.getFieldErrors()
                .stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining(";"));

        throw new EntityCreationException(errorsString);
    }
}