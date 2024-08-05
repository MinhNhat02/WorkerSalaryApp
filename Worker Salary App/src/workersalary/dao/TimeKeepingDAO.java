package workersalary.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import workersalary.entity.TimeKeeping;
import workersalary.entity.implement.TimeKeepingStaff;
import workersalary.entity.implement.TimeKeepingWorker;
import workersalary.util.DBConnection;

public class TimeKeepingDAO {

    private String TABLE_NAME = "TimeKeeping";
    private String ATTR_1 = "id";
    private String ATTR_2 = "employeeId";
    private String ATTR_3 = "createdDate";
    private String ATTR_4 = "shiftWorkId";
    private String ATTR_5 = "status";
    private String ATTR_6 = "leavePermission";
    private ShiftWorkDAO shiftWorkDAO = new ShiftWorkDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private TimeKeepingStaffDAO timeKeepingStaffDAO = new TimeKeepingStaffDAO();
    private TimeKeepingWorkerDAO timeKeepingWorkerDAO = new TimeKeepingWorkerDAO();

    public List<TimeKeeping> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<TimeKeeping> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String employeeId = rs.getString(ATTR_2);
                Date createdDate = rs.getDate(ATTR_3);
                String shiftWorkId = rs.getString(ATTR_4);
                String status = rs.getString(ATTR_5);
                boolean leavePermission = rs.getBoolean(ATTR_6);
                TimeKeeping staff = null;
                if ((staff = timeKeepingStaffDAO.getById(id)) != null) {
                    list.add(new TimeKeepingStaff(id, employeeDAO.getById(employeeId),
                            createdDate, shiftWorkDAO.getById(shiftWorkId), status, leavePermission));
                } else {
                    if ((staff = timeKeepingWorkerDAO.getById(id)) != null) {
                        TimeKeepingWorker worker = (TimeKeepingWorker) staff;
                        list.add(new TimeKeepingWorker(id, employeeDAO.getById(employeeId), createdDate, shiftWorkDAO.getById(shiftWorkId),
                                status, leavePermission, worker.getProduct(), worker.getStage(),
                                worker.getAssignment()));
                    }
                }
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

    public TimeKeeping getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String employeeId = rs.getString(ATTR_2);
                Date createdDate = rs.getDate(ATTR_3);
                String shiftWorkId = rs.getString(ATTR_4);
                String status = rs.getString(ATTR_5);
                boolean leavePermission = rs.getBoolean(ATTR_6);
                TimeKeeping staff = null;
                if ((staff = timeKeepingStaffDAO.getById(id)) != null) {
                    return new TimeKeepingStaff(id, employeeDAO.getById(employeeId),
                            createdDate, shiftWorkDAO.getById(shiftWorkId), status, leavePermission);
                } else {
                    if ((staff = timeKeepingWorkerDAO.getById(id)) != null) {
                        TimeKeepingWorker worker = (TimeKeepingWorker) staff;
                        return new TimeKeepingWorker(id, employeeDAO.getById(employeeId), createdDate, shiftWorkDAO.getById(shiftWorkId),
                                status, leavePermission, worker.getProduct(), worker.getStage(),
                                worker.getAssignment());
                    }
                }
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

    public boolean add(TimeKeeping timeKeeping) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, timeKeeping.getId(),
                    timeKeeping.getEmployee().getId(), timeKeeping.getCreatedDate(),
                    timeKeeping.getShiftWork().getId(), timeKeeping.getStatus(), timeKeeping.isLeavePermission());
            if (check > 0) {
                if (timeKeeping instanceof TimeKeepingStaff) {
                    return timeKeepingStaffDAO.add(timeKeeping.getId());
                } else {
                    return timeKeepingWorkerDAO.add((TimeKeepingWorker) timeKeeping);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }

    public boolean update(TimeKeeping timeKeeping) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? ," + ATTR_5 + " = ? ," + ATTR_6 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;",
                    timeKeeping.getEmployee().getId(), timeKeeping.getCreatedDate(),
                    timeKeeping.getShiftWork().getId(), timeKeeping.getStatus(), timeKeeping.isLeavePermission(),
                    timeKeeping.getId());
            if (check > 0) {
                if (timeKeeping instanceof TimeKeepingStaff) {
                    return true;
                } else {
                    return timeKeepingWorkerDAO.update((TimeKeepingWorker) timeKeeping);
                }
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
            boolean result = false;
            if (timeKeepingStaffDAO.getById(id) != null) {
                result = timeKeepingStaffDAO.delete(id);
            }
            if (timeKeepingWorkerDAO.getById(id) != null) {
                result = timeKeepingWorkerDAO.delete(id);
            }
            if (result) {
                int check = dbConn.excuteSql("Delete From " + TABLE_NAME + " "
                        + "WHERE " + ATTR_1 + " = ?;", id);
                if (check > 0) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }
}
