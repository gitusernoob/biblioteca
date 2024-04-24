package com.biblioteca.controller;


import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("biblioteca")  //l'indirizzo della pagine viene preceduta da biblioteca
public class LibroController {

    @GetMapping( value = "selezionaTuttiLibri") //l'indirizzo web dove collegarsi con postman per fare test
    public static List<Libro> selezionaTuttiLibri(){
        return LibroRepository.selezionaLibri();
    }


}
