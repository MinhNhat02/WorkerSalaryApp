package workersalary.entity.implement;

import java.sql.Date;

import workersalary.entity.Assignment;
import workersalary.entity.Employee;
import workersalary.entity.Product;
import workersalary.entity.ProductionStage;
import workersalary.entity.ShiftWork;
import workersalary.entity.TimeKeeping;

public class TimeKeepingWorker extends TimeKeeping {

    private Product product;
    private ProductionStage stage;
    private Assignment assignment;

    public TimeKeepingWorker(String id, Employee employee, Date createdDate, ShiftWork shiftWork, String status,
            boolean leavePermission, Product product, ProductionStage stage, Assignment assignment) {
        super(id, employee, createdDate, shiftWork, status, leavePermission);
        this.product = product;
        this.stage = stage;
        this.assignment = assignment;
    }

    public TimeKeepingWorker() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductionStage getStage() {
        return stage;
    }

    public void setStage(ProductionStage stage) {
        this.stage = stage;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

//    public void calculateSalary() {
//        double salary = shiftWork.getSalary();
//        employee.setSalary(salary);
//    }

}
