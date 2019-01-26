package dudzinski.musiconator;

public class User {
    // fields
    private int id;
    private String email;
    private String password;

    // constructors
    public User() {}
    public User(String userEmail, String userPassword) {
        this.email = userEmail;
        this.password = userPassword;
    }
    // properties
    public void setID(int userID) {
        this.id = userID;
    }
    public int getID() {
        return id;
    }

    public void setEmail(String userEmail) {
       this.email = userEmail;
    }
    public String getEmail() { return email;
    }
    public void setPassword(String userPassword) {
        this.password = userPassword;
    }
    public String getPassword() {
        return password;
    }

}