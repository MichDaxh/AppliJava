package ExceptionPackage;

public class CashPrizeException extends Exception {
    private String text;
    public CashPrizeException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Error with cash prize. Error : " + text;
    }
}
