package com.line.dao;

import com.line.FileController;
import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HospitalDao {
    ConnectionMaker connectionMaker  = new ConnectionMaker();
    public void add(List<Hospital> hospitals) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO seoul_hospital(id,address,district,category," +
                "emergency_room,name,subdivision) " +
                "VALUES(?,?,?,?,?,?,?)"); //sql문 템플릿

        for(Hospital hospital : hospitals){
            ps.setString(1,hospital.getId());
            ps.setString(2, hospital.getAddress());
            ps.setString(3, hospital.getDistrict());
            ps.setString(4, hospital.getCategory());
            ps.setInt(5,hospital.getEmergencyRoom());
            ps.setString(6, hospital.getName());
            ps.setString(7,hospital.getSubdivision());
            ps.executeUpdate();
        }
        //connection끊기
        ps.close();
        conn.close();
    }

    public Hospital getById(String id) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM seoul_hospital where id = ?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Hospital hospital = new Hospital(id,rs.getString("address"), rs.getString("category"),
                rs.getInt("emergency_room") , rs.getString("name"), rs.getString("subdivision"));


        rs.close();
        ps.close();
        conn.close();

        return hospital;
    }


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        FileController<Hospital> fileController = new FileController<>(new HospitalParser());
        String filename = "C:\\Users\\user\\Downloads\\seoul_hospital_location.csv";
        //List<Hospital> hospitals = fileController.readAndParse(filename);

        HospitalDao hospitalDao = new HospitalDao();
        Hospital hospital = hospitalDao.getById("A1100001");
        System.out.printf("id : %s, name : %s, address : %s", hospital.getId(), hospital.getName(), hospital.getAddress());

    }
}
