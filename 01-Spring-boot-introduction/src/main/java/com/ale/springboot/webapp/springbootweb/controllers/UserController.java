package com.ale.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    // se usa para traer los datos es una petición get
    // se le pasa la ruta de la petición
    @GetMapping("/details")
    // con el model le podemos pasar datos a la vista
    public String details(Model model) {
        model.addAttribute("title", "hola mundo");
        model.addAttribute("name", "Andres");
        model.addAttribute("lastname", "Velez");
        return "details";
    }

}
