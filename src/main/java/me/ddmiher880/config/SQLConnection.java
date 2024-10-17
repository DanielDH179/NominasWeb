package me.ddmiher880.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class SQLConnection {
	
	private static BasicDataSource ds;

  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "123456";
  private static final String HOST = "localhost";
  private static final short PORT = 3306;
  private static final String DATABASE = "DWES_NominasWeb";
  private static final String TIMEZONE = "?useTimezone=true&serverTimezone=UTC";
  private static final String URL = String.format("jdbc:mysql://%s:%d/%s%s", HOST, PORT, DATABASE, TIMEZONE);

  /**
   * Configura el DataSource si es nulo y lo devuelve.
   * 
   * @return DataSource configurado
   */
  private static DataSource getDataSource() {
		if (ds == null) {
			ds = new BasicDataSource();
			ds.setDriverClassName(DRIVER);
			ds.setUsername(USERNAME);
			ds.setPassword(PASSWORD);
			ds.setUrl(URL);
			ds.setInitialSize(20);
			ds.setMaxIdle(15);
			ds.setMaxTotal(20);
			ds.setMaxConn(Duration.ofSeconds(5));
		}
		return ds;
	}
	
  /**
   * Devuelve una conexión a la base de datos.
   * 
   * @return conexión a la base de datos
   * @throws SQLException si ocurre un error de conexión
   */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
	
}
