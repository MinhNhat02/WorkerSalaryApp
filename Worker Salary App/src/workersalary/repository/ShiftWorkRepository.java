package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.ShiftWorkDAO;
import workersalary.entity.ShiftWork;

public class ShiftWorkRepository {

    private static ShiftWorkRepository instance;

    public static ShiftWorkRepository getInstance() {
        if (instance == null) {
            instance = new ShiftWorkRepository();
        }
        return instance;
    }
    private ShiftWorkDAO dao;
    private List<ShiftWork> list;

    private ShiftWorkRepository() {
        dao = new ShiftWorkDAO();
        getAll();
    }

    public List<ShiftWork> getAll() {
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

    public ShiftWork getById(String id) {
        if(list != null && list.size() > 0) {
            for(ShiftWork shiftWork : list) {
                if(shiftWork.getId().equals(id)) return shiftWork;
            }
        }
        return null;
    }
    
    public ShiftWork getByName(String name) {
        if (list != null && list.size() > 0) {
            for (ShiftWork shiftWork : list) {
                if (shiftWork.getName().equals(name)) {
                    return shiftWork;
                }
            }
        }
        return null;
    }

    public List<ShiftWork> getList() {
        return list;
    }
    
    
}
