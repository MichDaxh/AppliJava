package ExceptionPackage;

public class EndDateException extends Exception {
    public EndDateException() {
    }

    @Override
    public String toString() {
        return "Error : end date must be after start date";
    }
}
