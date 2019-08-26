package ExceptionPackage;

public class AllEventsException extends Exception {
    private String text;

    public AllEventsException(String message){
        text = message;
    }

    @Override
    public String toString() {
        return "Failed to collect data. Error :\n"+ text;
    }
}
