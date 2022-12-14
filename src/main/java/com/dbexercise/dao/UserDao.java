package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private final JdbcTemplate jdbcTemplate;


    //Constructor에서 초기화
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void deleteAll() {
        //쿼리만 넘김
        this.jdbcTemplate.update("DELETE from users");
    }

    public int getCount() {
     return this.jdbcTemplate.queryForObject("SELECT count(*) from users", Integer.class);
    }

    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO users(id,name,password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }
    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            return user;
        }
    };

    public User getById(String id){

        return this.jdbcTemplate.queryForObject("SELECT * from users where id = ?",rowMapper,id);
    }

    public List<User> findAll(){
        return this.jdbcTemplate.query("SELECT * from users",rowMapper);
    }


}
