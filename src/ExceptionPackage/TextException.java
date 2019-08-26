package ExceptionPackage;

public class TextException extends Exception {
    private String text;

    public TextException(String textName) {
        this.text = textName;
    }

    @Override
    public String toString() {
        return "You have to fill " + text  + ".";
    }
}
