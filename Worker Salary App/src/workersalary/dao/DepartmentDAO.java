package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import workersalary.entity.Department;
import workersalary.util.DBConnection;

public class DepartmentDAO {

    private String TABLE_NAME = "Department";
    private String ATTR_1 = "id";
    private String ATTR_2 = "name";
    
    public List<Department> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Department> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String name = rs.getString(ATTR_2);
                list.add(new Department(id, name));
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
    
    public Department getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String name = rs.getString(ATTR_2);
                return new Department(id, name);
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
