package ExceptionPackage;

public class CastersException extends Exception{
    private String text;

    public CastersException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Error with casters array.\nError : " + text;
    }
}
