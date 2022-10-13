package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> hospitalLineReader = new LineReader<>(new HospitalParser());
        String filename = "C:\\Users\\user\\Downloads\\seoul_hospital_location.csv";
        List<Hospital> hospitals = hospitalLineReader.readAndParse(filename);

        System.out.println(hospitals.size());
        for(Hospital hospital : hospitals){
            System.out.println(hospital.getSqlInsertQuery());
        }
    }
}
