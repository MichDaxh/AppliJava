package ExceptionPackage;

public class TaskException extends Exception{
    private String text;

    public TaskException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Failed to retrieve task data.\nError : " +text;
    }
}
