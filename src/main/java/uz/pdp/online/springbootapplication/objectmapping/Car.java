package uz.pdp.online.springbootapplication.objectmapping;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Car {
    private String color;
    private String type;

    @JsonIgnore
    private Double price;

    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public Car() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}