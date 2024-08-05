package workersalary.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import workersalary.entity.ShiftWork;
import workersalary.util.DBConnection;

public class ShiftWorkDAO {

    private String TABLE_NAME = "ShiftWork";
    private String ATTR_1 = "id";
    private String ATTR_2 = "name";
    private String ATTR_3 = "startTime";
    private String ATTR_4 = "endTime";
    private String ATTR_5 = "salary";

    public List<ShiftWork> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<ShiftWork> list = new ArrayList();
        try {

            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String name = rs.getString(ATTR_2);
                Timestamp startTime = rs.getTimestamp(ATTR_3);
                Timestamp endTime = rs.getTimestamp(ATTR_4);
                double salary = rs.getDouble(ATTR_5);
                list.add(new ShiftWork(id, name, startTime, endTime, salary));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            dbConn.closeConnection();
        }
        return null;
    }

    public ShiftWork getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String name = rs.getString(ATTR_2);
                Timestamp startTime = rs.getTimestamp(ATTR_3);
                Timestamp endTime = rs.getTimestamp(ATTR_4);
                double salary = rs.getDouble(ATTR_5);
                return new ShiftWork(id, name, startTime, endTime, salary);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            dbConn.closeConnection();
        }
        return null;
    }
}
