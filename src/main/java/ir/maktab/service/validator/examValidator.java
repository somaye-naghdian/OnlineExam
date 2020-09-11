package ir.maktab.service.validator;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class examValidator implements Validator {

    @Autowired
    private Mapper mapper;

    @Override
    public boolean supports(Class<?> clazz) {
        return ExamDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExamDto examDto = (ExamDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");

        if (!examDto.getTitle().matches("^[a-zA-Z]{2,20}")) {
            errors.rejectValue("title", "LengthMissMatch");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "NotEmpty");
//     if( examDto.getStartDate().

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");

    }
}
