package com.florent.socialnetwork;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        NEO4JImpl neo = new NEO4JImpl();
        PostgresqlImpl pos = new PostgresqlImpl();
        //List<String> names = neo.get20Random();
        List<Double> timetable1 = new ArrayList<>();
        List<Double> timetable2 = new ArrayList<>();
        List<Double> timetable3 = new ArrayList<>();
        List<Double> timetable4 = new ArrayList<>();
        List<Double> timetable5 = new ArrayList<>();
        List<Double> timetable6 = new ArrayList<>();
        List<Double> timetable7 = new ArrayList<>();
        List<Double> timetable8 = new ArrayList<>();
        List<Double> timetable9 = new ArrayList<>();
        List<Double> timetable10 = new ArrayList<>();

        System.out.println("NEO4J QUERIES");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 1000; i++) {
            neo.getBooksByCity(timetable1);
        }
        System.out.println("Get all books that mention Copenhagen");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable1));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable1));
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 1000; i++) {
            neo.getAllCitiesInBook(timetable2);
        }
        System.out.println("get all cities mentioned in a specific book");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable2));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable2));
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 1000; i++) {
            neo.getBooksAndCitiesByAuthor(timetable3);
        }
        System.out.println("Get all books and the cities they mention by author name");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable3));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable3));
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 1000; i++) {
            neo.getAllBooksWrittenNearby(timetable4);
        }
        System.out.println("Get all books that mention a city Nearby");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable4));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable4));
        System.out.println("----------------------------------------------------------------");

        System.out.println("POSTGRESQL QUERIES");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < 1000; i++) {
            pos.GetBooksByCity("hejsa", timetable6);
        }

        System.out.println("Get all books that mention Copenhagen");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable6));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable6));

        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < 1000; i++) {
            pos.GetAllcitiesInBook("hejsa", timetable7);
        }

        System.out.println("get all cities mentioned in a specific book");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable7));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable7));

        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < 1000; i++) {
            pos.GetBooksAndCitiesByAuthor("hejsa", timetable8);
        }

        System.out.println("Get all books and the cities they mention by author name");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable8));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable8));

        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < 1000; i++) {
            pos.GetAllBooksWrittenNearby("hejsa", timetable9);
        }

        System.out.println("Get all books that mention a city Nearby");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable9));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable9));

        System.out.println("----------------------------------------------------------------");

        //names.forEach((name) -> { pos.GetPeopleEndorsedDepth5(name, timetable10);});
        //System.out.println("endorsements of depth five.");
        //System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable10));
        //System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable10));
    }
}
