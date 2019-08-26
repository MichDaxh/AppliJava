package ExceptionPackage;

public class NbTeamsException extends Exception{
    private String text;
    public NbTeamsException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Error with number of teams.\nError : " + text;
    }
}
