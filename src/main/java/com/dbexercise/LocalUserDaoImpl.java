package com.dbexercise;

import java.sql.Connection;
import java.sql.SQLException;

public class LocalUserDaoImpl extends UserDaoAbstract{
    @Override
    public Connection makeConnection() throws SQLException {
        return null;
    }
}
