package ExceptionPackage;

public class UpdateException extends Exception {
    private String text;

    public UpdateException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Failed to update data.\nError : " + text;
    }
}
