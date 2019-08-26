package ExceptionPackage;

public class DateException extends Exception {
    private String text;

    public DateException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "You have to enter a " + text + " date for the event";
    }
}
