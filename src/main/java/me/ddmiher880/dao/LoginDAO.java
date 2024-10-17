package me.ddmiher880.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.ddmiher880.config.SQLConnection;

public class LoginDAO {

  private PreparedStatement ps;
  private String query;

  public boolean authenticate(String username, String password) throws SQLException {

    boolean success = false;

    try (Connection conn = SQLConnection.getConnection()) {
      query = "SELECT * FROM auth_user WHERE username=? AND password=?";
      ps = conn.prepareStatement(query);
      ps.setString(1, username);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) success = true;
    }
    
    return success;

  }

  public String getAlias(String username, String password) throws SQLException {

    try (Connection conn = SQLConnection.getConnection()) {
      query = "SELECT alias FROM auth_user WHERE username=? AND password=?";
      ps = conn.prepareStatement(query);
      ps.setString(1, username);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) return rs.getString(1);
    }
    
    return null;

  }
  
}
