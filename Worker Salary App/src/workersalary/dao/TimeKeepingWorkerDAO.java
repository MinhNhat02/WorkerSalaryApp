package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import workersalary.entity.implement.TimeKeepingWorker;
import workersalary.util.DBConnection;

public class TimeKeepingWorkerDAO {

    private String TABLE_NAME = "TimeKeepingWorker";
    private String ATTR_1 = "timeKeepingId";
    private String ATTR_2 = "productId";
    private String ATTR_3 = "productionStageId";
    private String ATTR_4 = "assignmentId";
    private ProductDAO productDAO = new ProductDAO();
    private ProductionStageDAO productionStageDAO = new ProductionStageDAO();
    private AssignmentDAO assignmentDAO = new AssignmentDAO();
    
    public TimeKeepingWorker getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String productId = rs.getString(ATTR_2);
                String productionStageId = rs.getString(ATTR_3);
                String assignmentId = rs.getString(ATTR_4);
                TimeKeepingWorker timeKeepingWorker = new TimeKeepingWorker();
                timeKeepingWorker.setProduct(productDAO.getById(productId));
                timeKeepingWorker.setStage(productionStageDAO.getById(productionStageId));
                timeKeepingWorker.setAssignment(assignmentDAO.getById(assignmentId));
                return timeKeepingWorker;
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

    public boolean add(TimeKeepingWorker timeKeepingWorker) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, timeKeepingWorker.getId(), 
                    timeKeepingWorker.getProduct().getId(), timeKeepingWorker.getStage().getId(),
                    timeKeepingWorker.getAssignment().getId());
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

    public boolean update(TimeKeepingWorker timeKeepingWorker) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," 
                    + ATTR_3 + " = ? ," + ATTR_4 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", 
                    timeKeepingWorker.getProduct().getId(), timeKeepingWorker.getStage().getId(),
                    timeKeepingWorker.getAssignment().getId(), timeKeepingWorker.getId());
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
