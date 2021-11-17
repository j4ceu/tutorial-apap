package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.service.RoleService;
import apap.tutorial.cineplux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserFormPage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = userService.getUserRole(auth);
        if(role.equals("ADMIN")) {
            UserModel user = new UserModel();
            List<RoleModel> listRole = roleService.getListRole();
            model.addAttribute("user", user);
            model.addAttribute("listRole", listRole);
            return "form-add-user";
        } else {
            model.addAttribute("penjaga", false);
            return "notHaveAuthorization";
        }

    }

    @PostMapping(value="/addUser")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping(value="/list-user")
    private String userList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = userService.getUserRole(auth);
        if(role.equals("ADMIN")) {
            List<UserModel> listUser = userService.retrieveListUser();
            model.addAttribute("listUser",listUser);
            return "viewall-user";
        } else {
            model.addAttribute("penjaga", false);
            return "notHaveAuthorization";
        }

    }

    @GetMapping(value="/delete/{idUser}")
    private String deleteBioskop(@PathVariable(value="idUser") String id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = userService.getUserRole(auth);
        if(role.equals("ADMIN")){
            UserModel user = userService.getUserById(id);
            if (user != null) {
                userService.deleteUser(user);
                model.addAttribute("username",user.getUsername());
                return "deleteUserSuccess";
            } else {
                return "cantDeleteUser";
            }
        } else {
            model.addAttribute("penjaga", false);
            return "notHaveAuthorization";
        }

    }

    @GetMapping(value="/changepassword/{idUser}")
    private String changePassword(@PathVariable(value = "idUser") String id, Model model){
        UserModel user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("passwordBaru", false);
        model.addAttribute("passwordLama", false);
        return "form-changepassword";
    }

    @PostMapping(value="/changepassword")
    private String changePassword(@ModelAttribute UserModel user,
             @RequestParam("passwordBaru") String passwordBaru,
             @RequestParam("passwordLamaForm") String passwordLamaForm,
            @RequestParam("passwordLama") String passwordLama
            , Model model){

        Boolean cek = userService.comparePassword(passwordLamaForm, passwordLama);
        if (cek == true) {
            if (passwordBaru.equals(user.getPassword())) {
                userService.addUser(user);
            } else {
                System.out.println(user.getPassword());
                System.out.println(passwordBaru);
                user.setPassword(passwordLama);
                model.addAttribute("user", user);
                model.addAttribute("passwordBaru", true);
                model.addAttribute("passwordLama", false);
                return "form-changepassword";
            }
        } else {
            user.setPassword(passwordLama);
            model.addAttribute("user", user);
            model.addAttribute("passwordBaru", false);
            model.addAttribute("passwordLama", true);
            return "form-changepassword";
        }
        model.addAttribute("username", user.getUsername());
        return "changePasswordSuccess";
    }
}
