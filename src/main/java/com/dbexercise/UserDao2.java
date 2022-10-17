package com.dbexercise;

import com.dbexercise.domain.User;
import com.line.FileController;
import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao2 {


    public void add() throws SQLException, ClassNotFoundException, IOException {
        //환경변수로 DB 설정(보안위해)
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);  //db연동
        PreparedStatement ps = conn.prepareStatement("INSERT INTO seoul_hospital(id,address,district,category,emergency_room,name,subdivision) VALUES(?,?,?,?,?,?,?)"); //sql문 템플릿
        FileController<Hospital> fileController = new FileController<>(new HospitalParser());
        String filename = "C:\\Users\\user\\Downloads\\seoul_hospital_location.csv";
        List<Hospital> hospitals = fileController.readAndParse(filename);

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

    public User getById(String id) throws ClassNotFoundException, SQLException {
        //환경변수로 DB 설정(보안위해)
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);  //db연동
        PreparedStatement ps = conn.prepareStatement("SELECT * from users where id = ?"); //sql문 템플릿
        ps.setString(1,id);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        User user = new User(resultSet.getString("id"),resultSet.getString("name"),
                resultSet.getString("password"));

        resultSet.close();
        //connection끊기
        ps.close();
        conn.close();
        return user;
    }




    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        UserDao2 userDao2 = new UserDao2();
        User user = userDao2.getById("1");
        System.out.printf("id : %s, name : %s, password : %s", user.getId(),user.getName(), user.getPassword());
    }
}
