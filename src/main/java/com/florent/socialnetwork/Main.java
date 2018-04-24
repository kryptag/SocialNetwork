package com.florent.socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        NEO4JImpl neo = new NEO4JImpl();
        PostgresqlImpl pos = new PostgresqlImpl();
        List<String> names = neo.get20Random();
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




        names.forEach((name) -> { neo.getPeopleThatEndorse(name, ":ENDORSES" , timetable1); });

        System.out.println("----------------------------------------------------------------");

        System.out.println("all persons that a person endorses, i.e., endorsements of depth one.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable1));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable1));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name)->{ neo.getPeopleThatEndorse(name,":ENDORSES*2" , timetable2);});

        System.out.println("all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable2));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable2));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name)->{ neo.getPeopleThatEndorse(name,":ENDORSES*3" , timetable3);});

        System.out.println("endorsements of depth three.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable3));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable3));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name)->{ neo.getPeopleThatEndorse(name,":ENDORSES*4" , timetable4);});

        System.out.println("endorsements of depth four.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable4));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable4));

        System.out.println("----------------------------------------------------------------");

        //names.forEach((name)->{ neo.getPeopleThatEndorse(name,":ENDORSES*5" , timetable5);});

        //System.out.println("endorsements of depth five.");
        //System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable5));
        //System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable5));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name) -> { pos.GetPeopleEndorsedDepth1(name, timetable6);});

        System.out.println("all persons that a person endorses, i.e., endorsements of depth one.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable6));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable6));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name) -> { pos.GetPeopleEndorsedDepth2(name, timetable7);});

        System.out.println("all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable7));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable7));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name) -> { pos.GetPeopleEndorsedDepth3(name, timetable8);});

        System.out.println("endorsements of depth three.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable8));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable8));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name) -> { pos.GetPeopleEndorsedDepth4(name, timetable9);});

        System.out.println("endorsements of depth four.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable9));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable9));

        System.out.println("----------------------------------------------------------------");

        names.forEach((name) -> { pos.GetPeopleEndorsedDepth5(name, timetable10);});

        System.out.println("endorsements of depth five.");
        System.out.println("Average time in seconds for this Query: " + neo.getAverage(timetable10));
        System.out.println("Median time in seconds for this Query: " + neo.getMedian(timetable10));


    }
}
