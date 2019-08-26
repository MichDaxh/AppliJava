package ExceptionPackage;

public class SponsorsException extends Exception {
    private String text;

    public SponsorsException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Error with sponsors array. \nError : " + text;
    }
}
