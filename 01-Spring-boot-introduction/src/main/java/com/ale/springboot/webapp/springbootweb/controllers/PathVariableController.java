package com.ale.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ale.springboot.webapp.springbootweb.models.User;
import com.ale.springboot.webapp.springbootweb.models.dto.ParamDto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/var")
public class PathVariableController {
    /*
     * con la anotación value podemos traer configuraciones que colocamos
     * en el archivo application.properties se puede ver como una manera
     * de definir variables de entorno
     */

    @Value("${config.username}")
    private String username;

    @Value("${config.code}")
    private Integer code;

    @Value("${config.listOfValues}")
    private String[] listOfValues;

    @Autowired
    private Environment env;

    /*
     * Usamos las {} para pasar un valor dinámico
     * lo obtenemos con @PathVariable y el nombre de la variable
     * tiene que ser el mismo de la ruta
     * s
     * 
     * @PathVariable le podemos pasar el name en caso de que quisiéramos cambiar
     * el nombre de la variable
     */

    @GetMapping("/baz/{message}")
    // public ParamDto bazDto(@PathVariable(name = "message") String messageOther)
    public ParamDto bazDto(@PathVariable String message) {
        ParamDto param = new ParamDto();
        param.setMessage(message);

        return param;
    }

    /*
     * aca estamos creando una url dinamica para obtener esos datos
     * ejemplo de la url
     * /api/var/mix/chocolate/123123
     */
    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();

        json.put("product", product);
        json.put("id", id);
        return json;
    }

    /*
     * PostMapping para recibir peticiones post
     * RequestBody para obtener los datos que vengan en el body
     * estos dados tienen que venir como un objeto JSON
     */
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        // hacer algo con el usuario como guardarlo en una db

        return user;
    }

    /*
     * también podemos pasar los values como parametros
     * al método
     * public Map<String, Object> values(@Value("${config.code}") Integer code)
     */
    @GetMapping("/values")
    public Map<String, Object> values() {
        Map<String, Object> json = new HashMap<>();

        json.put("username", username);
        json.put("code", code);
        json.put("message", env.getProperty("config.message"));
        json.put("listOfValues", listOfValues);

        return json;
    }

}
