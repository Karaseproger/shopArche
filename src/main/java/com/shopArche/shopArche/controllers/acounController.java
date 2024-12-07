package com.shopArche.shopArche.controllers;

import com.shopArche.shopArche.Repository.Users;
import com.shopArche.shopArche.model.RegisterDto;
import com.shopArche.shopArche.model.SecuretyPost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class acounController {
    @Autowired
        private Users repo;


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerDto",new RegisterDto());
        model.addAttribute("success", false);
        return "register";
    }


    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute("registerDto") RegisterDto registerDto, BindingResult result){
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            result.addError(
                    new FieldError("registerDto", "confirmPassword", "Password and Confirm Password do not match")
            );
        }
        SecuretyPost appUser= (SecuretyPost) repo.findByEmail(registerDto.getEmail());
        if (appUser != null){
            result.addError(
                    new FieldError("registerDto", "email", "Email address is already used")
            );
        }

        if(result.hasErrors()){
            return "register";
        }
        try{
            // создаю новый акаунт
            var bCryptEncoder = new BCryptPasswordEncoder();

            SecuretyPost newUser = new SecuretyPost();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("client");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);


        }
        catch (Exception ex){
            result.addError(
                    new FieldError("registerDto", "firstName",
                            ex.getMessage())
            );
        }
        return "register";
    }
}
