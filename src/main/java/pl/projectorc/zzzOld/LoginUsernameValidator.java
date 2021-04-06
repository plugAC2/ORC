package pl.projectorc.zzzOld;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projectorc.models.LoginModel;
import pl.projectorc.services.UserService;

public class LoginUsernameValidator implements Validator {
    UserService service;

    public LoginUsernameValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginModel.class.equals(aClass);
    }

    @Override
    public void validate(Object user, Errors errors) {
        LoginModel userModel = (LoginModel) user;
        if (!service.checkIfUsernameExist(userModel.getUsername())) {
            errors.rejectValue("username", "UsernameNotExists");
        }
    }
}
