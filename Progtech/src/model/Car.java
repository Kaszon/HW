package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Car {
    
    private String brand;
    private String type;
    private Date lastService;
    private String licenseNumber;
    private boolean onService;
    private String img;
    private int pricePerDay;
    private int yearOfManufacture;

    
    public Car(String brand, String type, Date lastService, String licenseNumber,
            boolean onService, String img, int pricePerDay, int yearOfManufacture) {
        this.brand = brand;
        this.type = type;
        this.lastService = lastService;
        this.licenseNumber = licenseNumber;
        this.onService = onService;
        this.img = img;
        this.pricePerDay = pricePerDay;
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return "Márka : " + brand + " Típus : " + type + " Rendszám : " + licenseNumber + " Napi díj :" + pricePerDay;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public Date getLastService() {
        return lastService;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public boolean isOnService() {
        return onService;
    }

    public String getImg() {
        return img;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }
    
    
    
}


