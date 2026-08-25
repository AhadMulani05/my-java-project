import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // CREATE
    public void insertStudent(String name, double marks) {

        String sql =
                "INSERT INTO student (name, marks) VALUES (?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setDouble(2, marks);

            int rows = ps.executeUpdate();

            System.out.println(rows + " student inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // READ
    public void getAllStudents() {

        String sql = "SELECT * FROM student";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double marks = rs.getDouble("marks");

                System.out.println(
                        id + " | " + name + " | " + marks
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // UPDATE
    public void updateStudent(int id, double marks) {

        String sql =
                "UPDATE student SET marks = ? WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDouble(1, marks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(rows + " student updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // DELETE
    public void deleteStudent(int id) {

        String sql =
                "DELETE FROM student WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println(rows + " student deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}