package view;

import com.sun.imageio.plugins.jpeg.JPEG;
import controller.DBConnection;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Car;
import model.Client;
import model.Rent;

public class RentsFrame extends JFrame{
  
  private ArrayList<Car> avaibleCarList;
  private ArrayList<Client> avaibleClientList;
  private JComboBox<Client> cbSelectClient;
  private ComboBoxModel<Client> cbmClient;
  private JComboBox<Car> cbSelectCar;
  private ComboBoxModel<Car> cbmCar;
  private JLabel lbPrice;
  private JTextField tfStartDate;
  private JTextField tfEndDate;
  private Client[] arrayClient;
  private Car[] arrayCar;
    
  private ArrayList<Car> avaibleCars;
  private ArrayList<Client> avaibleClients;
  private JTable tbRents;
  private DefaultTableModel tm;
  private ArrayList<Rent> rents;
  private Rent selectedRent = null;
  private JScrollPane jsp;
  private JPanel jpn;
  private MainFrame mf;
  private JDialog diNewRent = new JDialog(this,"Új kölcsönzés",Dialog.DEFAULT_MODALITY_TYPE);
  private JDialog diEditRent = new JDialog(this,"Kölcsönzés vége",Dialog.DEFAULT_MODALITY_TYPE);
  private JDialog diNewClient = new JDialog(this,"Új ügyfél",Dialog.DEFAULT_MODALITY_TYPE);
  private JDialog diSearch = new JDialog(this,"Szűrés",Dialog.DEFAULT_MODALITY_TYPE);
  private JButton btnCancel = new JButton(new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      mf.setVisible(true);
    }
  });
  private JButton btnNewRent = new JButton(new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
      diNewRent.setVisible(true);
    }
  });
  private JButton btnEditRent = new JButton(new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (selectedRent != null) {
        diEditRent.setVisible(true);  
      }
    }
  });
  private JButton btnSearch = new JButton(new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
      diSearch.setVisible(true);
    }
  });
  private final Object[] COLUMNNAMES = {"ID","Rendszám - Típus","Bérlő neve","Bérlés kezdete","Bérlés tervezett vége","Tényleges visszahozatal"};

  public RentsFrame(MainFrame mf) {
    this.mf = mf;
    rents = DBConnection.getAllRents();
    selectedRent = rents.get(0);
    setFrame();
    addComponents();
    setNewRentDialog();
    setEditRentDialog(selectedRent);
    setNewClientDialog();
    setSearchDialog();
  }

  private void setFrame() {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("Kölcsönzések");
      setSize(850,600);
      setLocationRelativeTo(this);
  }

  private void addComponents() {
    tm = new DefaultTableModel(COLUMNNAMES, 0);
    jsp = new JScrollPane();
    jpn = new JPanel(new GridLayout(2,2));
    btnCancel.setText("Vissza");
    btnEditRent.setText("Kölcsönzés befejezése");
    btnNewRent.setText("Új kölcsönzés");
    btnSearch.setText("Keresés");
    for (Rent rent : rents) {
      tm.addRow(rent.getLine());
    }
    tbRents = new JTable();
    tbRents.setModel(tm);
    jsp.setViewportView(tbRents);
    
    jpn.add(btnCancel);
    jpn.add(btnNewRent);
    jpn.add(btnEditRent);
    jpn.add(btnSearch);
    add(jsp,BorderLayout.WEST);
    add(jpn,BorderLayout.EAST);
  }

  private void setNewRentDialog() {
    
    Date d1, d2;
    d1 = new Date();
    d2 = d1;
    try {
      d2 = DBConnection.sdf.parse("2018-06-01");
    } catch (ParseException ex) {
    }
    
    JLabel lbCar = new JLabel("Autó");
    JLabel lbClient = new JLabel("Ügyfél");
    JLabel lbStartDate = new JLabel("Kezdő dátum");
    JLabel lbEndDate = new JLabel("Visszahozatali dátum");
    JLabel lbPriceLabel = new JLabel("Tervezett költség");

    tfStartDate = new JTextField(DBConnection.sdf.format(d1));
    tfEndDate = new JTextField(DBConnection.sdf.format(d2));


    
    JButton btnNewClient = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        diNewClient.setVisible(true);
      }
    });
    
    JButton btnBack = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        diNewRent.dispose();
      }
    });

    
    JButton btnSave = new JButton();
    
    avaibleCarList = DBConnection.getAvaibleCars();
    avaibleClientList = DBConnection.getAvaibleClients();
        
    arrayClient = new Client[avaibleClientList.size()];
    cbSelectClient = new JComboBox<>();
    cbmClient = new DefaultComboBoxModel<>(avaibleClientList.toArray(arrayClient));
    cbSelectClient.setModel(cbmClient);
    
    arrayCar = new Car[avaibleCarList.size()];
    cbSelectCar = new JComboBox<>();
    cbmCar = new DefaultComboBoxModel<>(avaibleCarList.toArray(arrayCar));
    cbSelectCar.setModel(cbmCar);
    
    lbPrice = new JLabel(String.valueOf(getDifferenceDays(d1, d2)* ((Car) cbSelectCar.getSelectedItem()).getPricePerDay()));

    
    AbstractAction refreshPrice = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Date d1,d2;
          d1 = DBConnection.sdf.parse(tfStartDate.getText());
          d2 = DBConnection.sdf.parse(tfEndDate.getText());
          lbPrice.setText(String.valueOf(((Car) cbSelectCar.getSelectedItem()).getPricePerDay() * getDifferenceDays(d1, d2)));
        } catch (ParseException ex) {
          lbPrice.setText("Hibás dátum!");
        }
      }
    };
    
    tfStartDate.setAction(refreshPrice);
    tfEndDate.setAction(refreshPrice);
    cbSelectCar.setAction(refreshPrice);

    
    JPanel panelWest = new JPanel(new GridLayout(4, 2));
    panelWest.add(lbCar);
    panelWest.add(cbSelectCar);
    panelWest.add(lbClient);
    panelWest.add(cbSelectClient);
    panelWest.add(lbStartDate);
    panelWest.add(tfStartDate);
    panelWest.add(lbEndDate);
    panelWest.add(tfEndDate);
    
    JPanel panelEast = new JPanel(new GridLayout(2, 1));
    
    panelEast.add(lbPriceLabel);
    panelEast.add(lbPrice);
    
    JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
    
    btnBack.setText("Mégse");
    btnNewClient.setText("Új ügyfél");
    
    btnSave.setAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Rent r = new Rent(DBConnection.getMaxIdFromRent(), (Car)cbSelectCar.getSelectedItem(),
                  (Client)cbSelectClient.getSelectedItem(), DBConnection.sdf.parse(tfStartDate.getText()), DBConnection.sdf.parse(tfEndDate.getText()));
          rents.add(r);
          DBConnection.addNewRent(r);
          refreshList();
          diNewRent.dispose();
        } catch (ParseException ex) {
          ex.printStackTrace();
        }
      }
    });
        btnSave.setText("Mentés");


    panelSouth.add(btnNewClient);
    panelSouth.add(btnBack);
    panelSouth.add(btnSave);
    
    diNewRent.setSize(600, 500);
    diNewRent.setLocationRelativeTo(this);
    diNewRent.add(panelWest,BorderLayout.WEST);
    diNewRent.add(panelEast,BorderLayout.EAST);
    diNewRent.add(panelSouth,BorderLayout.SOUTH);
  }
  
  private static long getDifferenceDays(Date d1, Date d2) {
    long diff = d2.getTime() - d1.getTime();
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }
  
  private void refreshList() {
    rents = new ArrayList<>();
    rents = DBConnection.getAllRents();
    tm = new DefaultTableModel(COLUMNNAMES, 0);
    for (Rent rent : rents) {
      tm.addRow(rent.getLine());
    }
    tbRents.setModel(tm);
    tbRents.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (tbRents.getSelectedRow() > -1) {
          selectedRent = rents.get((int)tbRents.getValueAt(tbRents.getSelectedRow(), 0));
        }
      }
    });
    jsp.setViewportView(tbRents);

    avaibleCarList = DBConnection.getAvaibleCars();
    avaibleClientList = DBConnection.getAvaibleClients();

    arrayClient = new Client[avaibleClientList.size()];
    cbSelectClient = new JComboBox<>();
    cbmClient = new DefaultComboBoxModel<>(avaibleClientList.toArray(arrayClient));
    cbSelectClient.setModel(cbmClient);

    arrayCar = new Car[avaibleCarList.size()];
    cbSelectCar = new JComboBox<>();
    cbmCar = new DefaultComboBoxModel<>(avaibleCarList.toArray(arrayCar));
    cbSelectCar.setModel(cbmCar);

    Date d1 = null;
    Date d2 = null;
    try {
      d1 = DBConnection.sdf.parse(tfStartDate.getText());
      d2 = DBConnection.sdf.parse(tfEndDate.getText());
    } catch (ParseException ex) {
      Logger.getLogger(RentsFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    lbPrice = new JLabel(String.valueOf(getDifferenceDays(d1, d2)* ((Car) cbSelectCar.getSelectedItem()).getPricePerDay()));
  }

  private void setEditRentDialog(Rent r) {
    JLabel lbLicence = new JLabel(r.getCar().getLicenseNumber());
    JTextField tfReturnDate = new JTextField(DBConnection.sdf.format(new Date()));
    JButton btnOk = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          r.setReturnDate(DBConnection.sdf.parse(tfReturnDate.getText()));
          DBConnection.finishRent(tfReturnDate.getText(),lbLicence.getText());
          diEditRent.dispose();
        } catch (ParseException ex) {
          Logger.getLogger(RentsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    btnOk.setText("Ok");
    
    diEditRent.add(lbLicence,BorderLayout.WEST);
    diEditRent.add(tfReturnDate,BorderLayout.EAST);
    diEditRent.add(btnOk,BorderLayout.SOUTH);
    diEditRent.setSize(300, 100);
    diEditRent.setLocationRelativeTo(this);
  }

  private void setNewClientDialog() {
    JPanel panel = new JPanel(new GridLayout(4, 2));

    
    JLabel lbName = new JLabel("Név");
    JLabel lbAddress = new JLabel("Cím");
    JLabel lbPhone = new JLabel("Telefonszám");
    
    JTextField tfName = new JTextField();
    JTextField tfAddress = new JTextField();
    JTextField tfPhone = new JTextField();
    
    JButton btnCanc = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        diNewClient.dispose();
      }
    });
    
    JButton btnAdd = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DBConnection.addNewClient(tfName.getText(), tfAddress.getText(), tfPhone.getText());
        diNewClient.dispose();
      }
    });
    btnCanc.setText("Mégse");
    btnAdd.setText("Hozzáad");
    panel.add(lbName);
    panel.add(tfName);
    panel.add(lbAddress);
    panel.add(tfAddress);
    panel.add(lbPhone);
    panel.add(tfPhone);
    panel.add(btnCanc);
    panel.add(btnAdd);
    
    diNewClient.add(panel);
    diNewClient.setSize(500,500);
    diNewClient.setLocationRelativeTo(this);
  }

  private void setSearchDialog() {
    JPanel panel = new JPanel(new GridLayout(3, 2));
    
    JLabel lbName = new JLabel("Név");
    JLabel lbLicenc = new JLabel("Rendszám");
    
    JTextField tfName = new JTextField();
    JTextField tfLicenc = new JTextField();
    
    JButton btnSearchName = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean ok = true;
        for (Rent rent : rents) {
          if (tfName.getText().equals(rent.getClient().getName()) && ok) {
            ok = false;
            JOptionPane.showMessageDialog(RentsFrame.this, rent.toString());
          }
        }
        if (ok) {
          JOptionPane.showMessageDialog(RentsFrame.this, "Nincs ilyen találat.");
        }
        diSearch.dispose();
      }
    });
    
    JButton btnSearchLicenc = new JButton(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean ok = true;
        for (Rent rent : rents) {
          if (tfLicenc.getText().equals(rent.getCar().getLicenseNumber()) && ok) {
            ok = false;
            JOptionPane.showMessageDialog(RentsFrame.this, rent.toString());
          }
        }
        if (ok) {
          JOptionPane.showMessageDialog(RentsFrame.this, "Nincs ilyen találat.");
        }
        diSearch.dispose();
      }
    });
    
    btnSearchLicenc.setText("Keresés");
    btnSearchName.setText("Keresés");
    panel.add(lbName);
    panel.add(lbLicenc);
    panel.add(tfName);
    panel.add(tfLicenc);
    panel.add(btnSearchName);
    panel.add(btnSearchLicenc);
    
    diSearch.add(panel);
    diSearch.setSize(500,500);
    diSearch.setLocationRelativeTo(this);
  }
}
