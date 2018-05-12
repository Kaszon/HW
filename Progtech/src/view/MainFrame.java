package view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Car;

public class MainFrame extends JFrame{
    
    private JScrollPane jSp = new JScrollPane();
    private JList<Car> jList;
    private ArrayList<Car> cars = new ArrayList<>();
    private Car selectedCar = null;
    private JDialog diEditCar = new JDialog(this,"Új/Szerkesztés",Dialog.DEFAULT_MODALITY_TYPE);
    private JButton btnEditCar = new JButton(new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            diEditCar.setVisible(true);
            
        }
    });
    
    private ListSelectionListener lsl = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            btnEditCar.setEnabled(true);
            selectedCar = jList.getSelectedValue();
        }
    };

    public MainFrame(ArrayList<Car> cars) {
        this.cars = cars;
        setFrame();
        addComponents();
        setDialog(selectedCar);
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
        add(jSp, BorderLayout.WEST);
        add(btnEditCar, BorderLayout.EAST);
    }

    private void setDialog(Car car) {
        JButton btnCancel = new JButton("Mégse");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diEditCar.dispose();
            }
        });
        JTextField jtfBrand = new JTextField(car.getBrand());
        JTextField jtfType = new JTextField("");
        JTextField jtfLicNum = new JTextField(car.getLicenseNumber());
        JTextField jtfOnServ = new JTextField("");
        JTextField jtfPrice = new JTextField("");
        JTextField jtfLastServ = new JTextField("");
        JTextField jtfYear = new JTextField("");
    }

}
