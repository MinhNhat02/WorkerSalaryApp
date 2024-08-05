package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.AssignmentDAO;
import workersalary.entity.Assignment;
import workersalary.entity.implement.Worker;

public class AssignmentRepository {

    private static AssignmentRepository instance;

    public static AssignmentRepository getInstance() {
        if (instance == null) {
            instance = new AssignmentRepository();
        }
        return instance;
    }
    private AssignmentDAO dao;
    private List<Assignment> list;

    private AssignmentRepository() {
        dao = new AssignmentDAO();
        getAll();
    }

    public List<Assignment> getAll() {
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

    public Assignment getById(String id) {
        if (list != null && list.size() > 0) {
            for (Assignment assignment : list) {
                if (assignment.getId().equals(id)) {
                    return assignment;
                }
            }
        }
        return null;
    }
    
    public boolean checkExistWorkerAssignmentSameStage(Assignment assignment) {
        if(list != null && list.size() > 0) {
            for(Assignment assignmentInList : list) {
                if(!assignmentInList.getId().equals(assignment.getId())) {
                    if(assignmentInList.getWorker().getId().equals(assignment.getWorker().getId())
                            && assignmentInList.getStage().getId().equals(assignment.getStage().getId())) return true;
                }
            }
        }
        return false;
    }
    
    public int workerProduceCount(Worker worker) {
        if(list != null && list.size() > 0) {
            int productNum = 0;
            for(Assignment assignment : list) {
                if(assignment.getWorker().getId().equals(worker.getId())) {
                    productNum += assignment.getFinishAmount();
                }
            }
            return productNum;
        }
        return -1;
    }

    public boolean add(Assignment assignment) {
        if (getById(assignment.getId()) == null) {
            if (dao.add(assignment)) {
                list.add(assignment);
                return true;
            }
        }
        return false;
    }

    public boolean update(Assignment assignment) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Assignment assignmentInList = getById(assignment.getId());
        if (assignmentInList != null) {
            if (dao.update(assignment)) {
                int index = list.indexOf(assignmentInList);
                list.remove(assignmentInList);
                list.add(index, assignment);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Assignment assignment = getById(id);
        if (assignment != null) {
            if (dao.delete(id)) {
                list.remove(assignment);
                return true;
            }
        }
        return false;
    }

    public List<Assignment> getList() {
        return list;
    }
    
}
