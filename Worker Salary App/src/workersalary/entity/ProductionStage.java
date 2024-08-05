package workersalary.entity;

public class ProductionStage {
	private String id;
	private String name;
	private double price;
	private Product product;
	private int amount;
	private ProductionStage neededStage;
	
	public ProductionStage(String id, String name, double price, Product product, int amount,
			ProductionStage neededStage) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.product = product;
		this.amount = amount;
		this.neededStage = neededStage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ProductionStage getNeededStage() {
		return neededStage;
	}

	public void setNeededStage(ProductionStage neededStage) {
		this.neededStage = neededStage;
	}
	
	
}
