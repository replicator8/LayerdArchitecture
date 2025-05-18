package org.example.domain;

public class Inventory {
    private final int productId;
    private int quantity;
    private final int minStock;
    private final int optimalStock;
    private final int criticalLevel;

    public Inventory(int productId, int minStock, int optimalStock, int criticalLevel, int quantity) {
        this.productId = productId;
        this.minStock = minStock;
        this.optimalStock = optimalStock;
        this.criticalLevel = criticalLevel;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinStock() {
        return minStock;
    }

    public int getOptimalStock() {
        return optimalStock;
    }

    public int getCriticalLevel() {
        return criticalLevel;
    }

    public String getCurrentStocks(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("Product ID: ").append(productId).append("\n");
        sb.append("Product name: ").append(name).append("\n");
        sb.append("Current quantity: ").append(quantity).append("\n");
        sb.append("Minimal stock: ").append(minStock).append("\n");
        sb.append("Critical level: ").append(criticalLevel).append("\n");

        System.out.println();

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", minStock=" + minStock +
                ", optimalStock=" + optimalStock +
                ", criticalLevel=" + criticalLevel +
                '}';
    }
}
