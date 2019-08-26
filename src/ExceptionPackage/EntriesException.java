package ExceptionPackage;

public class EntriesException extends Exception{
    private String text;

    public EntriesException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Check entries : " + text;
    }
}
