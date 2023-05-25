public class FullTime extends Employee implements WageInteractions{
    private static final double minimumWage = 1300;
    private static final double wageProgression = 150;

    public FullTime (int id, String name, Department department, double wage, String position) throws Exception {
        super(id, name, department, wage, position);
    }

    @Override
    protected double getMinimumWage() {
        return minimumWage;
    }

    @Override
    protected double getWageAdjustment() {return wageProgression;}

    @Override
    public void increaseWage() throws Exception {
        super.increaseWage();
    }
}
