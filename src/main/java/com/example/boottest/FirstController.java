package com.example.boottest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import com.example.boottest.service.TemplateParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * FirstController
 */
@Controller
public class FirstController {

    @Autowired
    private TemplateParser templateParser;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadTemplate(@RequestParam(name = "file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        try {
            byte[] content = file.getBytes();
            String filename = System.getProperty("user.dir") + "/tmp/" + file.getOriginalFilename();
            Path path = Paths.get(filename);
            Files.write(path, content);

            templateParser.parse(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "results";
    }  
    
}