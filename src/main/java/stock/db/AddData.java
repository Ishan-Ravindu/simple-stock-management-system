package stock.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import config.MysqlConnect;

public class AddData {
    public int addElectronic(String name, String description, String count, String power_type) {
        Connection conn = MysqlConnect.ConnectDB();
        try {
            Statement st = conn.createStatement();
            int intCount = Integer.parseInt(count); // Mark is an integer
            String query = ("INSERT INTO electronic(name,description,count,power_type) VALUES('" + name + "','"
                    + description + "'," + intCount + ",'" + power_type + "');");
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs= st.getGeneratedKeys();
            if (rs.next()) 
            {
              return rs.getInt(1);
            }
            conn.close();
            return 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "adding failed", 0);
            return 0;
        }
    }

    public int addFurniture(String name, String description, String count, String material) {
        Connection conn = MysqlConnect.ConnectDB();
        try {
            Statement st = conn.createStatement();
            int intCount = Integer.parseInt(count); // Mark is an integer
            String query = ("INSERT INTO furniture(name,description,count,material) VALUES('" + name + "','"
                    + description + "'," + intCount + ",'" + material + "');");
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs= st.getGeneratedKeys();
            if (rs.next()) 
            {
              return rs.getInt(1);
            }
            conn.close();
            return 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "adding failed", 0);
            return 0;
        }
    }
}
