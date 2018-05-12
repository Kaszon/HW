package view;

import controller.DBConnection;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Car;

public class MainFrame extends JFrame{
    
    private JScrollPane jSp = new JScrollPane();
    private JList<Car> jList;
    private JPanel jPn = new JPanel();
    private ArrayList<Car> cars = new ArrayList<>();
    private Car selectedCar = null;
    private JDialog diEditCar = new JDialog(this,"Szerkesztés",Dialog.DEFAULT_MODALITY_TYPE);
    private JDialog diNewCar = new JDialog(this,"Új autó",Dialog.DEFAULT_MODALITY_TYPE);
    private JButton btnEditCar = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            diEditCar.setVisible(true);
        }
    });
    private JButton btnNewCar = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        diNewCar.setVisible(true);
      }
    });
    
    private ListSelectionListener lsl = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            btnEditCar.setEnabled(true);
            selectedCar = jList.getSelectedValue();
            setEditDialog(selectedCar);
        }
    };

    public MainFrame(ArrayList<Car> cars) {
        this.cars = cars;
        setFrame();
        addComponents();
        //setDialog(selectedCar);
        setNewCarDialog();
    }

    private void setFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Főoldal");
        setSize(600,400);
        setLocationRelativeTo(this);
    }
    
    private void addComponents() {
        jList = new JList(cars.toArray());
        jList.addListSelectionListener(lsl);
        jSp.setViewportView(jList);
        btnEditCar.setEnabled(false);
        btnEditCar.setText("Részletek");
        btnNewCar.setText("Új autó");
        add(jSp, BorderLayout.WEST);
        jPn.add(btnEditCar);
        jPn.add(btnNewCar);
        add(jPn, BorderLayout.EAST);
    }
    
    private void setNewCarDialog() {
      JButton btnCancel = new JButton("Mégse");
      JButton btnSave = new JButton("Új kocsi hozzáadása");
      btnCancel.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              diNewCar.dispose();
          }
      });
      JTextField jtfBrand = new JTextField();
      JLabel jlbBrand = new JLabel("Márka");
      JTextField jtfType = new JTextField();
      JLabel jlbType = new JLabel("Típus");
      JTextField jtfLicNum = new JTextField();
      JLabel jlbLicNum = new JLabel("Rendszám");
      JTextField jtfOnServ = new JTextField();
      JLabel jlbOnServ = new JLabel("Szervízben van?");
      JTextField jtfPrice = new JTextField();
      JLabel jlbPrice = new JLabel("Napidíj");
      JTextField jtfLastServ = new JTextField();
      JLabel jlbLastServ = new JLabel("Utolsó szervíz");
      JTextField jtfYear = new JTextField();
      JLabel jlbYear = new JLabel("Gyártási év");
      JPanel panel = new JPanel( new GridLayout(8,2,10,10));

      btnSave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Car newCar = null;
          try {
          newCar = new Car(jtfBrand.getText(), jtfType.getText(), DBConnection.sdf.parse(jtfLastServ.getText()),
                  jtfLicNum.getText(), false, Integer.parseInt(jtfPrice.getText()), Integer.parseInt(jtfYear.getText()));
          }
          catch(NumberFormatException exn){
            System.out.println("number");
          }
          catch(ParseException exp){
            System.out.println("parse");
          }
          if (newCar != null) {
            newCar = DBConnection.addNewCar(newCar);
            refreshList();
            diNewCar.dispose();
          }
//            Component[] comps = panel.getComponents();
//            for (int i = 0; i < numOfComp; i++) {
//              if (comps[i] instanceof JTextField) {
//                System.out.println(((JTextField)comps[i]).getText());
//                //((JTextField)comps[i]).setText("");
//              }
//            }
        }
      });

      diNewCar.setSize(400, 300);
      diNewCar.setLocationRelativeTo(this);
      diNewCar.add(panel);

      panel.add(jlbBrand);
      panel.add(jlbType);
      panel.add(jtfBrand);
      panel.add(jtfType);
      panel.add(jlbLicNum);
      panel.add(jlbPrice);
      panel.add(jtfLicNum);
      panel.add(jtfPrice);
      panel.add(jlbLastServ);
      panel.add(jlbOnServ);
      panel.add(jtfLastServ);
      panel.add(jtfOnServ);
      panel.add(jlbYear);
      panel.add(jtfYear);
      panel.add(btnCancel);
      panel.add(btnSave);
    }

    private void setEditDialog(Car car) {
        JButton btnCancel = new JButton("Mégse");
        JButton btnSave = new JButton("Módosítás");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diEditCar.dispose();
            }
        });
        JTextField jtfBrand = new JTextField(car.getBrand());
        JTextField jtfType = new JTextField(car.getType());
        JTextField jtfLicNum = new JTextField(car.getLicenseNumber());
        jtfLicNum.setEditable(false);
        JTextField jtfOnServ = new JTextField(car.isOnService() ? "Szervízen" : "Elérhető");
        JTextField jtfPrice = new JTextField(String.valueOf(car.getPricePerDay()));
        JTextField jtfLastServ = new JTextField(DBConnection.sdf.format(car.getLastService()));
        JTextField jtfYear = new JTextField(String.valueOf(car.getYearOfManufacture()));
        jtfYear.setEditable(false);
        JPanel panel = new JPanel( new GridLayout(5,2,10,10));
        
        btnSave.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int numOfComp = panel.getComponentCount();
            Car newCar = null;
            try {
            newCar = new Car(jtfBrand.getText(), jtfType.getText(), DBConnection.sdf.parse(jtfLastServ.getText()),
                    jtfLicNum.getText(), false, Integer.parseInt(jtfPrice.getText()), Integer.parseInt(jtfYear.getText()));
            }
            catch(Exception ex){
              diEditCar.dispose();
            }
            newCar = DBConnection.modifyACar(newCar);
            refreshList();
//            Component[] comps = panel.getComponents();
//            for (int i = 0; i < numOfComp; i++) {
//              if (comps[i] instanceof JTextField) {
//                System.out.println(((JTextField)comps[i]).getText());
//                //((JTextField)comps[i]).setText("");
//              }
//            }
            diEditCar.dispose();
          }
        });
        
        diEditCar.setSize(400, 300);
        diEditCar.setLocationRelativeTo(this);
        diEditCar.add(panel);
        
        panel.add(jtfBrand);
        panel.add(jtfType);
        panel.add(jtfLicNum);
        panel.add(jtfPrice);
        panel.add(jtfLastServ);
        panel.add(jtfOnServ);
        panel.add(jtfYear);
        panel.add(btnCancel);
        panel.add(btnSave);
    }
    
    private void refreshList() {
      cars = new ArrayList<>();
      cars = DBConnection.getAllCars();
      jList = new JList(cars.toArray());
      jSp.setViewportView(jList);
      jList.addListSelectionListener(lsl);
  }

}
