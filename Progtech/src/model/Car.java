package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

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

  public Car(String brand, String type, Date lastService, String licenseNumber, boolean onService, int pricePerDay, int yearOfManufacture) {
    this.brand = brand;
    this.type = type;
    this.lastService = lastService;
    this.licenseNumber = licenseNumber;
    this.onService = onService;
    this.img = "./files/car.png";
    this.pricePerDay = pricePerDay;
    this.yearOfManufacture = yearOfManufacture;
  }
    
    @Override
    public String toString() {
        return licenseNumber + " - " + brand + " " + type;
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

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 29 * hash + Objects.hashCode(this.licenseNumber);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Car other = (Car) obj;
    if (!Objects.equals(this.licenseNumber, other.licenseNumber)) {
      return false;
    }
    return true;
  }
    
}


