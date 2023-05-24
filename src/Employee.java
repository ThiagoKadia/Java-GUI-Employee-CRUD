abstract class Employee {
    private int id;
    private String name;
    private Department department;
    private double wage;
    protected String position;

    Employee (int id, String name, Department department, double wage, String position) throws Exception {
        this.id = id;
        this.name = name;
        this.department = department;
        setWage(wage);
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

    public void setWage(double wage) throws Exception {
        if (wage < getMinimumWage()) {
            throw new Exception("The wage needs to be higher than $" + getMinimumWage());
        }
        this.wage = wage;
    }

    protected double getMinimumWage() {
        return 0;
    }
}
