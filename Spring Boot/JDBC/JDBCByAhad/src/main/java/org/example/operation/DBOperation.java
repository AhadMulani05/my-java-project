package org.example.operation;

import org.example.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {

    private DBConnection dbConnection;

    //insert
    public void insertStudent(String name, double marks) {
        String sql = "INSERT INTO student(name, marks) VALUES (?, ?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setString(1, name);
            ps.setDouble(2, marks);

            int result = ps.executeUpdate();

            System.out.println(result + "inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllStudents() {
        String sql = "SELECT * FROM student";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double marks = rs.getDouble("marks");

                System.out.println(id + " | " + name + " | " + marks);
            }
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public void updateStudent(int id, double marks) {
        String sql = "UPDATE student SET marks = ? WHERE id = ?";

        try ( Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setDouble(1, marks);
            ps.setInt(2, id);

            int result = ps.executeUpdate();

            System.out.println(result+"updated");
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try(Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int result = ps.executeUpdate();

            System.out.println(result + "deleted");
        }
        catch (Exception e) {e.printStackTrace();}
    }

}
