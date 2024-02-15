package org.example.Model;
import java.util.Objects;
public class Product {

    public String brand;
    public String model;
    public double price;
    public String name;
    public long id;

    public Product(){

    }

    public Product(String brand, String model, double price, long id, String name) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.name = name;
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && id == product.id && Objects.equals(brand, product.brand) && Objects.equals(model, product.model) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price, name, id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}