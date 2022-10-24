package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private final JdbcContext jdbcContext;

    private final DataSource dataSource;

    //Constructor에서 초기화
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }


    public void deleteAll() throws SQLException{
        //쿼리만 넘김
        this.jdbcContext.executeSQL("DELETE from users");
    }

    public int getCount() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            ps = conn.prepareStatement("SELECT count(*) FROM users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //close
            //close()는 만들어진 순서 반대로
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void add(User user) throws SQLException, ClassNotFoundException, IOException {
        jdbcContext.workWithStatementStrategy(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users(id,name,password) values(?,?,?)"); //sql문 템플릿
            ps.setString(1,user.getId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            return ps;
        });
    }

    public User getById(String id) throws ClassNotFoundException, SQLException {
        //환경변수로 DB 설정(보안위해)
        Connection conn = dataSource.getConnection();


        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement ps = conn.prepareStatement("SELECT * from users where id = ?"); //sql문 템플릿
        ps.setString(1,id);

        ResultSet resultSet = ps.executeQuery();
        User user = null;
        if(resultSet.next()){
            user = new User(resultSet.getString("id"),resultSet.getString("name"),
                    resultSet.getString("password"));
        }

        //close
        resultSet.close();
        ps.close();
        conn.close();

        if(user == null){
            throw new EmptyResultDataAccessException(1);
        }
        return user;
    }

    public List<User> findAll() throws ClassNotFoundException, SQLException {
        //환경변수로 DB 설정(보안위해)

        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * from users"); //sql문 템플릿

        List<User> userList = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            User user = new User(resultSet.getString("id"),resultSet.getString("name"),
                    resultSet.getString("password"));
            userList.add(user);
        }

        resultSet.close();
        //connection끊기
        ps.close();
        conn.close();
        return userList;
    }


}
