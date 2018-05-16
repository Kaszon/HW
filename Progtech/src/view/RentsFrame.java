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
    
  private ArrayList<Car> avaibleCars;
  private ArrayList<Client> avaibleClients;
  private JTable tbRents;
  private DefaultTableModel tm;
  private ArrayList<Rent> rents;
  private JScrollPane jsp;
  private JPanel jpn;
  private MainFrame mf;
  private JDialog diNewRent = new JDialog(this,"Új kölcsönzés",Dialog.DEFAULT_MODALITY_TYPE);
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
  private final Object[] COLUMNNAMES = {"ID","Rendszám - Típus","Bérlő neve","Bérlés kezdete","Bérlés tervezett vége","Tényleges visszahozatal"};

  public RentsFrame(MainFrame mf) {
    this.mf = mf;
    rents = DBConnection.getAllRents();
    setFrame();
    addComponents();
    setNewRentDialog();
  }

  private void setFrame() {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("Kölcsönzések");
      setSize(800,600);
      setLocationRelativeTo(this);
  }

  private void addComponents() {
    tm = new DefaultTableModel(COLUMNNAMES, 0);
    jsp = new JScrollPane();
    jpn = new JPanel(new FlowLayout());
    btnCancel.setText("Vissza");
    btnNewRent.setText("Új kölcsönzés");
    for (Rent rent : rents) {
      tm.addRow(rent.getLine());
    }
    tbRents = new JTable();
    tbRents.setModel(tm);
    jsp.setViewportView(tbRents);
    
    jpn.add(btnCancel);
    jpn.add(btnNewRent);
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
    JLabel lbPrice = new JLabel(String.valueOf(getDifferenceDays(d1, d2)));

    JTextField tfStartDate = new JTextField(DBConnection.sdf.format(d1));
    JTextField tfEndDate = new JTextField(DBConnection.sdf.format(d2));
    
    
    JButton btnNewClient = new JButton("Új ügyfél");
    JButton btnBack = new JButton("Mégse");
    JButton btnSave = new JButton("Mentés");
    
    JComboBox<Client> cbSelectClient = new JComboBox<>();
    ComboBoxModel<Client> cbmClient = new DefaultComboBoxModel<>(); //IDE!!!!!
    cbSelectClient.setModel(cbmClient);
    
    JComboBox<Car> cbSelectCar = new JComboBox<>();
    ComboBoxModel<Car> cbmCar = new DefaultComboBoxModel<>(); //IDE!!!!!
    cbSelectCar.setModel(cbmCar);
    
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
    
    panelSouth.add(btnNewClient);
    panelSouth.add(btnBack);
    panelSouth.add(btnSave);
    
    diNewRent.setSize(400, 300);
    diNewRent.setLocationRelativeTo(this);
    diNewRent.add(panelWest,BorderLayout.WEST);
    diNewRent.add(panelEast,BorderLayout.EAST);
    diNewRent.add(panelSouth,BorderLayout.SOUTH);
  }
  
  public static long getDifferenceDays(Date d1, Date d2) {
    long diff = d2.getTime() - d1.getTime();
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }
  
    
}
