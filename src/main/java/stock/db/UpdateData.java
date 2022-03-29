package stock.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import config.MysqlConnect;

public class UpdateData {
    public void updateElectronic(int id,String name,String description,String count,String power_type) {
        Connection conn = MysqlConnect.ConnectDB();
        try {
            int intCount = Integer.parseInt(count);
            String query = ("UPDATE electronic SET name =?, description=?,count=?,power_type=?  WHERE electronic_id=?;");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,intCount);
            preparedStatement.setString(4,power_type);
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "update failed", 0);
        }
    }

    public void updateFurniture(int id,String name,String description,String count,String material) {
        Connection conn = MysqlConnect.ConnectDB();
        try {
            int intCount = Integer.parseInt(count);
            String query = ("UPDATE furniture SET name =?, description=?,count=?,material=?  WHERE furniture_id=?;");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,intCount);
            preparedStatement.setString(4,material);
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "update failed", 0);
        }
    }

   
}
