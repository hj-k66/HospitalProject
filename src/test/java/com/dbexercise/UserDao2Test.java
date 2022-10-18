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
        UserDao2 userDao2 = new UserDao2();
        User user = new User("8","ko","okokok");
        userDao2.add(user);

        User selectedUser = userDao2.getById("8");
        Assertions.assertEquals("ko", selectedUser.getName());
    }
}