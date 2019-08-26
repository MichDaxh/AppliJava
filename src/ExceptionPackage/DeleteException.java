package ExceptionPackage;

public class DeleteException extends Exception{
    private String text;

    public DeleteException(String text)  {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Failed to delete data.\nError : " + text;
    }
}
