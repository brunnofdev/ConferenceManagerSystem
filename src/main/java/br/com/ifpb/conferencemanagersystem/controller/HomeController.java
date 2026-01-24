package br.com.ifpb.conferencemanagersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/events"; // Define a Home como a lista de eventos
    }
}
