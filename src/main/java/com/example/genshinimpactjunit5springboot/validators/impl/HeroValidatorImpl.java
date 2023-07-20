package com.example.genshinimpactjunit5springboot.validators.impl;

import com.example.genshinimpactjunit5springboot.models.Hero;
import com.example.genshinimpactjunit5springboot.validators.interfaces.HeroValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HeroValidatorImpl implements HeroValidator {

    /*
    В этом примере мы используем ValidatorFactory и Validator из пакета javax.validation
    для получения экземпляра валидатора.
    Затем мы вызываем метод validate(this), чтобы выполнить валидацию текущего объекта Person.
    Если есть нарушения валидации (violations), мы строим сообщение об ошибке,
    содержащее текст каждого нарушения, и выбрасываем IllegalArgumentException.
    */
    @Override
    public void validate(Hero hero) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Hero>> violations = validator.validate(hero);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Hero> violation : violations) {
                errorMessage.append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}
