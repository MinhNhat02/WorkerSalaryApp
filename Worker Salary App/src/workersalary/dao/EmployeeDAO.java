package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import workersalary.entity.Employee;
import workersalary.entity.implement.AdministrativeStaff;
import workersalary.entity.implement.Worker;
import workersalary.util.DBConnection;

public class EmployeeDAO {

    private String TABLE_NAME = "Employee";
    private String ATTR_1 = "id";
    private String ATTR_2 = "fullName";
    private String ATTR_3 = "identifyCard";
    private String ATTR_4 = "dateOfBirth";
    private String ATTR_5 = "gender";
    private String ATTR_6 = "salary";
    private String ATTR_7 = "allowance";
    private String ATTR_8 = "phoneNumber";
    private String ATTR_9 = "address";
    private String ATTR_10 = "departmentId";
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private AdministrativeStaffDAO administrativeStaffDAO = new AdministrativeStaffDAO();
    private WorkerDAO workerDAO = new WorkerDAO();

    public List<Employee> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Employee> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String fullName = rs.getString(ATTR_2);
                String identifyCard = rs.getString(ATTR_3);
                Date dateOfBirth = rs.getDate(ATTR_4);
                String gender = rs.getString(ATTR_5);
                double salary = rs.getDouble(ATTR_6);
                double allowance = rs.getDouble(ATTR_7);
                String phoneNumber = rs.getString(ATTR_8);
                String address = rs.getString(ATTR_9);
                String departmentId = rs.getString(ATTR_10);
                Employee staff = null;
                if ((staff = administrativeStaffDAO.getById(id)) != null) {
                    list.add(new AdministrativeStaff(id, fullName, identifyCard, dateOfBirth, gender, salary,
                            ((AdministrativeStaff) staff).getBasicSalary(), allowance, phoneNumber,
                            address, departmentDAO.getById(departmentId)));
                } else {
                    if ((staff = workerDAO.getById(id)) != null) {
                        list.add(new Worker(id, fullName, identifyCard,
                                dateOfBirth, gender, salary, allowance, phoneNumber, address, departmentDAO.getById(departmentId)));
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

    public Employee getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String fullName = rs.getString(ATTR_2);
                String identifyCard = rs.getString(ATTR_3);
                Date dateOfBirth = rs.getDate(ATTR_4);
                String gender = rs.getString(ATTR_5);
                double salary = rs.getDouble(ATTR_6);
                double allowance = rs.getDouble(ATTR_7);
                String phoneNumber = rs.getString(ATTR_8);
                String address = rs.getString(ATTR_9);
                String departmentId = rs.getString(ATTR_10);
                Employee staff = null;
                if ((staff = administrativeStaffDAO.getById(id)) != null) {
                    return new AdministrativeStaff(id, fullName, identifyCard, dateOfBirth, gender, salary,
                            ((AdministrativeStaff) staff).getBasicSalary(), allowance, phoneNumber,
                            address, departmentDAO.getById(departmentId));
                } else {
                    if ((staff = workerDAO.getById(id)) != null) {
                        return new Worker(id, fullName, identifyCard,
                                dateOfBirth, gender, salary, allowance, phoneNumber, address, departmentDAO.getById(departmentId));
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

    public boolean add(Employee employee) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, employee.getId(), employee.getFullName(), employee.getIdentifyCard(),
                    employee.getDateOfBirth(), employee.getGender(), employee.getSalary(), employee.getAllowance(),
                    employee.getPhoneNumber(), employee.getAddress(), employee.getDepartment().getId());
            if (check > 0) {
                if (employee instanceof AdministrativeStaff) {
                    administrativeStaffDAO.add((AdministrativeStaff) employee);
                } else {
                    workerDAO.add(employee.getId());
                }
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            dbConn.closeConnection();
        }
        return false;
    }

    public boolean update(Employee employee) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? ," + ATTR_5 + " = ? ," + ATTR_6 + " = ? ," + ATTR_7 + " = ? ,"
                    + ATTR_8 + " = ? ," + ATTR_9 + " = ? ," + ATTR_10 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;", employee.getFullName(), employee.getIdentifyCard(),
                    employee.getDateOfBirth(), employee.getGender(), employee.getSalary(), employee.getAllowance(),
                    employee.getPhoneNumber(), employee.getAddress(), employee.getDepartment().getId(), employee.getId());
            if (check > 0) {
                if (employee instanceof AdministrativeStaff) {
                    return administrativeStaffDAO.update((AdministrativeStaff) employee);
                } else {
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

    public boolean delete(String id) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            boolean result = false;
            if (administrativeStaffDAO.getById(id) != null) {
                result = administrativeStaffDAO.delete(id);
            }
            if (workerDAO.getById(id) != null) {
                result = workerDAO.delete(id);
            }
            if (result) {
                int check = dbConn.excuteSql("Delete From " + TABLE_NAME
                        + " WHERE " + ATTR_1 + " = ?;", id);
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
