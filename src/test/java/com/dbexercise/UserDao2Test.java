package com.dbexercise;

import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDao2Test {
    @Test
    void addAndSelect() throws SQLException, IOException, ClassNotFoundException {
        AWSUserDaoImpl userDao2 = new AWSUserDaoImpl();
        User user = new User("10","ko","okokok");
        userDao2.add(user);

        User selectedUser = userDao2.getById("10");
        Assertions.assertEquals("ko", selectedUser.getName());
    }
}