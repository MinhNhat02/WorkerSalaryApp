package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import workersalary.entity.ProductionStage;
import workersalary.util.DBConnection;

public class ProductionStageDAO {

    private String TABLE_NAME = "Production_Stage";
    private String ATTR_1 = "id";
    private String ATTR_2 = "name";
    private String ATTR_3 = "price";
    private String ATTR_4 = "productId";
    private String ATTR_5 = "amount";
    private String ATTR_6 = "productionStageId";

    public List<ProductionStage> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<ProductionStage> list = new ArrayList();
        ProductDAO productDao = new ProductDAO();
        try {

            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String name = rs.getString(ATTR_2);
                double price = rs.getDouble(ATTR_3);
                String productId = rs.getString(ATTR_4);
                int amount = rs.getInt(ATTR_5);
                String productionStageId = rs.getString(ATTR_6);
                list.add(new ProductionStage(id, name, price, productDao.getById(productId), amount, getById(productionStageId)));
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

    public ProductionStage getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if (rs.next()) {
                String name = rs.getString(ATTR_2);
                double price = rs.getDouble(ATTR_3);
                String productId = rs.getString(ATTR_4);
                int amount = rs.getInt(ATTR_5);
                String productionStageId = rs.getString(ATTR_6);
                return new ProductionStage(id, name, price, new ProductDAO().getById(productId), amount, getById(productionStageId));
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

    public boolean add(ProductionStage productionStage) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = -1;
            if (productionStage.getNeededStage() == null) {
                check = dbConn.insert(TABLE_NAME, productionStage.getId(), productionStage.getName(), productionStage.getPrice(), productionStage.getProduct().getId(),
                        productionStage.getAmount(), null);
            } else {
                check = dbConn.insert(TABLE_NAME, productionStage.getId(), productionStage.getName(), productionStage.getPrice(), productionStage.getProduct().getId(),
                        productionStage.getAmount(), productionStage.getNeededStage().getId());
            }
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

    public boolean update(ProductionStage productionStage) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = -1;
            if (productionStage.getNeededStage() != null) {
                check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                        + ATTR_4 + " = ? ," + ATTR_5 + " = ? ," + ATTR_6 + " = ? "
                        + "WHERE " + ATTR_1 + " = ?;", productionStage.getName(),
                        productionStage.getPrice(), productionStage.getProduct().getId(), productionStage.getAmount(),
                        productionStage.getNeededStage().getId(), productionStage.getId());
            } else {
                check = dbConn.excuteSql("Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                        + ATTR_4 + " = ? ," + ATTR_5 + " = ? "
                        + "WHERE " + ATTR_1 + " = ?;", productionStage.getName(),
                        productionStage.getPrice(), productionStage.getProduct().getId(), productionStage.getAmount(), productionStage.getId());
            }
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
