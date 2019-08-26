package ModelPackage;

public class Player {
    private Integer id;
    private String firstName, lastName, pseudo, nationality;
    private boolean isBench;

    public Player(Integer id, String firstName, String lastName, String pseudo, String nationality, boolean isBench) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudo = pseudo;
        this.nationality = nationality;
        this.isBench = isBench;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
