package com.driver.driver;
import com.driver.driver.model.User;
import com.driver.driver.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

@RestController

public class UserController {
   @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexString() {
        return "index";

    }
    @GetMapping("/admin")
    public String adminString() {
        return "admin";
    }
    @GetMapping("/login")
    public String loginString() {
        return "login";

    }

//    @GetMapping("/register")
//    public String showNewUsersForm(Model model)  {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "register";
//    }
//    @PostMapping("/api/register")
//    public String registerUser( @RequestBody @ModelAttribute("user") User user) throws Exception {
//
//       return userService.register(user);
//
//    }
    @PostMapping("/register/user")
    public String registerUser(@RequestBody User user, Model model) {
    userService.registerUser(user,model);
        return "redirect:/login";
    }


    @GetMapping("/api/login")
    public String showLoginForm( @RequestBody LoginDto user) {
       if(userService.Login(user.getEmail(), user.getPassword()))
        {
            return "suceess";
        }else
        { return "not verified";}

    }
//    @GetMapping("/register/admin")
//    public String showADMINUsersForm(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "registeradmin";
//    }
//    @PostMapping("/add/admin")
//    public String saveADMINUsers(@ModelAttribute("user") User user) {
//       return userService.saveADMINUsers(user);
//         //return "redirect:/login";
//    }

}
