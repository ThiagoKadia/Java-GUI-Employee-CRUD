import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import static java.lang.Double.parseDouble;

public class MainPageGUI {
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
    private JButton btnTest;
    private static List<Employee> auxList = new ArrayList<>(); //this is the list which will be used in every employee manipulation
    private static Integer idCounter = 1; //the employees ID will start go from 1 and will change only under delete condition

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainPage");
        frame.setContentPane(new MainPageGUI().MainPage);
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

    public MainPageGUI() {
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
                txtList.setText(getEmployeeString(findEmployee(auxList, Integer.parseInt(txtSearchId.getText()) - 1)));
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
        btnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerTestEmployees();
            }
        });
    }

    private static String buildEmployeeList(List<Employee> employees) { // this method builds a String with all employees in a list
        StringBuilder builder = new StringBuilder();
        try {
            for (Employee employee : employees) {
                builder.append("ID: ").append(employee.getId())
                        .append("\nName: ").append(employee.getName())
                        .append("\nPosition: ").append(employee.getPosition())
                        .append("\nDepartment: ").append(employee.getDepartment())
                        .append("\nWage: $").append(employee.getWage())
                        .append("\n\n");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return builder.toString();
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

    private static String getEmployeeString(Employee employee) { //this method returns a String with the attributes of one employee
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("ID: ").append(employee.getId())
                    .append("\nName: ").append(employee.getName())
                    .append("\nPosition: ").append(employee.getPosition())
                    .append("\nDepartment: ").append(employee.getDepartment())
                    .append("\nWage: $").append(employee.getWage());
            return builder.toString();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
            if (position.equals("Manager")) { // could be (position == "Manager")
                Manager auxManager = new Manager(idCounter, name, department, wage, position);
                auxList.add(auxManager);
                idCounter++;
                JOptionPane.showMessageDialog(null, "Employee saved successfully", "Save Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (position.equals("FullTime")) {
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

    private static void increaseWage(int id) {
        try {
            findEmployee(auxList, id).increaseWage();
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registerTestEmployees() {
        try {
            saveEmployee("Thiago", Department.valueOf("QA"), 1850, "FullTime");
            saveEmployee("Gustavo", Department.valueOf("Dev"), 2100, "Manager");
            saveEmployee("Murilo", Department.valueOf("Dev"), 1600, "FullTime");
            saveEmployee("Eduardo", Department.valueOf("QA"), 2050, "Manager");
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}