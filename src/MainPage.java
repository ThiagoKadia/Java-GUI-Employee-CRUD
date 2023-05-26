import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import static java.lang.Double.parseDouble;

public class MainPage {
    private JTextField txtId;
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSearch;
    private JTextField txtSearchId;
    private JTextField txtName;
    private JButton btnListAll;
    private JTextArea txtList;
    private JPanel MainPage;
    private JTextField txtWage;
    private JComboBox cbPosition;
    private JComboBox cbDepartment;
    private JButton btnIncreaseWage;
    private static List<Employee> auxList = new ArrayList<>(); //this is the list which will be used in every employee manipulation
    private static Integer idCounter = 1; //the employees ID will start go from 1 and will change only under delete condition

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainPage");
        frame.setContentPane(new MainPage().MainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void clearTextFields() { // this method was created to give a fresh restart to some of the MainPage's fields
        txtId.setText("");
        txtName.setText("");
        txtSearchId.setText("");
        txtWage.setText("");
    }

    public MainPage() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText(Integer.toString(idCounter));
                saveEmployee(txtName.getText(), Department.valueOf(cbDepartment.getSelectedItem().toString()), parseDouble(txtWage.getText()), cbPosition.getSelectedItem().toString());
                clearTextFields();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee(Integer.parseInt(txtSearchId.getText()) - 1, txtName.getText(), Department.valueOf(cbDepartment.getSelectedItem().toString()), parseDouble(txtWage.getText()));
                txtId.setText(txtSearchId.getText());
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployee(Integer.parseInt(txtSearchId.getText()) - 1);
                for (Employee employee : auxList) {
                    employee.setId(auxList.indexOf(employee) + 1); //this code updates the employees IDs to follow their position in the list after an object is excluded
                }
                clearTextFields();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtList.setText(getStringEmployee(findEmployee(auxList, Integer.parseInt(txtSearchId.getText()) - 1)));
                txtId.setText(txtSearchId.getText());
            }
        });

        btnListAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtList.setText(buildEmployeeList(auxList));
                clearTextFields();
            }
        });
        btnIncreaseWage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseWage(Integer.parseInt(txtSearchId.getText()) - 1);
            }
        });
    }

    private static String buildEmployeeList(List<Employee> employees) { // this method builds a String with all employees in a list
        try {
            String writtenEmployees = "";
            for (Employee employee : employees) {
                writtenEmployees += "ID: " + (employee.getId()) + "\nName: " + employee.getName() + "\nPosition: " + employee.getPosition() + "\nDepartment: " + employee.getDepartment() + "\nWage: $" + employee.getWage() + "\n\n";
            }
            return writtenEmployees;
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private static Employee findEmployee (List<Employee> employees, int id) { // return an employee based on a list, and it's position in the list
        try {
            return employees.get(id);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private static String getStringEmployee (Employee employee) { //this method returns a String with the attributes of one employee
        try {
            return "ID: " + (employee.getId()) + "\nName: " + employee.getName() + "\nPosition: " + employee.getPosition() + "\nDepartment: " + employee.getDepartment() + "\nWage: $" + employee.getWage();
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private static void updateEmployee (int id, String newName, Department newDepartment, double newWage) {
        try {
            Employee auxEmployee = findEmployee(auxList, id);
            auxEmployee.setName(newName);
            auxEmployee.setDepartment(newDepartment);
            auxEmployee.setWage(newWage);
            JOptionPane.showMessageDialog(null, "Employee updated successfully", "Update Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removeEmployee (int id) {
        try {
            auxList.remove(id);
            JOptionPane.showMessageDialog(null, "Employee deleted successfully", "Delete Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void saveEmployee (String name, Department department, double wage, String position) {
        try {
            if (position == "Manager") {
                Manager auxManager = new Manager(idCounter, name, department, wage, position);
                auxList.add(auxManager);
                idCounter++;
                JOptionPane.showMessageDialog(null, "Employee saved successfully", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (position == "FullTime") {
                FullTime auxFullTime = new FullTime(idCounter, name, department, wage, position);
                auxList.add(auxFullTime);
                idCounter++;
                JOptionPane.showMessageDialog(null, "Employee saved successfully", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                throw new Exception("Invalid position. Please use Manager or FullTime.");
            }
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void increaseWage(int id) {
        try {
            findEmployee(auxList, id).increaseWage();
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}