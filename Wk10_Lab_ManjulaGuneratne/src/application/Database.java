package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	  //private static final Logger logger = Logger.getLogger(Database.class.getName());
		private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		private static final String DB_CONNECTION = "jdbc:mysql://localhost/wk10_labwork_manjulaguneratne";
		private static final String DB_USER = "root";
		private static final String DB_PASSWORD = "admin";
		
		private Database() {
			
		}
		
		public static Connection getDBConnection() {
			Connection connection = null;

			try {
				Class.forName(DB_DRIVER);
			} catch (ClassNotFoundException ex) {
				System.out.println(ex.getStackTrace());
				System.out.println(ex.getMessage());
				
		//		logger.log(Level.SEVERE, exception.getMessage());
			}

			try {
				connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				System.out.println("Connection Established successfully");
				return connection;
			} catch (SQLException ex) {
				System.out.println(ex.getSQLState());
				System.out.println(ex.getMessage());
			//	logger.log(Level.SEVERE, exception.getMessage());
			}

			return connection;
		}
		
	}