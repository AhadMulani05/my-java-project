import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean signup(User user) {

        String sql =
                "INSERT INTO users (name, email, password) " +
                "VALUES (?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public User login(String email, String password) {

    String sql =
            "SELECT * FROM users " +
            "WHERE email = ? AND password = ?";

    try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)
    ) {

        ps.setString(1, email);
        ps.setString(2, password);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return null;
    }
}