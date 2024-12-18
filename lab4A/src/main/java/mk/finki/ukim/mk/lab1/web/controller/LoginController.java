package mk.finki.ukim.mk.lab1.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab1.model.User;
import mk.finki.ukim.mk.lab1.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab1.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;

        try{
            user = this.authenticationService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }catch (InvalidUserCredentialsException ex){
            model.addAttribute("hasError",true);
            model.addAttribute("error", ex.getMessage());
            return "login";
        }
    }

}

