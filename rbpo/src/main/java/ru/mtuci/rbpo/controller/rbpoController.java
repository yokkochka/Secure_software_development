package ru.mtuci.rbpo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class rbpoController {

    @GetMapping("/secret")
    public String showHtmlPage(@RequestParam String secName) {
        if (secName.equals("Gendalf")){
            return "Obliviate!";
        }
        return "Try again!";
    }
}
