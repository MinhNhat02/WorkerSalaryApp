package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import workersalary.entity.Assignment;
import workersalary.entity.implement.Worker;
import workersalary.util.DBConnection;

public class AssignmentDAO {

    private String TABLE_NAME = "Assignment";
    private String ATTR_1 = "id";
    private String ATTR_2 = "workerId";
    private String ATTR_3 = "finishAmount";
    private String ATTR_4 = "productionStageId";
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private ProductionStageDAO productionDAO = new ProductionStageDAO();

    public List<Assignment> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Assignment> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String workerId = rs.getString(ATTR_2);
                int finishAmount = rs.getInt(ATTR_3);
                String productionStageId = rs.getString(ATTR_4);
                list.add(new Assignment(id, (Worker) employeeDAO.getById(workerId), productionDAO.getById(productionStageId), finishAmount));
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

    public Assignment getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String workerId = rs.getString(ATTR_2);
                int finishAmount = rs.getInt(ATTR_3);
                String productionStageId = rs.getString(ATTR_4);
                return new Assignment(id, (Worker) employeeDAO.getById(workerId), productionDAO.getById(productionStageId), finishAmount);
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

    public boolean add(Assignment assignment) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, assignment.getId(), assignment.getWorker().getId(), 
                    assignment.getFinishAmount(), assignment.getStage().getId());
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

    public boolean update(Assignment product) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", product.getWorker().getId(),
                    product.getFinishAmount(), product.getStage().getId(), product.getId());
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
