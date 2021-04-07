package pl.projectorc.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projectorc.models.UserModel;
import pl.projectorc.services.UserService;

public class UsernameValidator implements Validator {
    UserService service;

    public UsernameValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object user, Errors errors) {
        UserModel userModel = (UserModel) user;
        if (service.checkIfUsernameExist(userModel.getUsername())) {
            errors.rejectValue("username", "UsernameExists");
        }
    }
}
