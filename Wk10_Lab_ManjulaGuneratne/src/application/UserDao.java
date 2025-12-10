package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public int saveUser(User user) throws SQLException {  // Consider throwing SQLException instead of swallowing
        try (Connection connection = Database.getDBConnection()) {
            connection.setAutoCommit(false);
            
            String query1 = "INSERT INTO Application(full_name, current_address, contact_number, email_address, highest_education, gender) "
                          + "VALUES(?, ?, ?, ?, ?, ?)";
            
            String query2 = "INSERT INTO Employment(application_id, date_available, desired_pos, desired_salary, legal_work_auth, rel_working_here, further_explanation) "
                          + "VALUES(?, ?, ?, ?, ?, ?, ?)";  // Added application_id as first param
            
            try (PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statement2 = connection.prepareStatement(query2, Statement.NO_GENERATED_KEYS)) {  // No keys needed for stmt2
                
                // Insert into Application
                int counter = 1;
                statement1.setString(counter++, user.getFullName());
                statement1.setString(counter++, user.getCurrentAddress());
                statement1.setString(counter++, user.getContactNumber());
                statement1.setString(counter++, user.getEmailAddress());
                statement1.setString(counter++, user.getHighestEducation());
                statement1.setString(counter++, user.getGender());
                
                int rowsAffected1 = statement1.executeUpdate();
                if (rowsAffected1 != 1) {
                    throw new SQLException("Failed to insert Application record");
                }
                
                // Get generated Application ID
                try (ResultSet rs = statement1.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("No ID generated for Application");
                    }
                    int applicationId = rs.getInt(1);
                    
                    // Insert into Employment using the ID
                    counter = 1;
                    statement2.setInt(counter++, applicationId);  // Set FK first
                    statement2.setString(counter++, user.getDateAvailable());
                    statement2.setString(counter++, user.getDesiredPos());
                    statement2.setString(counter++, user.getDesiredSalary());
                    statement2.setString(counter++, user.getLegalWorkAuth());
                    statement2.setString(counter++, user.getRelWorkingHere());
                    statement2.setString(counter++, user.getFurtherExplanation());
                    
                    int rowsAffected2 = statement2.executeUpdate();
                    if (rowsAffected2 != 1) {
                        throw new SQLException("Failed to insert Employment record");
                    }
                    
                    connection.commit();
                    return applicationId;  // Return the main record's ID
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;  // Rethrow for caller to handle
            }
        } catch (SQLException e) {
            // Log e (e.g., via logger)
            e.printStackTrace();
            throw e;  // Or return -1; adjust based on your API
        }
    }
}