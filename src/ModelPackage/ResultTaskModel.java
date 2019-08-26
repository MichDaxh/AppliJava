package ModelPackage;

public class ResultTaskModel {
    private int year;
    private int totalCashPrize;

    public ResultTaskModel(int year, int totalCashPrize) {
        this.year = year;
        this.totalCashPrize = totalCashPrize;
    }

    public int getYear() {
        return year;
    }

    public int getTotalCashPrize() {
        return totalCashPrize;
    }
}
