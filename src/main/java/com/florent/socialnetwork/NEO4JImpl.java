package com.florent.socialnetwork;

import org.neo4j.driver.v1.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NEO4JImpl {

    public Double getAverage(List<Double> timetable) {
        Double avg = 0.0;
        for (int i = 0; i < timetable.size(); i++) {
            avg += timetable.get(i);
        }

        return avg / timetable.size();
    }

    public Double getMedian(List<Double> timetable) {
        int median = timetable.size() / 2;
        median = median % 2 == 0 ? median - 1 : median;
        return timetable.get(median);
    }

    public void CountDataBase() {
        // Check if we use the right dataset result should be 500000
        Driver driver = DBConnection.getInstance();
        Session session = driver.session();

        StatementResult res = driver.session().run("MATCH(n) RETURN COUNT(n)");
        System.out.println(res.single());

        DBConnection.closeDriver();

    }

    public List get20Random() {
        Driver driver = DBConnection.getInstance();
        Session session = driver.session();

        List<String> names = new ArrayList<>();
        StatementResult res = session.run("MATCH(a:Person) WITH a, rand() AS number "
                + "RETURN a.name as name ORDER BY number LIMIT 20");

        while (res.hasNext()) {
            Record record = res.next();
            names.add(record.get("name").asString());
        }

        DBConnection.closeDriver();

        return names;
    }

    public void getPeopleThatEndorse(String name, String depth, List<Double> timetable) {
        Driver driver = DBConnection.getInstance();

        Session session = driver.session();
        String query = String.format("MATCH (:Person {name:\"%s\"})-[%s]->(p:Person) RETURN p", name, depth);
        StatementResult res = session.run(query);
        timetable.add((double) res.summary().resultConsumedAfter(TimeUnit.MILLISECONDS) / 1000.0);

        DBConnection.closeDriver();

    }

    public void getBooksByCity(List<Double> timetable) {
        Driver driver = DBConnection.getInstance();

        Session session = driver.session();
        String query = String.format("MATCH (book:Book)-[r:CONTAINS]->(city:City {name: Copenhagen}) RETURN book,r,city;");
        StatementResult res = session.run(query);
        timetable.add((double) res.summary().resultConsumedAfter(TimeUnit.MILLISECONDS) / 1000.0);

        DBConnection.closeDriver();

    }

    public void getAllCitiesInBook(List<Double> timetable) {
        Driver driver = DBConnection.getInstance();

        Session session = driver.session();
        String query = String.format("MATCH (:Book{title: Morals and the Evolution of Man)-[:CONTAINS]-(city:City) RETURN city");
        StatementResult res = session.run(query);
        timetable.add((double) res.summary().resultConsumedAfter(TimeUnit.MILLISECONDS) / 1000.0);

        DBConnection.closeDriver();

    }

    public void getBooksAndCitiesByAuthor(List<Double> timetable) {
        Driver driver = DBConnection.getInstance();

        Session session = driver.session();
        String query = String.format("MATCH (:Author{name: Maxime Provost)-[:WRITTEN_BY]-(book:Book)-[:CONTAINS]-(city:City) RETURN book.title, city");
        StatementResult res = session.run(query);
        timetable.add((double) res.summary().resultConsumedAfter(TimeUnit.MILLISECONDS) / 1000.0);

        DBConnection.closeDriver();

    }

    public void getAllBooksWrittenNearby(List<Double> timetable) {
        Driver driver = DBConnection.getInstance();

        Session session = driver.session();
        String query = String.format("WITH {55.675940} AS lat, {12.565530} AS lon\n" +
            "MATCH (l:City) \n" +
            "WHERE 2 * 6371 * asin(sqrt(haversin(radians(lat - toFloat(split(l.location, \",\")[0])))+ cos(radians(lat))* cos(radians(toFloat(split(l.location, \",\")[0])))* haversin(radians(lon - toFloat(split(l.location, \",\")[1]))))) < 20\n" +
            "MATCH (l)-[:CONTAINS]-(book:Book)\n" +
            "RETURN l");
        StatementResult res = session.run(query);
        timetable.add((double) res.summary().resultConsumedAfter(TimeUnit.MILLISECONDS) / 1000.0);

        DBConnection.closeDriver();

    }
}
