package com.biblioteca.controller;


import com.biblioteca.model.Libro;
import com.biblioteca.model.Noleggio;
import com.biblioteca.repository.LibroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("biblioteca")  //l'indirizzo della pagine viene preceduta da biblioteca
public class LibroController {

    @GetMapping( value = "selezionaTuttiLibri") //l'indirizzo web dove collegarsi con postman per fare test
    public static List<Libro> selezionaTuttiLibri(){
        return LibroRepository.selezionaTuttiLibri();
    }

    @PostMapping("inserisciLibro")
    public String inserisciLibro(@RequestBody Libro libro) {
        LibroRepository.inserisciLibro(libro);
        return "Libro inserito con successo!";
    }

    // Metodo per inserire un nuovo noleggio nel database
    @PostMapping("/inserisciNoleggio")
    public String inserisciNoleggio(@RequestBody Noleggio noleggio) {
        LibroRepository.inserisciNoleggio(noleggio);
        return "Noleggio inserito con successo!";
    }


    // Aggiungi il nuovo metodo per selezionare i libri per genere
    @GetMapping("/selezionaLibriPerGenere")
    public List<Libro> selezionaLibriPerGenere(@RequestParam("genere") String genere) {
        return LibroRepository.selezionaLibriPerGenere(genere);
    }

}
