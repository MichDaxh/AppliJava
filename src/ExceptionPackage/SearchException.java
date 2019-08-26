package ExceptionPackage;

public class SearchException extends Exception {
    private String text;

    public SearchException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Error during research.\n Error : " +text;
    }
}
