package ExceptionPackage;

public class CloseException extends Exception{
    private String text;

    public CloseException(String message){
        text = message;
    }

    @Override
    public String toString() {
        return "Failed to close connection. Error :\n" + text;
    }
}
