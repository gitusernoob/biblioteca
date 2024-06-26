package com.biblioteca.repository;

import com.biblioteca.constants.DbConfig;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Noleggio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository {

    public static List<Libro> selezionaTuttiLibri()
    {
        List<Libro> libriList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "SELECT * FROM libro";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Libro b = new Libro(
                        rs.getString("CODICE_ISBN"),
                        rs.getString("TITOLO"),
                        rs.getString("GENERE"),
                        rs.getString("AUTORE"),
                        rs.getString("ANNO_PUBBLICAZIONE")
                );
                libriList.add(b);
            }
            return libriList;

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    //inserisci libro

    // Metodo per inserire un nuovo libro nel database
    public static void inserisciLibro(Libro libro) {
        try {
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "INSERT INTO Libro (CODICE_ISBN, TITOLO, GENERE, ANNO_PUBBLICAZIONE, AUTORE) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, libro.getISBN());
            statement.setString(2, libro.getTitolo());
            statement.setString(3, libro.getGenere());
            statement.setString(4, libro.getAnno_pubblicazione());
            statement.setString(5, libro.getAutore());

            statement.executeUpdate(); // Esegui l'istruzione di inserimento

            System.out.println("Libro inserito con successo!");

        } catch (SQLException ex) {
            System.out.println("Errore durante l'inserimento del libro: " + ex.getMessage());
        }
    }


    // Metodo per selezionare i libri in base all'autore
    public static List<Libro> selezionaLibriPerAutore(String nomeAutore) {
        List<Libro> libriList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "SELECT * FROM Libro WHERE AUTORE = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomeAutore);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Libro b = new Libro(
                        rs.getString("CODICE_ISBN"),
                        rs.getString("TITOLO"),
                        rs.getString("GENERE"),
                        rs.getString("AUTORE"),
                        rs.getString("ANNO_PUBBLICAZIONE")
                );
                libriList.add(b);
            }
        } catch (SQLException ex) {
            System.out.println("Errore durante la selezione dei libri per autore: " + ex.getMessage());
        }
        return libriList;
    }


    // Metodo per inserire un nuovo noleggio nel database
    public static void inserisciNoleggio(Noleggio noleggio) {
        try {
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "INSERT INTO Noleggio (ISBN, CODICE_UTENTE, DATA_INIZIO_PRESTITO, DATA_FINE_PRESTITO) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, noleggio.getISBN());
            statement.setString(2, noleggio.getCodice_utente());
            statement.setString(3, noleggio.getData_inizio_prestito());
            statement.setString(4, noleggio.getData_fine_prestito());

            statement.executeUpdate(); // Esegui l'istruzione di inserimento

            System.out.println("Noleggio inserito con successo!");

        } catch (SQLException ex) {
            System.out.println("Errore durante l'inserimento del noleggio: " + ex.getMessage());
        }
    }

    // Metodo per selezionare i libri in base al genere
    public static List<Libro> selezionaLibriPerGenere(String genere) {
        List<Libro> libriList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "SELECT * FROM Libro WHERE GENERE = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, genere);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Libro b = new Libro(
                        rs.getString("CODICE_ISBN"),
                        rs.getString("TITOLO"),
                        rs.getString("GENERE"),
                        rs.getString("AUTORE"),
                        rs.getString("ANNO_PUBBLICAZIONE")
                );
                libriList.add(b);
            }
        } catch (SQLException ex) {
            System.out.println("Errore durante la selezione dei libri per genere: " + ex.getMessage());
        }
        return libriList;
    }


    // Metodo per recuperare i titoli dei libri noleggiati da un utente
    public static List<Libro> selezionatitoliLibriNoleggiatiDaUtente(String codiceUtente) {
        List<Libro> libriList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "SELECT * FROM Libro l JOIN utente_noleggia_libro n ON l.CODICE_ISBN = n.CODICE_ISBN WHERE n.codice_utente = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codiceUtente);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Libro b = new Libro(
                        rs.getString("CODICE_ISBN"),
                        rs.getString("TITOLO"),
                        rs.getString("GENERE"),
                        rs.getString("AUTORE"),
                        rs.getString("ANNO_PUBBLICAZIONE")
                );
                libriList.add(b);

            }
        } catch (SQLException ex) {
            System.out.println("Errore durante la selezione dei titoli dei libri noleggiati dall'utente: " + ex.getMessage());
        }
        return libriList;
    }

}
