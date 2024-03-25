package com.ale.springboot.webapp.springbootweb.controllers;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ale.springboot.webapp.springbootweb.models.User;
import com.ale.springboot.webapp.springbootweb.models.dto.UserDto;

// Con este decorador creamos un controlador Rest
@RestController
// usamos este decorador para crear una ruta elevada a las que tenemos
// adentro del controlador ejemplo /api/details
@RequestMapping("/api")
public class UserRestController {

    // se usa para traer los datos es una petición get
    // se le pasa la ruta de la petición
    // @GetMapping("/details")

    // Podemos usar RequestMapping para definir la ruta y el tipo de método
    // pero este funciona igual que el getMapping
    // @RequestMapping(path = "/details", method = RequestMethod.GET)

    @GetMapping("/details")
    public UserDto details() {
        UserDto userDto = new UserDto();

        User user = new User("Andres", "Velez");
        userDto.setUser(user);
        userDto.setTitle("Hola mundo en Spring Boot");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user = new User("Andres", "Velez");
        User user2 = new User("Pedro", "Camacho");
        User user3 = new User("Julian", "Perez");

        List<User> users = Arrays.asList(user, user2, user3);
        // List<User> users = new ArrayList<>();
        // users.add(user);
        // users.add(user2);
        // users.add(user3);

        return users;
    }

    @GetMapping("/details-map")
    // definimos el tipo de retorno a map para retornar un JSON
    public Map<String, Object> detailsMap() {
        User user = new User("Andres", "Velez");
        // creamos la instancia del json
        Map<String, Object> body = new HashMap<>();
        body.put("title", "hola mundo");
        body.put("user", user);

        return body;
    }

}
