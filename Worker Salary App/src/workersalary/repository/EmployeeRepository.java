package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.EmployeeDAO;
import workersalary.entity.Employee;

public class EmployeeRepository {

    private static EmployeeRepository instance;

    public static EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
        }
        return instance;
    }
    private EmployeeDAO dao;
    private List<Employee> list;

    private EmployeeRepository() {
        dao = new EmployeeDAO();
        getAll();
    }

    public List<Employee> getAll() {
        try {
            list = dao.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public Employee getById(String id) {
        if (list != null && list.size() > 0) {
            for (Employee employee : list) {
                if (employee.getId().equals(id)) {
                    return employee;
                }
            }
        }
        return null;
    }

    public List<Employee> filterById(String id) {
        if (list != null && list.size() > 0) {
            List<Employee> filterList = new ArrayList<>();
            for (Employee employee : list) {
                if (employee.getId().toLowerCase().contains(id.toLowerCase())) {
                    filterList.add(employee);
                }
            }
            return filterList;
        }
        return null;
    }

    public boolean add(Employee employee) {
        if (getById(employee.getId()) == null) {
            if (dao.add(employee)) {
                list.add(employee);
                return true;
            }
        }
        return false;
    }

    public boolean update(Employee employee) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Employee employeeInList = getById(employee.getId());
        if (employeeInList != null) {
            if (dao.update(employee)) {
                int index = list.indexOf(employeeInList);
                list.remove(employeeInList);
                list.add(index, employee);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Employee employee = getById(id);
        if (employee != null) {
            if (dao.delete(id)) {
                list.remove(employee);
                return true;
            }
        }
        return false;
    }

    public List<Employee> getList() {
        return list;
    }


}
