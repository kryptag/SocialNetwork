package com.florent.socialnetwork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresqlImpl {

    public void GetPeopleEndorsedDepth1(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON "
                    + "endorsements.source_node_id = Social_people.ID WHERE name='" + name + "');");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth2(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN"
                    + "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  "
                    + "WHERE ID IN(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID"
                    + " WHERE name='" + name + "'));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth3(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN "
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN"
                    + "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  WHERE ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE name='" + name + "')));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth4(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN"
                    + "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id WHERE ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN"
                    + "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  WHERE ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE name='" + name + "'))));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth5(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN"
                    + "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id WHERE ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN"
                    + "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  WHERE ID IN"
                    + "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE name='" + name + "')))));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetBooksByCity(String name, List<Double> timetable) throws SQLException {
        Connection con = PostDBConnection.getConnection();
        con.createStatement().execute("SET search_path TO gutenberg;");
        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT DISTINCT ON (books.book_id) books.book_id, title FROM books "
                    + "NATURAL JOIN books_cities "
                    + "NATURAL JOIN cities "
                    + "WHERE cities.name = 'Copenhagen';");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetAllcitiesInBook(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT name FROM cities "
                    + "NATURAL JOIN books_cities "
                    + "NATURAL JOIN books "
                    + "WHERE books.title = 'Morals and the Evolution of Man';");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetBooksAndCitiesByAuthor(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM books NATURAL JOIN authors_books "
                    + "NATURAL JOIN books_cities "
                    + "NATURAL JOIN cities "
                    + "WHERE author_id IN (SELECT author_id FROM authors WHERE name = 'Maxime Provost');");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetAllBooksWrittenNearby(String name, List<Double> timetable) {
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT DISTINCT ON (books.book_id) title, books.book_id FROM books "
                    + "JOIN books_cities ON (books.book_id = books_cities.book_id) "
                    + "JOIN cities ON (books_cities.latitude = cities.latitude AND books_cities.longitude = cities.longitude) "
                    + "GROUP BY (title, books.book_id, cities.latitude, cities.longitude) "
                    + "HAVING geodistance(cities.latitude, cities.longitude, ( 55.675940 ), ( 12.565530 )) <= 20;");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime / 1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
