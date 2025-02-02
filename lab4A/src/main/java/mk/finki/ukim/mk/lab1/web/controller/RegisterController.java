package mk.finki.ukim.mk.lab1.web.controller;

import mk.finki.ukim.mk.lab1.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab1.model.exceptions.PasswordDoNotMatchException;
import mk.finki.ukim.mk.lab1.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthenticationService authenticationService;

    public RegisterController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if (error!= null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname){

        try {
            this.authenticationService.register(username,password,repeatedPassword,name,surname);
            return "redirect:/login";
        }catch (PasswordDoNotMatchException | InvalidArgumentsException ex){
            return "redirect:/register?error=" + ex.getMessage();
        }

    }
}
