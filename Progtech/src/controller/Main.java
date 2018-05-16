package controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import model.Car;
import view.MainFrame;

public class Main {

    public static void main(String[] args) {
        new DBConnection();
        ArrayList<Car> cars = DBConnection.getAllCars();
        //cars.add(new Car("Teszt", "teszt", new Date(), "ABC123", false, "./files/car.png", 5000, 2000));
        new MainFrame(cars).setVisible(true);
    }
    
}
