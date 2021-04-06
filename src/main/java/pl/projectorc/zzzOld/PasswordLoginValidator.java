package pl.projectorc.zzzOld;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.projectorc.entities.User;
import pl.projectorc.models.LoginModel;
import pl.projectorc.services.UserService;

import java.util.Optional;

public class PasswordLoginValidator implements Validator {

    UserService userService;

    public PasswordLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginModel.class.equals(aClass);
    }

    @Override
    public void validate(Object user, Errors errors) {
        LoginModel userModel = (LoginModel) user;
        Optional<User> username = userService.showRecordByUsername(userModel.getUsername());
        String dbPassword = username.map(User::getPassword).orElse("error");
        if (!dbPassword.equals(userModel.getPassword())) {
            errors.rejectValue("password", "WrongPassword");
        }
    }
}
