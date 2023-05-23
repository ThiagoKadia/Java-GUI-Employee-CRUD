import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainPage {
    private JTextField txtId;
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSearch;
    private JTextField txtSearchId;
    private JTextField txtName;
    private JTextField txtDepartment;
    private JButton btnListAll;
    private JTextArea txtList;
    private JPanel MainPage;
    private static List<Employee> auxList = new ArrayList<>(); //this is the list which will be used in every employee manipulation
    private static Integer idCounter = 1; //the employees ID will start go from 1 and will change only under delete condition

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainPage");
        frame.setContentPane(new MainPage().MainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void clearTextFields() { // this method was created to give a fresh restart to some of the MainPage's fields
        txtId.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtSearchId.setText("");
    }

    public MainPage() {
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText(Integer.toString(idCounter));
                saveEmployee(txtName.getText(), Department.valueOf(txtDepartment.getText()));
                clearTextFields();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee(Integer.parseInt(txtSearchId.getText()) - 1, txtName.getText(), Department.valueOf(txtDepartment.getText()));
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
    }

    public static String buildEmployeeList(List<Employee> employees) { // this method builds a String with all employees in a list
        try {
            String writtenEmployees = "";
            for (Employee employee : employees) {
                writtenEmployees += "ID: " + Integer.toString(employee.getId()) + "\nName: " + employee.getName() + "\nDepartment: " + employee.getDepartment() + "\n\n";
            }
            return writtenEmployees;
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static Employee findEmployee (List<Employee> employees, int id) { // return an employee based on a list, and it's position in the list
        try {
            return employees.get(id);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static String getStringEmployee (Employee employee) { //this method returns a String with the attributes of one employee
        try {
            return "ID: " + Integer.toString(employee.getId()) + "\nName: " + employee.getName() + "\nDepartment: " + employee.getDepartment() + "\n";
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void updateEmployee (int id, String newName, Department newDepartment) {
        try {
            Employee auxEmployee = findEmployee(auxList, id);
            auxEmployee.setName(newName);
            auxEmployee.setDepartment(newDepartment);
            JOptionPane.showMessageDialog(null, "Employee updated successfully", "Update Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void removeEmployee (int id) {
        try {
            auxList.remove(id);
            JOptionPane.showMessageDialog(null, "Employee deleted successfully", "Delete Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void saveEmployee (String name, Department department) {
        try {
            Employee auxEmployee = new Employee(idCounter, name, department);
            auxList.add(auxEmployee);
            idCounter++;
            JOptionPane.showMessageDialog(null, "Employee saved successfully", "Save Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}