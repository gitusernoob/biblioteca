package com.biblioteca.repository;

import com.biblioteca.constants.DbConfig;
import com.biblioteca.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository {

    public static List<Libro> selezionaLibri()
    {
        List<Libro> libriList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PPW);
            String query = "SELECT * FROM BIBLIOTECA";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Libro b = new Libro(
                        rs.getString("CODICE_ISBN"),
                        rs.getString("TITOLO"),
                        rs.getString("GENERE"),
                        rs.getString("ANNO_PUBBLICAZIONE"),
                        rs.getString("AUTORE")
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
            String query = "INSERT INTO BIBLIOTECA (CODICE_ISBN, TITOLO, GENERE, ANNO_PUBBLICAZIONE, AUTORE) " +
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

}
