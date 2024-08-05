package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.ProductDAO;
import workersalary.entity.Product;

public class ProductRepository {

    private static ProductRepository instance;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }
    private ProductDAO dao;
    private List<Product> list;

    private ProductRepository() {
        dao = new ProductDAO();
        getAll();
    }

    public List<Product> getAll() {
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

    public Product getById(String id) {
        if(list != null && list.size() > 0) {
            for(Product product : list) {
                if(product.getId().equals(id)) return product;
            }
        }
        return null;
    }
    public Product getByName(String name) {
        if (list != null && list.size() > 0) {
            for (Product product : list) {
                if (product.getName().equals(name)) {
                    return product;
                }
            }
        }
        return null;
    }
    
    public List<Product> filterById(String id) {
        if(list != null && list.size() > 0) {
            List<Product> filterList = new ArrayList<>();
            for(Product product : list) {
                if(product.getId().toLowerCase().contains(id.toLowerCase())) filterList.add(product);
            }
            return filterList;
        }
        return null;
    }

    public boolean add(Product product) {
        if (getById(product.getId()) == null) {
            if (dao.add(product)) {
                list.add(product);
                return true;
            }
        }
        return false;
    }

    public boolean update(Product product) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Product productInList = getById(product.getId());
        if (productInList != null) {
            if (dao.update(product)) {
                int index = list.indexOf(productInList);
                list.remove(productInList);
                list.add(index, product);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Product product = getById(id);
        if (product != null) {
            if (dao.delete(id)) {
                list.remove(product);
                return true;
            }
        }
        return false;
    }

    public List<Product> getList() {
        return list;
    }
    
    
}
