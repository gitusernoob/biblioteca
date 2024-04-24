package com.biblioteca.model;

import lombok.*;

import java.io.Serializable;



@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Libro implements Serializable {

    private String ISBN;
    private String titolo;
    private String genere;
    private String autore;
    private String anno_pubblicazione;


}
