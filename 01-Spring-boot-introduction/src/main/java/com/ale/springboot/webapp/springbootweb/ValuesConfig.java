package com.ale.springboot.webapp.springbootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
/*
 * Asi podemos añadir nuestras propias configuraciones
 * a nuestro proyecto para mantenter cada archivo con configuraciones
 * especificas
 * Usamos el encoding para permitir caracteres especiales en el valor de los properties
 */
@PropertySources({
                @PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
// @PropertySource("classpath:values.properties2")
// @PropertySource("classpath:values.properties3")
})
public class ValuesConfig {

}
