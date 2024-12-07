package com.shopArche.shopArche.controllers;


import com.shopArche.shopArche.Repository.TovarRep;
import com.shopArche.shopArche.model.TovarPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Controller
public class controllerTovar {
    @Autowired
    private TovarRep tovarRep;


    @GetMapping("/uploadTovar")
    public String showUpluadForm(){
        return "uploadForm";
    }

    @PostMapping("/uploadTovar")
    public String upluadTovar(@RequestParam("photoFile") MultipartFile photoFile, @RequestParam String  description , String title, Model model){
        if(photoFile.isEmpty()) {
        model.addAttribute("message", "Пожалуйста, выберите видео для загрузки.");
        return "uploadForm";
    }
        try {
            String uploadDirectory = ("C:/uploads/photo/"); // Это будет папка внутри вашего проекта
            Path uploadPath = Paths.get(uploadDirectory);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = photoFile.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            photoFile.transferTo(filePath.toFile());

            TovarPost tovar = new TovarPost();
            tovar.setFileName(fileName);
            tovar.setFilePath(filePath.toString());
            tovar.setUploadDate(LocalDate.now());
            tovar.setDescription(description);
            tovar.setTitle(title);
            tovarRep.save(tovar);
            model.addAttribute("message", "Товар успешно загружен.");


        }catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "Ошибка при загрузке товара: " + e.getMessage());        }
        return "upluadstatus";
        }


    @GetMapping("/tovars")
    public String viewTovars(Model model) {
        model.addAttribute("tovars", tovarRep.findAll());
        return "tovarList";
    }







}
