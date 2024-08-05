package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.DepartmentDAO;
import workersalary.entity.Department;

public class DepartmentRepository {

    private static DepartmentRepository instance;

    public static DepartmentRepository getInstance() {
        if (instance == null) {
            instance = new DepartmentRepository();
        }
        return instance;
    }
    private DepartmentDAO dao;
    private List<Department> list;

    private DepartmentRepository() {
        dao = new DepartmentDAO();
        getAll();
    }

    public List<Department> getAll() {
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

    public Department getById(String id) {
        if (list != null && list.size() > 0) {
            for (Department deparment : list) {
                if (deparment.getId().equals(id)) {
                    return deparment;
                }
            }
        }
        return null;
    }
    public Department getByName(String name) {
        if (list != null && list.size() > 0) {
            for (Department deparment : list) {
                if (deparment.getName().equals(name)) {
                    return deparment;
                }
            }
        }
        return null;
    }

    public List<Department> getList() {
        return list;
    }
    
    
}
