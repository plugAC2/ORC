package pl.projectorc.controllers;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.projectorc.entities.User;
import pl.projectorc.models.AddUserModel;
import pl.projectorc.models.LoginModel;
import pl.projectorc.services.UserService;
import pl.projectorc.validators.AddUserModelValidator;
import pl.projectorc.validators.UsernameValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView getAddUser() {
        return new ModelAndView("register", "addUserModel", new AddUserModel());
    }

    @PostMapping("/add")
    public String saveUser(@Valid AddUserModel userModel, BindingResult bindingResult) {
        new AddUserModelValidator().validate(userModel, bindingResult);
        new UsernameValidator(userService).validate(userModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = getUserFromModel(userModel);
        System.out.println(user.toString());
        userService.newRecord(user);

        return "register";
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("login", "loginModel", new LoginModel());
    }

    @PostMapping("/login")
    public String loginTo(@Valid LoginModel loginModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "login";
        }
        if (userService.checkIfUsernameExist(loginModel.getUsername())) {
            if (userService.comparePasswords(loginModel.getUsername(), loginModel.getPassword())) {
                return "index";
            }
        }
        return "login";

    }


private User getUserFromModel(AddUserModel userModel) {
    String address = userModel.getStreet() + ", " + userModel.getCity() + ", " + userModel.getRegion() + ", " + userModel.getRegion() + ", " + userModel.getCountry();
    return new User(userModel.getUsername(),
            userModel.getPassword(),
            userModel.getEmail(),
            userModel.getFirstName(),
            userModel.getSecondName(),
            address);
    }
}