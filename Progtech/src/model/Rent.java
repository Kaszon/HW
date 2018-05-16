package model;

import controller.DBConnection;
import java.util.Date;

public class Rent {
  
  private int id;
  private Car car;
  private Client client;
  private Date startDate;
  private Date endDate;
  private Date returnDate;

  public Rent(int id, Car car, Client client, Date startDate, Date endDate, Date returnDate) {
    this.id = id;
    this.car = car;
    this.client = client;
    this.startDate = startDate;
    this.endDate = endDate;
    this.returnDate = returnDate;
  }

  public Rent(int id, Car car, Client client, Date startDate, Date endDate) {
    this.id = id;
    this.car = car;
    this.client = client;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public int getId() {
    return id;
  }

  public Car getCar() {
    return car;
  }

  public Client getClient() {
    return client;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  @Override
  public String toString() {
    return "Rent{" + "id=" + id + ", car=" + car + ", client=" + client + ", startDate=" + startDate + ", endDate=" + endDate + ", returnDate=" + returnDate + '}';
  }
  
  public Object[] getLine(){
    Object[] result = new Object[6];
    result[0] = id;
    result[1] = car.toString();
    result[2] = client.getName();
    result[3] = DBConnection.sdf.format(startDate);
    result[4] = DBConnection.sdf.format(endDate);
    if (returnDate != null) {
        result[5] = DBConnection.sdf.format(returnDate);
    }
    else{
      result[5] = "";
    }
    return result;
  }
  
}
