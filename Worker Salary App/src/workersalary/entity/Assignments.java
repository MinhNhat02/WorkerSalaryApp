package workersalary.entity;

import workersalary.entity.implement.Worker;

public class Assignments {
	private String id;
	private Worker worker;
	private int finishAmount;
	private ProductionStage stage;
	
	public Assignments(String id, Worker worker, ProductionStage stage) {
		this.id = id;
		this.worker = worker;
		this.stage = stage;
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
	
	
	
}
