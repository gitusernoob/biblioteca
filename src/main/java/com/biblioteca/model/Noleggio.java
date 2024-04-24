package com.biblioteca.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Noleggio {

    private String ISBN;
    private String codice_utente;
    private String data_inizio_prestito;
    private String data_fine_prestito;
}
