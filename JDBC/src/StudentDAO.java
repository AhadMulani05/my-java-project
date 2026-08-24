import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public void getAllStudents() {

        String sql = "SELECT * FROM student";

        try (
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double marks = rs.getDouble("marks");

                System.out.println(
                        id + " " + name + " " + marks
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}