package ExceptionPackage;

public class IdException extends Exception {
    private String text;

    public IdException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Error with event id. Error : " + text;
    }
}
