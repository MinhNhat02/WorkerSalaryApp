package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.ProductionStageDAO;
import workersalary.entity.ProductionStage;

public class ProductionStageRepository {

    private static ProductionStageRepository instance;

    public static ProductionStageRepository getInstance() {
        if (instance == null) {
            instance = new ProductionStageRepository();
        }
        return instance;
    }
    private ProductionStageDAO dao;
    private List<ProductionStage> list;

    private ProductionStageRepository() {
        dao = new ProductionStageDAO();
        getAll();
    }

    public List<ProductionStage> getAll() {
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

    public ProductionStage getById(String id) {
        if (list != null && list.size() > 0) {
            for (ProductionStage stage : list) {
                if (stage.getId().equals(id)) {
                    return stage;
                }
            }
        }
        return null;
    }

    public ProductionStage getByName(String name) {
        if (list != null && list.size() > 0) {
            for (ProductionStage stage : list) {
                if (stage.getName().equals(name)) {
                    return stage;
                }
            }
        }
        return null;
    }

    public boolean add(ProductionStage stage) {
        if (getById(stage.getId()) == null) {
            if (dao.add(stage)) {
                list.add(stage);
                return true;
            }
        }
        return false;
    }

    public boolean checkExistStageNeeded(ProductionStage curStage) {
        if (list != null && list.size() > 0) {
            for (ProductionStage stage : list) {
                if (stage.getNeededStage() == null) {
                    continue;
                }
                if (stage.getNeededStage().getId().equals(curStage.getId())) {
                    return true;
                }
                System.out.println(stage.getName());
            }
        }
        return false;
    }

    public boolean checkNeededNotCurrentStage(ProductionStage stage) {
        if (stage.getNeededStage() == null); else {
            if (stage.getNeededStage().getId().equals(stage.getId())) {
                return false;
            }
        }
        return true;
    }

    public String update(ProductionStage stage) {
        if (list == null || list.isEmpty()) {
            return "Không tồn tại mã CĐ";
        }
        ProductionStage stageInList = getById(stage.getId());
        if (stageInList != null) {
            if (!checkNeededNotCurrentStage(stage)) {
                return "Công đoạn yêu cầu không được trùng với công đoạn hiện tại";
            }
            if (dao.update(stage)) {
                int index = list.indexOf(stageInList);
                list.remove(stageInList);
                list.add(index, stage);
                return "Sửa Thành Công";
            }
        }
        return "Không tồn tại mã CĐ";
    }

    public boolean delete(String id) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        ProductionStage stage = getById(id);
        if (stage != null) {
            if (dao.delete(id)) {
                list.remove(stage);
                return true;
            }
        }
        return false;
    }

    public List<ProductionStage> getList() {
        return list;
    }

}
