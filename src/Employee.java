abstract class Employee {
    private int id;
    private String name;
    private Department department;
    protected double wage;
    protected String position;

    Employee (int id, String name, Department department, double wage, String position) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.wage = wage;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public double getWage() {
        return wage;
    }
    public String getPosition() {
        return position;
    }
}
