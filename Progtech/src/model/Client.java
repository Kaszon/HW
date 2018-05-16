package model;

import java.util.Objects;

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

  @Override
  public String toString() {
    return name;
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

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 13 * hash + this.id;
    hash = 13 * hash + Objects.hashCode(this.name);
    hash = 13 * hash + Objects.hashCode(this.address);
    hash = 13 * hash + Objects.hashCode(this.phonenumber);
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
    final Client other = (Client) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.address, other.address)) {
      return false;
    }
    if (!Objects.equals(this.phonenumber, other.phonenumber)) {
      return false;
    }
    return true;
  }
  
  
}
