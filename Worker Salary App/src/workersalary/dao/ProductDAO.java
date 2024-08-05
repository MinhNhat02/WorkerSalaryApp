package workersalary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import workersalary.entity.Product;
import workersalary.util.DBConnection;

public class ProductDAO {

    private String TABLE_NAME = "Product";
    private String ATTR_1 = "id";
    private String ATTR_2 = "name";
    private String ATTR_3 = "style";
    private String ATTR_4 = "material";
    private String ATTR_5 = "amount";

    public List<Product> getAll() throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        List<Product> list = new ArrayList();
        try {
            rs = dbConn.selectAll(TABLE_NAME);
            while (rs.next()) {
                String id = rs.getString(ATTR_1);
                String name = rs.getString(ATTR_2);
                String style = rs.getString(ATTR_3);
                String material = rs.getString(ATTR_4);
                int amount = rs.getInt(ATTR_5);
                list.add(new Product(id, name, style, material, amount));
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

    public Product getById(String id) throws SQLException {
        DBConnection dbConn = DBConnection.getInstance();
        ResultSet rs = null;
        try {
            rs = dbConn.selectWithCondition("Select * From " + TABLE_NAME + " Where " + ATTR_1 + " = ?", id);
            if(rs.next()) {
                String name = rs.getString(ATTR_2);
                String style = rs.getString(ATTR_3);
                String material = rs.getString(ATTR_4);
                int amount = rs.getInt(ATTR_5);
                return new Product(id, name, style, material, amount);
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

    public boolean add(Product product) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            int check = dbConn.insert(TABLE_NAME, product.getId(), product.getName(), product.getStyle(), product.getMaterial(),
                    product.getAmount());
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

    public boolean update(Product product) {
        DBConnection dbConn = DBConnection.getInstance();
        try {
            String sql = "Update " + TABLE_NAME + " Set " + ATTR_2 + " = ? ," + ATTR_3 + " = ? ,"
                    + ATTR_4 + " = ? ," + ATTR_5 + " = ? "
                    + "WHERE " + ATTR_1 + " = ?;";
            System.out.println(sql);
            int check = dbConn.excuteSql(sql, product.getName(),
                    product.getStyle(), product.getMaterial(), product.getAmount(), product.getId());
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
