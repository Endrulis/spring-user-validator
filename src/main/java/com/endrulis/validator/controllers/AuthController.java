package com.endrulis.validator.controllers;

import com.endrulis.validator.dto.UserDTO;
import com.endrulis.validator.entities.UserEntity;
import com.endrulis.validator.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController( UserService userService) {
        this.userService = userService;
    }
    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm( Model model){
        // create model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDto,
                               BindingResult result,
                               Model model){
        UserEntity existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String users(Model model){
        List<UserDTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
