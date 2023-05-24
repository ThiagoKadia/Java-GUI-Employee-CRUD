public class Manager extends Employee{
    private static final double minimumWage = 2000;
    public Manager (int id, String name, Department department, double wage, String position) throws Exception {
        super(id, name, department, wage, position);
        if (wage < minimumWage) {
            throw new Exception("The wage needs to be higher than $" + minimumWage);
        }
    }
}
