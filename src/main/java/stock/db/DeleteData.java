package stock.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import config.MysqlConnect;

public class DeleteData {
    public void deleteElectronic(int id) {
        Connection conn = MysqlConnect.ConnectDB();
        try {
            Statement st = conn.createStatement();
            String query = ("delete from electronic where electronic_id ='" + id + "'");
            st.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "delete failed", 0);
        }
    }

    public void deleteFurniture(int id) {
        Connection conn = MysqlConnect.ConnectDB();
        try {
            Statement st = conn.createStatement();
            String query = ("delete from furniture where furniture_id ='" + id + "'");
            st.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "delete failed", 0);
        }
    }
}
