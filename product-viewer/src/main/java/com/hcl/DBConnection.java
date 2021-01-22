package com.hcl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Connection connection;

    public DBConnection() {
        String propFileName = "config.properties";
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(propFileName);){
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = new Properties();
            props.load(in);
            this.connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        if (this.connection != null)
            this.connection.close();
    }
}
