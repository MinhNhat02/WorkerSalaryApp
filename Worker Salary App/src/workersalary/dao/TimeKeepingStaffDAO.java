package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import workersalary.entity.implement.TimeKeepingStaff;
import workersalary.util.DBConnection;

public class TimeKeepingStaffDAO {

    private String TABLE_NAME = "TimeKeepingStaff";
    private String ATTR_1 = "timeKeepingId";

    public TimeKeepingStaff getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                TimeKeepingStaff timeKeepingStaff = new TimeKeepingStaff();
                return timeKeepingStaff;
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

    public boolean add(String id) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, id);
            if (check > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }
    public boolean delete(String id) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Delete From " + TABLE_NAME + " "
                    + "WHERE " + ATTR_1 + " = ?;", id);
            if (check > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }
}
