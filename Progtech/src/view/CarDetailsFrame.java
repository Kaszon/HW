package view;

import controller.DBConnection;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Car;

public class CarDetailsFrame extends JFrame{
    
    private JTextField jtfBrand;
    private JTextField jtfType;
    private JTextField jtfLicNum;
    private JTextField jtfOnServ;
    //private JTextField jtfBrand;
    private JTextField jtfPrice;
    private JTextField jtfLastServ;
    private JTextField jtfYear;
    private Car car;

    public CarDetailsFrame(Car car) {
        this.car = car;
        jtfBrand = new JTextField(car.getBrand());
        jtfType = new JTextField(car.getType());
        jtfLicNum = new JTextField(car.getLicenseNumber());
        jtfOnServ = new JTextField(car.isOnService() ? "Szervízen" : "Elérhető");
        jtfPrice = new JTextField(String.valueOf(car.getPricePerDay()));
        jtfLastServ = new JTextField(DBConnection.sdf.format(car.getLastService()));
        jtfYear = new JTextField(String.valueOf(car.getYearOfManufacture()));
        setFrame();
        addComponents();
    }
    
    public CarDetailsFrame() {
        setFrame();
        addComponents();
    }
    

    private void setFrame() {
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Főoldal");
        setSize(600,400);
        setLocationRelativeTo(this);
    }
    
    private void addComponents() {
        add(jtfBrand);
        add(jtfType);
        add(jtfLicNum);
    }
    
}
