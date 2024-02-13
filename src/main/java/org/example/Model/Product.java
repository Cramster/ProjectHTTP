package org.example.Model;
import java.util.Objects;
public class Product {

    public String brand;
    public String model;
    public double price;
    public long id;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Product() {
    }

    public Product(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(brand, product.brand) && Objects.equals(model, product.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "make='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + price +
                '}';
    }
}
