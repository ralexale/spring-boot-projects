package com.ale.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ale.springboot.webapp.springbootweb.models.dto.ParamDto;
import com.ale.springboot.webapp.springbootweb.models.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    /**
     * RequestParam se utiliza para obtener los params de la request
     * /api/params/foo?message=hola
     * RequestParam(
     * required = para validar que el parametro sea obligatorio
     * defaultValue = el valor por defecto si no viene el param
     * name = para modificar el nombre del param que se tiene que recibir
     * )
     * 
     * @param message
     * @return
     */
    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "hola") String message) {
        ParamDto param = new ParamDto();
        // podemos manejar el valor con un ternario o con un defaultValue
        // param.setMessage(message != null ? message : "hola");
        param.setMessage(message);

        return param;
    }

    @GetMapping("/bar")
    public ParamMixDto bar(@RequestParam String text, @RequestParam Integer code) {
        ParamMixDto param = new ParamMixDto();

        param.setMessage(text);
        param.setCode(code);

        return param;
    }

    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest request) {
        ParamMixDto params = new ParamMixDto();
        Integer code = 0;
        try {
            // si intentamos convertir un string a numero nos va a romper el servidor
            // por eso lo colocamos en un trycatch para manejar el error
            code = Integer.parseInt(request.getParameter("code"));
        } catch (Exception e) {
        }

        params.setCode(code);
        params.setMessage(request.getParameter("message"));

        return params;
    }

}
