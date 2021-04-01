package pl.projectorc.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projectorc.models.AddUserModel;

public class AddUserModelValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return AddUserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object user, Errors errors) {
        AddUserModel addUserModel = (AddUserModel) user;
        if(!addUserModel.getPassword().equals(addUserModel.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "PasswordsDontMatch");
        }
    }
}
