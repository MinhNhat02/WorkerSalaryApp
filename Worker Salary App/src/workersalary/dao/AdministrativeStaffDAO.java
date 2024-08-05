package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import workersalary.entity.implement.AdministrativeStaff;
import workersalary.util.DBConnection;

public class AdministrativeStaffDAO {

    private String TABLE_NAME = "Administrative_Staff";
    private String ATTR_1 = "employeeId";
    private String ATTR_2 = "basicSalary";

    public AdministrativeStaff getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                double basicSalary = rs.getDouble(ATTR_2);
                AdministrativeStaff staff = new AdministrativeStaff();
                staff.setBasicSalary(basicSalary);
                return staff;
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

    public boolean add(AdministrativeStaff staff) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, staff.getId(), staff.getBasicSalary());
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

    public boolean update(AdministrativeStaff employee) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;",employee.getBasicSalary(), employee.getId());
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
