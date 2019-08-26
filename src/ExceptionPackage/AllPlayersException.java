package ExceptionPackage;

public class AllPlayersException extends Exception{
    private String text;

    public AllPlayersException(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Failed to collect data in player table.\nError : " + text;
    }
}
