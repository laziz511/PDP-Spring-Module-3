package uz.pdp.online.springbootapplication.objectmapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class CarMapperImpl {

    public static void carToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("data/car.json");
        Car car = new Car("Black", "Audi");

        mapper.writeValue(file, car);
    }


    public static void jsonToCar() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("data/car.json");
        Car car = mapper.readValue(file, Car.class);

        System.out.println(car);
    }

    public static void carArrayToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("data/cars.json");
        Car[] cars = new Car[]{
                new Car("Black", "Audi"),
                new Car("White", "Volvo"),
                new Car("Gray", "BMW")};

        mapper.writeValue(file, cars);
    }

    public static void JsonToCarList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("data/cars.json");
        List<Car> cars = mapper.readValue(file, new TypeReference<List<Car>>() {
        });

        cars.forEach(System.out::println);
    }


}
