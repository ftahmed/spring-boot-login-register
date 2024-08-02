package me.ftahmed.skel.controller;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import me.ftahmed.skel.model.Privilege;
import me.ftahmed.skel.model.Role;
import me.ftahmed.skel.model.User;
import me.ftahmed.skel.service.PrivilegeService;
import me.ftahmed.skel.service.RoleService;
import me.ftahmed.skel.service.UserService;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PrivilegeService privilegeService;

    @ModelAttribute("allRoles")
    public List<Role> allRoles() {
        return roleService.getRoles();
    }
    
    @ModelAttribute("allPrivileges")
    public List<Privilege> allPrivileges() {
        return privilegeService.getPrivileges();
    }
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        var user = new User();
        user.setPassword("p1234567"); // FIXME
        user.setMobile("1234567890");
        user.setRoles(List.of(roleService.getRoles().getFirst()));
        model.addAttribute("user", user);
        return "auth/register";
    }

    @PostMapping("/register")
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

    @GetMapping("/permission")
    public String permission(Model model){
        return "auth/permission";
    }

    @ModelAttribute("role")
    public Role initRole(Model model) {
        var role = new Role("NEW_ROLE"); // FIXME
        role.setPrivileges(List.of(new Privilege("NEW_PRIVILEGE_1"), new Privilege("NEW_PRIVILEGE_2")));
        return role;
    }

    @PostMapping("/permission")
    public String rolePermission(Model model, @ModelAttribute("role") @Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "role registration error!");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/permission";
        }
        List<Object> rolePresentObj = roleService.isRolePresent(role);
        if((Boolean) rolePresentObj.get(0)){
            model.addAttribute("successMessage", rolePresentObj.get(1));
            return "auth/permission";
        }

        roleService.saveRole(role);
        model.addAttribute("successMessage", "Role registered successfully!");

        return "auth/permission";
    }

}
