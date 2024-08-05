package workersalary.entity;

import workersalary.entity.implement.Worker;

public class Assignment {

    private String id;
    private Worker worker;
    private int finishAmount;
    private ProductionStage stage;

    public Assignment(String id, Worker worker, ProductionStage stage, int finishAmount) {
        this.id = id;
        this.worker = worker;
        this.stage = stage;
        this.finishAmount = finishAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public ProductionStage getStage() {
        return stage;
    }

    public void setStage(ProductionStage stage) {
        this.stage = stage;
    }

    public int getFinishAmount() {
        return finishAmount;
    }

    public void setFinishAmount(int finishAmount) {
        this.finishAmount = finishAmount;
    }
    
    
}
