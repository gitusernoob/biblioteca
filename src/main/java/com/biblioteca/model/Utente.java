package com.biblioteca.model;

import lombok.*;

import java.io.Serializable;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utente implements Serializable {

    private String codice_utente;
    private String nome;
    private String cognome;


}
