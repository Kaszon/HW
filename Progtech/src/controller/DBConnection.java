package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Car;
import model.Client;
import model.Rent;


public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/RentsDB";
    private static final String USERNAME = "kaszon";
    private static final String PASSWORD = "kaszon";
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static Connection conn = null;
    private static Statement statement = null;
    private static PreparedStatement prepStatement = null;
    private static DatabaseMetaData dbmd = null;

    public DBConnection() {
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
        }
        if (conn != null) {
            //createCarTable();
            //createClientTable();
            //createRentTable();
        }
    }
    
    public static ArrayList<Rent> getAllRents() {
      ArrayList<Rent> rents = new ArrayList<>();
      Rent tmpRent;
      Date s,e,r;
      try {
          conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      } catch (SQLException ex) {
      }
      if (conn != null) {
          try {
              statement = conn.createStatement();
              statement.execute("SELECT * FROM rent");
              ResultSet rs = statement.getResultSet();

              while (rs.next()) {
                try {
                  r = sdf.parse(rs.getString(6));
                } catch ( ParseException ex) {
                  r = null;
                }
                s = sdf.parse(rs.getString(4));
                e = sdf.parse(rs.getString(5));
                tmpRent = new Rent(rs.getInt(1), getCarByLicenceNumber(rs.getString(2)), getClientByID(rs.getInt(3)), s,e,r);
                rents.add(tmpRent);
              }
          } catch (SQLException | ParseException ex) {
          }
      }
      return rents;
    }
    
    public static Client getClientByID(int id){
      Client result = null;
      try {
        conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      } catch (SQLException e) {
      }
      if (conn != null) {
        try {
          prepStatement = conn.prepareStatement("SELECT * FROM client WHERE id = ?");
          prepStatement.setInt(1, id);
          ResultSet rs = prepStatement.executeQuery();
          while (rs.next()) {
            result = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
          }
        } catch (SQLException e) {
        }
      }
      return result;
    }
    
    public static Car getCarByLicenceNumber(String licenceNumb){
      Car result = null;
      Date tmpDate;
      try {
        conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      } catch (SQLException e) {
      }
      if (conn != null) {
        try {
          prepStatement = conn.prepareStatement("SELECT * FROM car WHERE licencenumber = ?");
          prepStatement.setString(1, licenceNumb);
          ResultSet rs = prepStatement.executeQuery();
          while (rs.next()) {
            tmpDate = sdf.parse(rs.getString(4));
            result = new Car(rs.getString(2), rs.getString(3), tmpDate,
                            rs.getString(1), rs.getBoolean(6), rs.getString(5), rs.getInt(7), rs.getInt(8));
          }
        } catch (SQLException | ParseException e) {
        }
      }
      return result;
    }
    
    public static Rent getRentByID(int id){
      Rent result = null;
      Date s,e,r;
      try {
        conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      } catch (SQLException ex) {
      }
      if (conn != null) {
        try {
          prepStatement = conn.prepareStatement("SELECT * FROM rent WHERE id = ?");
          prepStatement.setInt(1, id);
          ResultSet rs = prepStatement.executeQuery();
          while (rs.next()) {
            try {
              r = sdf.parse(rs.getString(6));
            } catch ( ParseException ex) {
              r = null;
            }
            s = sdf.parse(rs.getString(4));
            e = sdf.parse(rs.getString(5));
            result = new Rent(rs.getInt(1), getCarByLicenceNumber(rs.getString(2)), getClientByID(rs.getInt(3)), s,e,r);
          }
        } catch (SQLException | ParseException ex) {
        }
      }
      return result;
    }
    
    public static ArrayList<Client> getAllClients() {
      ArrayList<Client> clients = new ArrayList<>();
      Client tmpClient;
      try {
          conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      } catch (SQLException e) {
      }
      if (conn != null) {
          try {
              statement = conn.createStatement();
              statement.execute("SELECT * FROM client");
              ResultSet rs = statement.getResultSet();

              while (rs.next()) {
                  tmpClient = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                  clients.add(tmpClient);
              }
          } catch (SQLException e) {
          }
      }
      return clients;
    }
    
    public static ArrayList<Car> getAllCars(){
        ArrayList<Car> cars = new ArrayList<>();
        Car tmpCar;
        Date tmpDate;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
          System.err.println("Not connected to the database!");
          System.exit(1);
        }
        if (conn != null) {
            try {
                statement = conn.createStatement();
                statement.execute("SELECT * FROM car");
                ResultSet rs = statement.getResultSet();
                
                while (rs.next()) {
                    tmpDate = sdf.parse(rs.getString(4));
                    tmpCar = new Car(rs.getString(2), rs.getString(3), tmpDate,
                            rs.getString(1), rs.getBoolean(6), rs.getString(5), rs.getInt(7), rs.getInt(8));
                    cars.add(tmpCar);
                }
            } catch (SQLException | ParseException e) {
            }
        }
        return cars;
    }
    
    public static Car addNewCar(Car c){
      Car result = null;
      Date tmpDate;
      try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
          System.out.println("hiba1");
        }
      if (conn != null) {
          try {
            //"INSERT INTO car VALUES ('toy515', 'Toyota', 'Yaris', '2017-05-05', './files/car.png', 0, 5000, 2015)"
            StringBuilder sb = new StringBuilder("INSERT INTO car VALUES ('");
            sb.append(c.getLicenseNumber());
            sb.append("', '");
            sb.append(c.getBrand());
            sb.append("', '");
            sb.append(c.getType());
            sb.append("', '");
            sb.append(sdf.format(c.getLastService()));
            sb.append("', '");
            sb.append(c.getImg());
            sb.append("', ");
            sb.append(c.isOnService() ? 1 : 0);
            sb.append(", ");
            sb.append(c.getPricePerDay());
            sb.append(", ");
            sb.append(c.getYearOfManufacture());
            sb.append(")");
            statement = conn.createStatement();
            System.out.println(sb.toString());
            statement.execute(sb.toString());
            statement.execute("SELECT * FROM car WHERE licencenumber = '" + c.getLicenseNumber() + "'");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                tmpDate = sdf.parse(rs.getString(4));
                result = new Car(rs.getString(2), rs.getString(3), tmpDate,
                        rs.getString(1), rs.getBoolean(6), rs.getString(5), rs.getInt(7), rs.getInt(8));
            }
          } catch (SQLException | ParseException e) {
            System.out.println("hiba2");
          }
      }
      return result;
    }

    public static Car modifyACar(Car c){
      Car result = null;
      Date tmpDate;
      try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
          System.out.println("hiba1");
        }
      if (conn != null) {
          try {
            StringBuilder sb = new StringBuilder("UPDATE car SET brand = '");
            sb.append(c.getBrand());
            sb.append("', type = '");
            sb.append(c.getType());
            sb.append("', lastservice = '");
            sb.append(sdf.format(c.getLastService()));
            sb.append("', onservice = ");
            sb.append(c.isOnService() ? 1 : 0);
            sb.append(", price = ");
            sb.append(c.getPricePerDay());
            sb.append(" WHERE licencenumber = '");
            sb.append(c.getLicenseNumber());
            sb.append("'");
            statement = conn.createStatement();
            System.out.println(sb.toString());
            statement.execute(sb.toString());
            statement.execute("SELECT * FROM car WHERE licencenumber = '" + c.getLicenseNumber() + "'");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                tmpDate = sdf.parse(rs.getString(4));
                result = new Car(rs.getString(2), rs.getString(3), tmpDate,
                        rs.getString(1), rs.getBoolean(6), rs.getString(5), rs.getInt(7), rs.getInt(8));
            }
          } catch (SQLException | ParseException e) {
            System.out.println("hiba2");
          }
      }
      return result;
    }
    
    private void createCarTable() {
        try {
            statement = conn.createStatement();
            //statement.execute("DROP TABLE car");
            statement.execute("CREATE TABLE car (licencenumber varchar(6) primary key not null, brand varchar(32), type varchar(32),"
                    + " lastservice varchar(32), picture varchar(32), onservice int, price int, yearofmanu int)");            
            statement.execute("INSERT INTO car VALUES ('toy515', 'Toyota', 'Yaris', '2017-05-05', './files/car.png', 0, 5000, 2015)");
            statement.execute("INSERT INTO car VALUES ('hoc616', 'Honda', 'Civic', '2018-01-02', './files/car.png', 0, 6000, 2016)");
            statement.execute("INSERT INTO car VALUES ('sks614', 'Skoda', 'SuperB', '2017-05-05', './files/car.png', 0, 6000, 2014)");
            statement.execute("INSERT INTO car VALUES ('toy413', 'Toyota', 'Yaris', '2017-05-05', './files/car.png', 0, 4500, 2013)");
            statement.execute("INSERT INTO car VALUES ('rem611', 'Renault', 'Megane', '2017-05-05', './files/car.png', 0, 6000, 2011)");                
            statement.execute("INSERT INTO car VALUES ('poc318', 'Porsche', 'Cayenne', '2015-05-05', './files/car.png', 1, 30000, 2018)");
            statement.execute("INSERT INTO car VALUES ('fer318', 'Ferrari', '488', '2017-05-05', './files/car.png', 1, 30000, 2018)");
            statement.execute("INSERT INTO car VALUES ('ivd110', 'Iveco', 'Daily', '2016-05-05', './files/car.png', 0, 10000, 2010)");
            statement.execute("INSERT INTO car VALUES ('kis910', 'Kia', 'Sorento', '2017-05-05', './files/car.png', 0, 9000, 2010)");
            statement.execute("INSERT INTO car VALUES ('vos712', 'Volkswagen', 'Sharan', '2018-05-05', './files/car.png', 0, 7000, 2012)");
//            statement.execute("SELECT * FROM car");
//            ResultSet rs = statement.getResultSet();
//            StringBuilder sb;
//            while (rs.next()) {
//                sb = new StringBuilder();
//                sb.append(rs.getString("licencenumber")).append(" ");
//                sb.append(rs.getString("brand")).append(" ");
//                sb.append(rs.getString("type")).append(" ");
//                sb.append(rs.getString("lastservice")).append(" ");
//                sb.append(rs.getString("picture")).append(" ");
//                sb.append(rs.getInt("onservice")).append(" ");
//                sb.append(rs.getInt("price")).append(" ");
//                sb.append(rs.getInt("yearofmanu"));
//                System.out.println(sb.toString());
//            }
        } catch (SQLException e) {
        }
    }

    private void createClientTable() {
        try {
            statement = conn.createStatement();
            statement.execute("CREATE TABLE client (id int primary key not null GENERATED ALWAYS AS IDENTITY " + 
                    "(START WITH 1, INCREMENT BY 1), name varchar(32), address varchar(32), phonenumber varchar(32))");            
            statement.execute("INSERT INTO client VALUES (default, 'Kis János', 'Budapest', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Nagy Sándor', 'Vác', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Erős Petra', 'Budapest', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Alma Borbála', 'Pécs', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Méhész István', 'Budapest', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Nagy Péter', 'Budapest', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Kiss József', 'Budapest', '06505555555')");
            statement.execute("INSERT INTO client VALUES (default, 'Katona Erika', 'Budapest', '06505555555')");
        } catch (SQLException e) {
        }
    }

    private void createRentTable() {
        try {
            statement = conn.createStatement();
            statement.execute("CREATE TABLE rent (id int primary key not null GENERATED ALWAYS AS IDENTITY " + 
                    "(START WITH 1, INCREMENT BY 1), carlicenc varchar(6), clientid int, startdate varchar(32), enddate varchar(32), returndate varchar(32))");            
            statement.execute("INSERT INTO rent VALUES (default, 'toy413', 3, '2018-04-01', '2018-05-11', '2018-05-11')");
            statement.execute("INSERT INTO rent VALUES (default, 'toy413', 3, '2018-05-18', '2018-06-01', '')");
            statement.execute("INSERT INTO rent VALUES (default, 'fer318', 1, '2018-05-01', '2018-05-03', '2018-05-02')");
            statement.execute("INSERT INTO rent VALUES (default, 'toy515', 6, '2018-04-02', '2018-04-11', '2018-04-13')");
            statement.execute("INSERT INTO rent VALUES (default, 'toy413', 3, '2018-05-12', '2018-05-15', '2018-05-15')");            
            statement.execute("INSERT INTO rent VALUES (default, 'rem611', 4, '2018-03-30', '2018-04-20', '2018-04-21')");
            statement.execute("INSERT INTO rent VALUES (default, 'ivd110', 5, '2018-05-11', '2018-05-20', '')");
        } catch (SQLException e) {
        }
    }
    
    
}
