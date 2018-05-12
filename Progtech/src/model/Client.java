package model;

public class Client {
  
  public static int lastId = 0;
  
  private int id;
  private String name;
  private String address;
  private String phonenumber;

  public Client(int id, String name, String address, String phonenumber) {
    this.id = id;
    if (id > lastId) {
      lastId = id;
    }
    this.name = name;
    this.address = address;
    this.phonenumber = phonenumber;
  }
  
  public Client(String name, String address, String phonenumber) {
    lastId++;
    this.id = lastId;
    this.name = name;
    this.address = address;
    this.phonenumber = phonenumber;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhonenumber() {
    return phonenumber;
  }
  
  
  
}
