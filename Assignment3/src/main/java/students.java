
import java.sql.*;

/**
 * students class contains all the functions to perform CRUD
 * Aboderin forewa 101241161
 */
public class students{
    private static String url = "jdbc:postgresql://localhost:2244/Assignment3";
    private static String user = "postgres";
    private static String password = "gbemisola23$";

    /**
     * This method gets the students information from the student table
     */
    public static void getAllStudents() {
        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            // Create statement
            Statement stmt = conn.createStatement(); // Execute SQL query
            String SQL = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(SQL); // Process the result
            while(rs.next()){
                int student_id = rs.getInt("student_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                Date enrollment_date = rs.getDate("enrollment_date");

                System.out.println("Student_id: " + student_id +" First Name: " + first_name + ", Last Name: " + last_name + ", Email: "+ email
                        + ", Enrollment Date: " + enrollment_date);
            }
            // Close resources
            rs.close();
            stmt.close();

            // Close the connection (in a real scenario, do this in a finally
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }


    }
    /**
     * This method adds a new students information to the student table
     */

    public static void addStudent(String first_name,String last_name,String  email, Date enrollment_date) {
        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            // Create statement
            Statement stmt = conn.createStatement(); // Execute SQL query
            String insertSQL = "INSERT INTO students (first_name, last_name,email, enrollment_date) VALUES (?, ?, ?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, first_name);
                pstmt.setString(2, last_name);
                pstmt.setString(3, email);
                pstmt.setDate(4,enrollment_date);
                pstmt.executeUpdate();
                System.out.println("\n");
                System.out.println("Data inserted using PreparedStatement.");
            }

            // Close resources

            stmt.close();

            // Close the connection (in a real scenario, do this in a finally
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates the student email in the students table
     * @param student_id the id of the student
     * @param new_email the new email that will be used to update
     */
    public static void updateStudentEmail(int student_id, String new_email) {
        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            // Create statement
            Statement stmt = conn.createStatement(); // Execute SQL query
            String insertSQL = "UPDATE students SET email = ? WHERE student_id = ? ";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, new_email);
                pstmt.setInt(2, student_id);

                pstmt.executeUpdate();
                System.out.println("\n");
                System.out.println("Data Update using PreparedStatement.");
            }

            // Close resources

            stmt.close();

            // Close the connection (in a real scenario, do this in a finally
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method deletes a student from students table based on the student id

     */
    public static void deleteStudent(int studentId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM students WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("No student found with the specified ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){


      // deleteStudent(5);
        //getAllStudents();
        deleteStudent(4);
        //getAllStudents();
        //getAllStudents();
        //updateStudentEmail(4,"boldbay.beam@example.com");
        getAllStudents();
        //addStudent("Bold","Bay","ja.beam@example.com", Date.valueOf("2023-09-02"));

        //getAllStudents();

       // getAllStudents();
    }



}
