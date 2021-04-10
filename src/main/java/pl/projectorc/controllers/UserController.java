package pl.projectorc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import pl.projectorc.models.UserModel;
import pl.projectorc.services.UserService;
import pl.projectorc.validators.PasswordRegisterValidator;
import pl.projectorc.validators.UsernameValidator;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView getAddUser() {
        return new ModelAndView("register", "addUserModel", new UserModel());
    }

    @PostMapping("/add")
    public String saveUser(@Valid UserModel userModel, BindingResult bindingResult) {
        new PasswordRegisterValidator().validate(userModel, bindingResult);
        new UsernameValidator(userService).validate(userModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.setUserFromModel(userModel);
        return "redirect:/login?newUser"; //dodać napis, że utworzono użytkownika
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}