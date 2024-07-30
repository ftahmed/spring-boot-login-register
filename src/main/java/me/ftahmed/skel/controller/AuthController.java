package me.ftahmed.skel.controller;

import me.ftahmed.skel.model.Role;
import me.ftahmed.skel.model.User;
import me.ftahmed.skel.service.RoleService;
import me.ftahmed.skel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @ModelAttribute("allRoles")
    public List<Role> allRoles() {
        return roleService.getRoles();
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(Model model){
        var user = new User();
        user.setPassword("p1234567");
        user.setMobile("1234567890");
        user.setRoles(List.of(roleService.getRoles().getFirst())); // FIXME
        model.addAttribute("user", user);
        return "auth/register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerUser(Model model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User registration error!");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }
        List<Object> userPresentObj = userService.isUserPresent(user);
        if((Boolean) userPresentObj.get(0)){
            model.addAttribute("successMessage", userPresentObj.get(1));
            return "auth/register";
        }

        user.setOffsetTimeColumn(OffsetTime.now());  // FIXME
        user.setOffsetDateTimeColumn(OffsetDateTime.now());
        user.setZonedDateTimeColumn(ZonedDateTime.now(TimeZone.getTimeZone("Europe/London").toZoneId()));
        user.setSpan(Period.of(1, 2, 3));

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/login";
    }
}
