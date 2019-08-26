package ExceptionPackage;

public class AddEventException extends Exception {
    private String text;

    public AddEventException(String message){
        text =  message;
    }

    @Override
    public String toString() {
        return "Failed to add data. Error :\n" + text;
    }
}
