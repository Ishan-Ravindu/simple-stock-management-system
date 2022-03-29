package auth.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import config.MysqlConnect;

public class Login {
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean signIn() {
        try {
            Connection conn = MysqlConnect.ConnectDB();
            PreparedStatement st = (PreparedStatement)conn.prepareStatement("Select * from user where email=? and password=?");
            st.setString(1, this.email);
            st.setString(2, this.password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("db fail");
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
