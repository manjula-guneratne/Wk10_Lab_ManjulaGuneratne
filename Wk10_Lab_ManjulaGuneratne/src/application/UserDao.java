package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	public int saveUser(User user) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	        connection = Database.getDBConnection();
	        connection.setAutoCommit(false);

	        // Updated SQL to match your User fields
	        String query = "INSERT INTO user(full_name, current_address, contact_number, email_address, highest_education, gender, date_available, desired_pos, desired_salary, legal_work_auth, rel_working_here, further_explanation) "
	                     + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        int counter = 1;

	        statement.setString(counter++, user.getFullName());
	        statement.setString(counter++, user.getCurrentAddress());
	        statement.setString(counter++, user.getContactNumber());
	        statement.setString(counter++, user.getEmailAddress());
	        statement.setString(counter++, user.getHighestEducation());
	        statement.setString(counter++, user.getGender());
	        statement.setString(counter++, user.getDateAvailable()); // already a string
	        statement.setString(counter++, user.getDesiredPos());
	        statement.setString(counter++, user.getDesiredSalary());
	        statement.setString(counter++, user.getLegalWorkAuth());
	        statement.setString(counter++, user.getRelWorkingHere());
	        statement.setString(counter++, user.getFurtherExplanation());

	        statement.executeUpdate();
	        connection.commit();

	        resultSet = statement.getGeneratedKeys();
	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	    } catch (SQLException exception) {
	        if (connection != null) {
	            connection.rollback();
	        }
	        exception.printStackTrace();
	    } finally {
	        if (resultSet != null) resultSet.close();
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }

	    return 0;
	}

}
