package com.studi.kasus.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.studi.kasus.mysql.utils.ConnectionUtil;

public class ConnectionTest {
    
    @Test
    void testConnectionUtil() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}
