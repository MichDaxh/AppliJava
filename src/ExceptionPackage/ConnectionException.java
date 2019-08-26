package ExceptionPackage;

public class ConnectionException extends Exception {
    private String text;

    public ConnectionException(String message){
        text = message;
    }

    @Override
    public String toString() {
        return "Connection failed. \nError : " + text;
    }
}
