package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileController<Hospital>  fileController = new FileController<>(new HospitalParser());
        String filename = "C:\\Users\\user\\Downloads\\seoul_hospital_location.csv";
        List<Hospital> hospitals = fileController.readAndParse(filename);

        List<String> sqlStatements = new ArrayList<>();
        for(Hospital hospital : hospitals){
            sqlStatements.add(hospital.getSqlInsertQuery());
        }
        String queryFile = "./insert.sql";
        fileController.createAFile(queryFile);
        fileController.write(queryFile, sqlStatements);

        System.out.println(hospitals.size());

    }
}
