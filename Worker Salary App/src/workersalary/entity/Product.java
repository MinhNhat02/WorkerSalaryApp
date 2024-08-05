package workersalary.entity;

public class Product {

    private String id;
    private String name;
    private String style;
    private String material;
    private int amount;

    public Product(String id, String name, String style, String material, int amount) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.material = material;
        this.amount = amount;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", style=" + style + ", material=" + material + ", amount=" + amount + '}';
    }

    
    
}
