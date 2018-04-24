package com.florent.socialnetwork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresqlImpl {



    public void GetPeopleEndorsedDepth1(String name, List<Double> timetable){
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON " +
                    "endorsements.source_node_id = Social_people.ID WHERE name='"+ name +"');");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime/1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth2(String name, List<Double> timetable){
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN" +
                    "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  " +
                    "WHERE ID IN(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID" +
                    " WHERE name='"+ name +"'));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime/1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth3(String name, List<Double> timetable){
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN " +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN" +
                    "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  WHERE ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE name='"+ name +"')));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime/1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth4(String name, List<Double> timetable){
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN" +
                    "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id WHERE ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN" +
                    "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  WHERE ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE name='"+ name +"'))));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime/1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GetPeopleEndorsedDepth5(String name, List<Double> timetable){
        Connection con = PostDBConnection.getConnection();

        try {
            long startTime = System.nanoTime();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM Social_people Where ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN" +
                    "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id WHERE ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE target_node_id IN" +
                    "(SELECT ID FROM Social_people JOIN endorsements ON Social_people.ID = endorsements.source_node_id  WHERE ID IN" +
                    "(SELECT target_node_id FROM endorsements JOIN Social_people ON endorsements.source_node_id = Social_people.ID WHERE name='"+ name +"')))));");
            long endtime = System.nanoTime() - startTime;
            timetable.add((double) endtime/1000000000.0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
