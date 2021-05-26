package com.example.spring.controller;

import com.example.spring.model.Game;
import com.example.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("store")
public class MainController {
    @Autowired
    GameController gameController;

    @Autowired
    UserController userController;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/games")
    public String games(Model model) {
        model.addAttribute("games", gameController.findAllGames());
        return "games";
    }

    @GetMapping("/createGame")
    public String createGame() {
        return "createGame";
    }

    @PostMapping("/createGame")
    public String createGame(Game game) {
        gameController.createGame(game);
        return "redirect:/store/games";
    }

    @PostMapping("/deleteGame")
    private String deleteGame(Long id){
        gameController.deleteGame(id);
        return "redirect:/store/games";
    }

//    @GetMapping("/updateUser")
//    public String updateUser(Model model, String nickname) {
//        model.addAttribute("user", userController.findByNickname(nickname));
//        return "updateUserPage";
//    }
//
//    @PostMapping("/updateUserMethod")
//    private String updateUser(Long id, User user){
//        userController.updateUser(id, user);
//        return "redirect:/home/admin";
//    }

    @PostMapping("/deleteUser")
    private String deleteUser(Long id){
        userController.deleteUser(id);
        return "redirect:/store/admin";
    }

    @GetMapping("/admin")
    public String users(Model model) {
        model.addAttribute("users", userController.findAllUsers());
        return "admin";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("users", userController.findAllUsers());
        return "profile";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @GetMapping("/homeAdmin")
    public String signInAdmin() {
        return "homeAdmin";
    }

    @PostMapping("/signin")
    public String signIn(Model model, String nickname) {
        User user = userController.findByNickname(nickname);
        if (user.isAccountNonExpired() && user.getNickname().equals("admin") && user.getPassword().equals("admin")) {
            model.addAttribute("nickname", user);
            return "redirect:/store/homeAdmin";
        }
        else if (user.isAccountNonExpired() && !user.getNickname().equals("admin")) {
            model.addAttribute("nickname", user);
            return "redirect:/store/home";
        }
        else {
            return "redirect:/store";
        }
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        userController.createUser(user);
        return "redirect:/store/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
