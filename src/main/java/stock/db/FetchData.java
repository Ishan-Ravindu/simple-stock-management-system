package stock.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MysqlConnect;

public class FetchData {

    public static ResultSet getFurnitureDataForTable(){
        Connection conn = MysqlConnect.ConnectDB();
        try {
            PreparedStatement st = (PreparedStatement)conn.prepareStatement("Select * from furniture");
            ResultSet rs = st.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
       
    }


    public static ResultSet getElectronicDataForTable(){
        Connection conn = MysqlConnect.ConnectDB();
        try {
            PreparedStatement st = (PreparedStatement)conn.prepareStatement("Select * from electronic");
            ResultSet rs = st.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
