package pl.projectorc.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projectorc.models.AddUserModel;
import pl.projectorc.models.LoginModel;
import pl.projectorc.services.UserService;

public class UsernameValidator implements Validator {
    UserService service;

    public UsernameValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginModel.class.equals(aClass);
    }

    @Override
    public void validate(Object user, Errors errors) {
        AddUserModel userModel = (AddUserModel) user;
        if (!service.checkIfUsernameExist(userModel.getUsername())) {
            errors.rejectValue("username", "UsernameExists");
        }
    }
}
