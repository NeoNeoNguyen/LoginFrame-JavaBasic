package loginform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;

    public Student checkLogin(String uname) {
        Student student = new Student();
        con = ConnectionFactory.getConnection();
        try {
            String query = "select* from student where name=?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            while (rs.next()) {
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setUname(rs.getString("mobile_no"));
                student.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }
}
