public class FullTime extends Employee{
    private static final double minimumWage = 1300;
    public FullTime (int id, String name, Department department, double wage, String position) throws Exception {
        super(id, name, department, wage, position);
        if (wage < minimumWage) {
            throw new Exception("The wage needs to be higher than $" + minimumWage);
        }
    }
}
